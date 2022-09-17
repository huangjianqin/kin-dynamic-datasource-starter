package org.kin.springboot.jdbc.dynamic;

import org.kin.framework.mybatis.DaoSupport;
import org.springframework.stereotype.Component;

/**
 * @author huangjianqin
 * @date 2022/9/17
 */
@Component
public class UserDao extends DaoSupport<User, UserMapper> {
}
