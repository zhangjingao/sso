package org.wlgzs.website.service;

import org.springframework.transaction.annotation.Transactional;
import org.wlgzs.website.entity.TbUser;

/**
 * @author zjg
 * @date 2018/8/14 8:27
 * @Description 用户业务层接口
 */
public interface UserService {


    /**
     * 添加用户
     * @param tbUser 用户信息
     * @return 当添加成功时返回true
     */
    @Transactional
    boolean insertUser(TbUser tbUser);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 如果用户存在返回user，反之为null
     */
    TbUser login(String username, String password);
}
