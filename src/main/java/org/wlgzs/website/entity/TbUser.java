package org.wlgzs.website.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zjg
 * @date 2018/8/8 17:23
 * @Description 用户实体类：13个属性
 */
@Data
@TableName("tb_user")
public class TbUser implements Serializable {

    private int id;
    /**
     * 用户名：邮箱
     */
    private String username;
    /**
     * 姓名
     */
    private String name;
    /**
     * 加密所用盐
     */
    private String salt;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private String sex;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 年级
     */
    private String grades;
    /**
     * 班级名称
     */
    private String className;
    /**
     * 角色，职位：数据库使用外键，被依赖表是角色表
     */
    private int roleId;
    /**
     * 学习方向
     */
    private String studyDirect;
    /**
     * 头像
     */
    private String headPhoto;
    /**
     * 博客地址
     */
    private String blogUrl;
    /**
     * 最后一次登录时间
     */
    private String lastLoginDate;
    /**
     * 个人介绍
     */
    private String remark;

    public TbUser(String username, String name, String salt, String password, String sex, String phone, String grades, String className, int roleId, String studyDirect, String headPhoto, String blogUrl, String lastLoginDate, String remark) {
        this.username = username;
        this.name = name;
        this.salt = salt;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.grades = grades;
        this.className = className;
        this.roleId = roleId;
        this.studyDirect = studyDirect;
        this.headPhoto = headPhoto;
        this.blogUrl = blogUrl;
        this.lastLoginDate = lastLoginDate;
        this.remark = remark;
    }

    public TbUser() {}

    @Override
    public String toString() {
        return JSON.parse("{" +
                "id:" + id +
                ", username:'" + username + '\'' +
                ", name:'" + name + '\'' +
                ", salt:'" + salt + '\'' +
                ", password:'" + password + '\'' +
                ", sex:'" + sex + '\'' +
                ", phone:'" + phone + '\'' +
                ", grades:'" + grades + '\'' +
                ", className:'" + className + '\'' +
                ", roleId:" + roleId +
                ", studyDirect:'" + studyDirect + '\'' +
                ", headPhoto:'" + headPhoto + '\'' +
                ", blogUrl:'" + blogUrl + '\'' +
                ", lastLoginDate:'" + lastLoginDate + '\'' +
                ", remark:'" + remark + '\''+
                "}").toString();
    }
}
