package org.wlgzs.website.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlgzs.website.dao.DomainDao;
import org.wlgzs.website.entity.TbDomain;
import org.wlgzs.website.service.DomainService;

import java.util.List;

/**
 * @author zjg
 * @date 2018/8/14 15:21
 * @Description 子系统（域名）管理业务层实现类
 */
@Service
public class DomainServiceimpl implements DomainService {

    @Autowired
    private DomainDao domainDao;

    @Override
    public List<TbDomain> selectAll() {
        return domainDao.selectList(null);
    }
}
