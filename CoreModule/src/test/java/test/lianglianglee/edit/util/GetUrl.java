package test.lianglianglee.edit.util;

import com.doubi.edit.common.utils.BeanUtils;
import com.doubi.edit.dto.result.base.UserDto;
import com.doubi.edit.entity.UserEntity;

/**
 * Created by SJG on 2015/11/18.
 */

public class GetUrl {
  public static void main(String args[]) {
    UserDto dto = new UserDto();
    dto.setId(1L);
    dto.setName("dddd");
    UserEntity entity = BeanUtils.dtoToEntity(dto, UserEntity.class);
    System.out.println(entity.getId());

  }
}
