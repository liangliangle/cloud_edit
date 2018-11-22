package com.lianglianglee.edit.dao;

import com.lianglianglee.edit.entity.DemoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemoDAO {
  List<DemoEntity> getAll();
}
