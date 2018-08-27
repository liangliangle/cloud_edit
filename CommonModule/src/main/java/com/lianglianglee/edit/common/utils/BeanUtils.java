package com.lianglianglee.edit.common.utils;

import com.lianglianglee.edit.common.dto.BaseDto;
import com.lianglianglee.edit.common.entity.BaseEntity;
import com.lianglianglee.edit.common.exception.ServiceException;

import java.lang.reflect.Field;

public class BeanUtils {


  public static <T> T DtoToEntity(BaseDto dto, Class<T> t) {
    try {
      T entity = t.newInstance();
      return beanCopy(entity, dto);
    } catch (InstantiationException e) {
      e.printStackTrace();
      throw new ServiceException(e.getMessage());
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      throw new ServiceException(e.getMessage());
    }

  }

  public static <T> T EntityToDto(BaseEntity entity, Class<T> t) {
    try {
      T dto = t.newInstance();
      return beanCopy(dto, entity);
    } catch (InstantiationException e) {
      e.printStackTrace();
      throw new ServiceException(e.getMessage());
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      throw new ServiceException(e.getMessage());
    }
  }

  private static <T> T beanCopy(T newO, Object oldO) {
    Field[] oldFields = oldO.getClass().getDeclaredFields();
    Field[] newFields = newO.getClass().getDeclaredFields();
    for (Field f1 : oldFields) {
      f1.setAccessible(true);
      for (Field f2 : newFields) {
        if (f1.getName().equals(f2.getName())) {
          f2.setAccessible(true);
          try {
            f2.set(newO, f1.get(oldO));
          } catch (IllegalArgumentException e) {
            e.printStackTrace();
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return newO;
  }


}

