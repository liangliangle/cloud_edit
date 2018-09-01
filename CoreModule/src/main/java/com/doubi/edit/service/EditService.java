package com.doubi.edit.service;

import com.doubi.edit.common.dto.PageDto;
import com.doubi.edit.dto.create.EditCreateDto;
import com.doubi.edit.dto.result.EditDetailDto;
import com.doubi.edit.dto.result.ShareDto;

public interface EditService {


  void createEdit(EditCreateDto dto);

  void updateEdit(EditCreateDto dto, Long id);

  EditDetailDto getById(Long id);


  PageDto getLog(Long id, int page, int row);

  ShareDto share(Long id, boolean encry);

  EditDetailDto getLogDetail(Long id);
}
