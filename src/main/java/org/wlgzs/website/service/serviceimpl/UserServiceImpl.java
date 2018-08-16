package org.wlgzs.website.service.serviceimpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlgzs.website.dao.UserDao;
import org.wlgzs.website.entity.TbUser;
import org.wlgzs.website.service.UserService;
import org.wlgzs.website.util.AesUtil;

import java.util.List;

/**
 * @author zjg
 * @date 2018/8/14 8:28
 * @Description 用户业务类
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean insertUser(TbUser tbUser) {
        List<TbUser> tbUserDb = userDao.selectList(
                new EntityWrapper<TbUser>().eq("username", tbUser.getUsername())
        );
        if (tbUserDb.size() > 0) {
            return false;
        }
        userDao.insert(tbUser);
        return true;
    }

    @Override
    public TbUser login(String username, String password) {
        TbUser[] user = {null};
        List<TbUser> userList = userDao.selectList(
                new EntityWrapper<TbUser>().eq("username", username)
        );
        if (userList.size() > 0) {
            AesUtil aesUtil = new AesUtil(password);
            String encyptPassword = aesUtil.encrypt();
            //加盐密码验证
            userList.forEach((TbUser u) ->{
                String encyPassSalt = encyptPassword+u.getSalt();
                aesUtil.setContent(encyPassSalt);
                String result = aesUtil.encrypt();
                if (result != null && result.equals(u.getPassword())) {
                    user[0] = u;
                }
            });
        }
        return user[0];
    }
}
