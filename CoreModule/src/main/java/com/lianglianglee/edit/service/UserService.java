package com.lianglianglee.edit.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.liangliagnlee.common.exception.AuthorizationException;
import com.liangliagnlee.common.exception.ServiceException;
import com.liangliagnlee.common.model.EditJwtModel;
import com.liangliagnlee.common.service.JwtService;
import com.liangliagnlee.common.utils.BeanUtils;
import com.liangliagnlee.common.utils.EditUtils;
import com.liangliagnlee.common.utils.GoogleAuthenticator;
import com.lianglianglee.edit.dao.UserDAO;
import com.lianglianglee.edit.dto.create.GroupCreateDto;
import com.lianglianglee.edit.dto.create.UserCreateDto;
import com.lianglianglee.edit.dto.result.base.LoginDto;
import com.lianglianglee.edit.dto.result.base.UserDto;
import com.lianglianglee.edit.dto.update.UserPasswordDto;
import com.lianglianglee.edit.entity.UserEntity;
import com.lianglianglee.edit.enums.GroupTypeEnum;
import com.lianglianglee.edit.utils.Qrcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liangliang
 * @date 2018年7月16日
 */
@Service
public class UserService {
  @Autowired
  private UserDAO userDAO;
  @Autowired
  private GroupService groupService;
  private JwtService jwtService = new JwtService();

  public LoginDto login(String userName, String password) {

    UserEntity user = userDAO.selectByUser(userName);
    if (user == null || !user.getPassword().equals(md5(password))) {
      throw new AuthorizationException("用户名或密码错误!");
    }
    return buildLoginDto(user);
  }

  private LoginDto buildLoginDto(UserEntity user) {

    LoginDto dto = new LoginDto();
    UserDto userDto = BeanUtils.entityToDto(user, UserDto.class);
    if (null == user.getSecret()) {
      userDto.setAuth(false);
    } else {
      userDto.setAuth(true);
    }
    dto.setUserDto(userDto);
    dto.setTocken(getTocken(userDto));
    dto.setGroups(groupService.getByUser(user.getId()));
    return dto;
  }

  public LoginDto reload(String token) {
    EditJwtModel model = jwtService.getAuthenticatedUser(token);
    if (model == null) {
      throw new ServiceException("登录失败");
    }
    LoginDto dto = new LoginDto();
    UserEntity user = userDAO.selectById(model.getId());
    if (user == null) {
      throw new AuthorizationException("登录失败!");
    }
    return buildLoginDto(user);
  }

  public void update(UserDto userDto) {
    UserEntity oldEntity = userDAO.selectById(userDto.getId());
    oldEntity.change(userDto.getName(), userDto.getEmail(), userDto.getPhone());
    userDAO.updateById(oldEntity);
  }

  public void updatePassword(UserPasswordDto dto) {
    UserEntity entity = userDAO.selectById(dto.getId());
    if (null == entity) {
      throw new ServiceException("未知账户");
    }
    if (!entity.getPassword().equals(md5(dto.getOldPassword()))) {
      throw new ServiceException("请校对旧密码!");
    }
    entity.changePassword(md5(dto.getNewPassword()));
    userDAO.updateById(entity);
  }

  public String getCodeImg(Long userId) {
    UserEntity entity = userDAO.selectById(userId);
    if (null == entity.getSecret()) {
      String secret = GoogleAuthenticator.generateSecretKey();
      entity.changeSecret(secret);
    } else {
      throw new ServiceException("您已使用过二次验证！");
    }
    entity.buildDefaultLastTime();
    userDAO.updateById(entity);
    String url = GoogleAuthenticator.getUrl(entity.getSecret(), entity.getName());
    return Qrcode.getCodeString(url);
  }

  @Transactional(rollbackFor = ServiceException.class)
  public void insert(UserCreateDto dto) {
    if (userDAO.countByPhoneOrEmail(dto.getEmail(), dto.getPhone()) > 0) {
      throw new ServiceException("手机号或邮箱已占用!");
    }
    UserEntity entity = new UserEntity();
    entity.change(dto.getName(), dto.getEmail(), dto.getPhone(), null, null, 1);
    entity.changePassword(md5(dto.getPassword()));
    userDAO.insert(entity);
    GroupCreateDto groupCreateDto = new GroupCreateDto();
    groupCreateDto.setName("默认笔记");
    groupCreateDto.setType(GroupTypeEnum.PRIVATE.getType());
    groupCreateDto.setUserId(entity.getId());
    groupCreateDto.setUserName(entity.getName());
    groupService.insert(groupCreateDto);
  }

  private String md5(String s) {
    try {
      return EditUtils.encoderByMd5(s);
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
    return jwtService.generateToken(model, 360000000);
  }

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

  public UserDto getById(Long id) {
    UserEntity entity = userDAO.selectById(id);
    return BeanUtils.entityToDto(entity, UserDto.class);
  }

  public UserDto getByMailOrPhone(String mailOrPhone) {
    UserEntity user = userDAO.selectByUser(mailOrPhone);
    if (null == user) {
      throw new ServiceException("未知用户");
    }
    return BeanUtils.entityToDto(user, UserDto.class);
  }
}
