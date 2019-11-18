package com.wlgzs.org;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wlgzs.website.OrgApplication;
import org.wlgzs.website.controller.user.UserController;
import org.wlgzs.website.entity.TbUser;
import org.wlgzs.website.util.Result.Result;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgApplication.class)
public class OrgApplicationTests {

	@Autowired
	private UserController userController;

	@Test
	public void insert(){
		log.info("start test");
		TbUser tbUser = new TbUser(
			"zjg","zhangjingao","1234","1234","男","187xxxxxxxx","大二","班级",1,"JavaWeb","","","",""
		);
		Result result = userController.insertUser(tbUser);
		log.info(result.toString());
		log.info(tbUser.toString());
	}



}
