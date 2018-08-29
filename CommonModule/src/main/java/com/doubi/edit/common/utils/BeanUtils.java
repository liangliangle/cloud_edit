package com.doubi.edit.common.utils;

import com.doubi.edit.common.entity.BaseEntity;
import com.doubi.edit.common.dto.BaseDto;
import com.doubi.edit.common.exception.ServiceException;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;

public class BeanUtils {

  private static Logger logger = Logger.getLogger(BeanUtils.class);

  public static <T> T DtoToEntity(BaseDto dto, Class<T> t) {
    return beanToBean(dto, t);

  }

  public static <T> T EntityToDto(BaseEntity entity, Class<T> t) {
    return beanToBean(entity, t);
  }

  private static <T> T beanToBean(Object o, Class<T> t) {
    try {
      T dto = t.newInstance();
      return beanCopy(dto, o);
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
            logger.error(e.getMessage());
          } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
          }
        }
      }
    }
    return newO;
  }


}

