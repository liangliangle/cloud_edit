package com.doubi.edit.dao;

import com.doubi.edit.entity.DemoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemoDAO {
  List<DemoEntity> getAll();
}
