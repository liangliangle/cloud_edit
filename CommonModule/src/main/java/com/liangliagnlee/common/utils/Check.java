package com.liangliagnlee.common.utils;

import com.liangliagnlee.common.exception.ValidationException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {

  public static void check(Object o) {
    if (null == o) {
      return;
    }
    Class clazz = o.getClass();
    List<Field> fieldList = new ArrayList<Field>();
    while (clazz != null) {
      fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
      clazz = clazz.getSuperclass();
    }
    fieldList.forEach(field -> {
      field.setAccessible(true);
      try {
        Object value = field.get(o);
        Validation annotation = field.getAnnotation(Validation.class);
        if (null == annotation) {
          return;
        }
        checkNotNull(value, annotation);
        checkPattern(value, annotation);
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
        //数据解析失败
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    });
  }

  private static double objectToDubule(Object o) {
    return Double.valueOf(o.toString());
  }


  private static void checkNotNull(Object value, Validation validation) {
    if (validation.notNull() && value == null) {
      throw new ValidationException(validation.value());
    }
    if (value instanceof String) {
      if (((String) value).length() == 0) {
        throw new ValidationException(validation.value());
      }
    }
  }

  private static void checkPattern(Object value, Validation validation) {
    if (null != validation.pattern() && validation.pattern().length() > 0) {
      Pattern p = Pattern.compile(validation.pattern());
      Matcher m = p.matcher(value.toString());
      if (!m.matches()) {
        throw new ValidationException(validation.value());
      }
    }
  }

}
