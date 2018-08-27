package com.lianglianglee.edit.service.impl;

import com.lianglianglee.edit.common.exception.AuthorizationException;
import com.lianglianglee.edit.common.exception.ServiceException;
import com.lianglianglee.edit.common.model.EditJwtModel;
import com.lianglianglee.edit.common.service.JwtAuthenticationServiceImpl;
import com.lianglianglee.edit.common.utils.BeanUtils;
import com.lianglianglee.edit.common.utils.GoogleAuthenticator;
import com.lianglianglee.edit.dao.UserDAO;
import com.lianglianglee.edit.dto.result.LoginDto;
import com.lianglianglee.edit.dto.result.UserDto;
import com.lianglianglee.edit.dto.update.UserPasswordDto;
import com.lianglianglee.edit.entity.UserEntity;
import com.lianglianglee.edit.service.UserService;
import com.lianglianglee.edit.utils.Qrcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author liangliang
 * @date 2018年7月16日
 */
@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserDAO userDAO;
  @Autowired
  private JwtAuthenticationServiceImpl jwtAuthenticationService;

  @Override
  public LoginDto login(String userName, String password) {
    LoginDto dto = new LoginDto();
    UserEntity user = userDAO.selectByUser(userName);
    if (user == null || !user.getPassword().equals(md5(password))) {
      throw new AuthorizationException("用户名或密码错误!");
    }
    user.setPassword(null);

    UserDto userDto = BeanUtils.EntityToDto(user, UserDto.class);
    if (null == user.getSecret()) {
      userDto.setAuth(false);
    } else {
      userDto.setAuth(true);
    }
    dto.setUserDto(userDto);
    dto.setTocken(getTocken(userDto));

    //TODO 用户所在小组
    return dto;
  }

  @Override
  public void update(UserDto userDto) {
    UserEntity oldEntity = userDAO.selectById(userDto.getId());
    oldEntity.buildDefaultLastTime();
    oldEntity.setEmail(userDto.getEmail());
    oldEntity.setName(userDto.getName());
    oldEntity.setPhone(userDto.getPhone());
    userDAO.updateById(oldEntity);
  }

  @Override
  public void updatePassword(UserPasswordDto dto) {
    UserEntity entity = userDAO.selectById(dto.getId());
    if (null == entity) {
      throw new ServiceException("未知账户");
    }
    if (!entity.getPassword().equals(md5(dto.getOldPassword()))) {
      throw new ServiceException("请校对旧密码!");
    }
    entity.setPassword(md5(dto.getNewPassword()));
    entity.buildDefaultLastTime();
    userDAO.updateById(entity);
  }

  @Override
  public String getCodeImg(Long userId) {
    UserEntity entity = userDAO.selectById(userId);
    if (null == entity.getSecret()) {
      String secret = GoogleAuthenticator.generateSecretKey();
      entity.setSecret(secret);
    } else {
      throw new ServiceException("您已使用过二次验证！");
    }
    entity.buildDefaultLastTime();
    userDAO.updateById(entity);
    String url=GoogleAuthenticator.getUrl(entity.getSecret(),entity.getName());
    return Qrcode.getCodeString(entity.getSecret());
  }

  @Override
  public void insert(UserDto dto) {
    if (userDAO.countByPhoneOrEmail(dto.getEmail(), dto.getPhone()) > 0) {
      throw new ServiceException("手机号或邮箱已占用!");
    }
    UserEntity entity = BeanUtils.DtoToEntity(dto, UserEntity.class);
    entity.setStatus(1);
    entity.setPassword(md5(dto.getPassword()));
    entity.buildDefaultTimeStamp();
    userDAO.insert(entity);
  }


  private String md5(String s) {
    try {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      BASE64Encoder base64en = new BASE64Encoder();
      //加密后的字符串
      return base64en.encode(md5.digest(s.getBytes("utf-8")));

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new ServiceException("修改密码失败");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      throw new ServiceException("修改密码失败");
    }
  }

  private String getTocken(UserDto dto) {
    EditJwtModel model = new EditJwtModel();
    model.setId(dto.getId());
    model.setUserName(dto.getName());
    return jwtAuthenticationService.generateToken(model, 360000000);
  }

  @Override
  public void checkCode(Long userId, String code) {
    UserEntity userEntity = userDAO.selectById(userId);
    if (null == userEntity.getSecret()) {
      throw new ServiceException("未开启二次验证");
    }
    boolean check = GoogleAuthenticator.authcode(code, userEntity.getSecret());
    if (!check) {
      throw new ServiceException("验证失败，请重试");
    }
  }
}
