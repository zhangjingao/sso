package org.wlgzs.website.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.website.enums.Method;
import org.wlgzs.website.util.HttpUtil.HttpUtil;
import org.wlgzs.website.util.Result.Result;
import org.wlgzs.website.util.Result.ResultCodeEnum;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author zjg
 * @date 2018/8/8 9:33
 * @Description 登录控制层
 */
@Slf4j
@RestController
public class LoginController {

    private final SsoCheck ssoCheck;

    @Autowired
    public LoginController(SsoCheck ssoCheck) {
        this.ssoCheck = ssoCheck;
    }

    /**
     *
     * @return 响应界面：login/index
     */
    @GetMapping("/ssocheck")
    public ModelAndView checkCookies (HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("jian".equals(cookie.getName())) { //统一登录cookie为jian，如果存在就认证
                    log.info("cookie 存在，开始验证");
                    boolean authBoo = ssoCheck.checkAuthCookies(cookie.getName(), cookie.getValue());
                    if (authBoo) {
                        log.info("验证通过");
                        return new ModelAndView("public/index");
                    }
                    break;
                }
            }
        }
        return new ModelAndView("index");
    }

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return index/login
     */
    @PostMapping("/login")
    public ModelAndView doLogin (String username, String password) {
        if (username != null && !"".equals(username) &&
                    password != null && !"".equals(password) ) {
            Result result = ssoCheck.checkLogin(username,password);
            int isLogin = result.getResultCode().getCode();
            if (isLogin == 1) {
                @SuppressWarnings("all")
                Map<String,String> param = (Map<String, String>) result.getData();
                return new ModelAndView("public/index","sendparam",param);
            }
        }
        return new ModelAndView("index");
    }

    /**
     * 退出到登录界面
     * @return 登录界面
     */
    @GetMapping("/loginout")
    public ModelAndView loginOut () {
        return new ModelAndView("index");
    }

    /**
     * 清除掉cookie
     * @param request 请求
     * @param response 响应
     */
    @GetMapping("/clear")
    public void clear (HttpServletRequest request,HttpServletResponse response) {
        //获得域名
        log.info("clear掉ip为："+request.getRemoteHost()+"的cookie");
        Cookie [] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if ("jian".equals(cookie.getName())) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    /**
     *
     * @param cookieName cookie名称
     * @param cookieValue cookie值
     * @param response 响应
     */
    @GetMapping("/addcookie")
    public void addCookies (String cookieName, String cookieValue, HttpServletResponse response) {
        log.info("添加cookie");
        Cookie cookie = new Cookie(cookieName,cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}
