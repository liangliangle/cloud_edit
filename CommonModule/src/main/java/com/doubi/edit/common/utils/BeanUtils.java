package com.doubi.edit.common.utils;

import com.doubi.edit.common.entity.BaseEntity;
import com.doubi.edit.common.dto.BaseDto;
import com.doubi.edit.common.exception.ServiceException;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    Field[] oldFields = getAllFields(oldO);
    Field[] newFields = getAllFields(newO);
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

  private static Field[] getAllFields(Object object) {
    Class clazz = object.getClass();
    List<Field> fieldList = new ArrayList<Field>();
    while (clazz != null) {
      fieldList.addAll(new ArrayList<Field>(Arrays.asList(clazz.getDeclaredFields())));
      clazz = clazz.getSuperclass();
    }
    Field[] fields = new Field[fieldList.size()];
    fieldList.toArray(fields);
    return fields;
  }


}

