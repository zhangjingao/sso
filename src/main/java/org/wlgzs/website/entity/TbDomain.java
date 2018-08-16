package org.wlgzs.website.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zjg
 * @date 2018/8/9 19:50
 * @Description 子系统（域名）管理
 */
@Data
@TableName("tb_domain")
public class TbDomain implements Serializable {

    private int id;
    /**
     * 域名
     */
    private String domain;
    /**
     * 域名所在系统信息描述
     */
    private String describe;
    /**
     * 备份字段
     */
    private String remark;

    public TbDomain(String domain, String describe, String remark) {
        this.domain = domain;
        this.describe = describe;
        this.remark = remark;
    }

    public TbDomain () {}
}
