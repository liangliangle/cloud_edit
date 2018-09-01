package com.doubi.edit.service.impl;

import com.doubi.edit.common.dto.PageDto;
import com.doubi.edit.common.exception.ServiceException;
import com.doubi.edit.common.interceptor.HttpContext;
import com.doubi.edit.dao.EditDAO;
import com.doubi.edit.dao.EditInfoDAO;
import com.doubi.edit.dao.EditLogDAO;
import com.doubi.edit.dto.create.EditCreateDto;
import com.doubi.edit.dto.result.EditDetailDto;
import com.doubi.edit.dto.result.ShareDto;
import com.doubi.edit.entity.EditEntity;
import com.doubi.edit.entity.EditInfoEntity;
import com.doubi.edit.entity.EditLogEntity;
import com.doubi.edit.enums.LogTypeEnum;
import com.doubi.edit.service.EditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditServiceImpl implements EditService {
  @Autowired
  private EditDAO editDAO;
  @Autowired
  private EditLogDAO logDAO;
  @Autowired
  private EditInfoDAO infoDAO;


  private static Logger logger = LoggerFactory.getLogger(EditServiceImpl.class);

  @Override
  @Transactional(rollbackFor = ServiceException.class)
  public void createEdit(EditCreateDto dto) {
    Long userId = HttpContext.getContext().getUserId();
    String userName = HttpContext.getContext().getUserName();
    logger.info("insert:userId:{},parentId:{},content:{}", userId, dto.getParentId(), dto.getContent());
    EditEntity entity = new EditEntity();
    entity.setGroupId(dto.getGroupId());
    entity.setParentId(dto.getParentId());
    entity.setStatus(1);
    entity.setUserId(userId);
    entity.setUserName(userName);
    entity.buildDefaultTimeStamp();
    editDAO.insert(entity);
    insert(entity.getId(), dto.getContent());

  }

  @Override
  @Transactional(rollbackFor = ServiceException.class)
  public void updateEdit(EditCreateDto dto, Long id) {
    Long userId = HttpContext.getContext().getUserId();
    String userName = HttpContext.getContext().getUserName();
    logger.info("update:userId:{},parentId:{},content:{}", userId, dto.getParentId(), dto.getContent());
    EditEntity entity = editDAO.selectById(id);
    //todo  请注意小组成员也可以修改
    if (!entity.getUserId().equals(userId)) {
      throw new ServiceException("未找到笔记");
    }
    if (!entity.getParentId().equals(dto.getGroupId())) {
      entity.setParentId(dto.getParentId());
      entity.buildDefaultLastTime();
      editDAO.updateById(entity);
    }
    EditInfoEntity infoEntity = infoDAO.getByEditId(id);
    if (!infoEntity.getInfo().equals(dto.getContent())) {
      insert(id, dto.getContent());
    }
  }

  @Override
  public EditDetailDto getById(Long id) {
    //todo
    return null;
  }

  @Override
  public PageDto getLog(Long id, int page, int row) {//todo
    return null;
  }

  @Override
  public ShareDto share(Long id, boolean encry) {//todo
    return null;
  }

  @Override
  public EditDetailDto getLogDetail(Long id) {//todo
    return null;
  }


  private void insert(Long editId, String content) {
    logger.info("editId:{},content:{}", editId, content);
    EditInfoEntity oldInfo = infoDAO.getByEditId(editId);
    LogTypeEnum type = LogTypeEnum.CREATE;
    EditInfoEntity infoEntity = new EditInfoEntity();
    infoEntity.setEditId(editId);
    infoEntity.setInfo(content);
    infoEntity.setStatus(1);
    infoEntity.setType(1);
    infoEntity.buildDefaultTimeStamp();
    infoDAO.insert(infoEntity);
    Long infoId = infoEntity.getId();
    if (null != oldInfo) {
      type = LogTypeEnum.UPDATE;
      infoId = oldInfo.getId();
      saveLog(editId, type, infoId);
    }
  }

  private void saveLog(Long editId, LogTypeEnum type, Long infoId) {
    logger.info("type:{},editId:{},infoId:{}", type, editId, infoId);
    EditLogEntity logEntity = new EditLogEntity();
    logEntity.setInfoId(infoId);
    logEntity.setEditId(editId);
    logEntity.setType(type.getType());
    logEntity.setStatus(1);
    logEntity.buildDefaultTimeStamp();
    logDAO.insert(logEntity);

  }
}
