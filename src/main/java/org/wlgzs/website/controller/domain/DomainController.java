package org.wlgzs.website.controller.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wlgzs.website.entity.TbDomain;
import org.wlgzs.website.service.DomainService;
import org.wlgzs.website.util.Result.Result;
import org.wlgzs.website.util.Result.ResultCodeEnum;

import java.util.List;

/**
 * @author zjg
 * @date 2018/8/14 15:34
 * @Description 子系统(域名)管理控制层
 */
@Slf4j
@RestController
@RequestMapping("/domain")
public class DomainController {

    @Autowired
    private DomainService domainService;

    @GetMapping
    public Result<List<TbDomain>> getAllDomain () {
        List<TbDomain> domainList = domainService.selectAll();
        return new Result<>(ResultCodeEnum.SELECTSUCCESS,null,domainList);
    }

}
