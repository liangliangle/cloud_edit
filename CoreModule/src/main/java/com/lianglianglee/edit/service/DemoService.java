package com.lianglianglee.edit.service;

import com.lianglianglee.edit.dao.DemoDAO;
import com.lianglianglee.edit.dto.DemoDto;
import com.lianglianglee.edit.entity.DemoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoService {
  @Autowired
  private DemoDAO demoDAO;

  public List<DemoDto> getAll() {
    List<DemoEntity> entityList = this.demoDAO.getAll();
    return convert(entityList, new ArrayList<DemoDto>(entityList.size()));

  }

  private List<DemoDto> convert(List<DemoEntity> entities, List<DemoDto> dtos) {
    for (DemoEntity entity : entities) {
      DemoDto dto = new DemoDto();
      dto.setId(entity.getId());
      dto.setName(entity.getName());
      dtos.add(dto);
    }
    return dtos;
  }
}
