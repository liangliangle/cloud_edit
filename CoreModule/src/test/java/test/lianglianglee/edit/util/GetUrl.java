package test.lianglianglee.edit.util;

import com.lianglianglee.edit.common.utils.BeanUtils;
import com.lianglianglee.edit.dto.result.UserDto;
import com.lianglianglee.edit.entity.UserEntity;

/**
 * Created by SJG on 2015/11/18.
 */

public class GetUrl {
    public static void main(String args[]){
        UserDto dto=new UserDto();
        dto.setName("dddd");
       UserEntity entity= BeanUtils.DtoToEntity(dto,UserEntity.class);
       System.out.println(entity.getName());

    }
}
