package org.wlgzs.website.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wlgzs.website.entity.TbUser;
import org.wlgzs.website.service.UserService;
import org.wlgzs.website.util.Result.Result;
import org.wlgzs.website.util.Result.ResultCodeEnum;

/**
 * @author zjg
 * @date 2018/8/14 8:19
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping
    public Result insertUser (TbUser tbUser) {
        boolean boo = userService.insertUser(tbUser);
        if (boo) {
            return new Result(ResultCodeEnum.INSERTSUCCESS,"添加成功");
        }
        return new Result(ResultCodeEnum.INSERTFAIL,"用户已存在");
    }
}
