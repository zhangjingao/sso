package org.wlgzs.website.service;

import org.springframework.transaction.annotation.Transactional;
import org.wlgzs.website.entity.TbDomain;

import java.util.List;

/**
 * @author zjg
 * @date 2018/8/14 15:16
 * @Description 子系统（域名）管理业务层接口
 */
public interface DomainService {

    /**
     * 查询全部
     * @return list
     */
    @Transactional
    List<TbDomain> selectAll ();

}
