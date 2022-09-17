package org.kin.springboot.jdbc.dynamic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author huangjianqin
 * @date 2022/9/17
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
