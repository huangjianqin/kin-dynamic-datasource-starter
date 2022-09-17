package org.kin.springboot.jdbc.dynamic;

import org.kin.springboot.jdbc.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huangjianqin
 * @date 2022/9/17
 */
@RestController
@RequestMapping("/ds")
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/master")
    @UseDataSource("master")
    public List<User> queryMaster(){
        return userDao.selectList(null);
    }

    @GetMapping("/slave1")
    @UseDataSource("slave1")
    public List<User> querySlave1(){
        return userDao.selectList(null);
    }

    @GetMapping("/slave2")
    @UseDataSource("slave2")
    public List<User> querySlave2(){
        return userDao.selectList(null);
    }
}
