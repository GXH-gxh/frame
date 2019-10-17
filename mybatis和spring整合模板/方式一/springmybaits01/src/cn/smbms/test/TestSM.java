package cn.smbms.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.smbms.entity.User;
import cn.smbms.service.UserService;

public class TestSM {

	@Test
	public void test() {
		//加载spring配置文件
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService service =  (UserService) ac.getBean("userService");
		List<User> list = service.findUserList();
		for (User user : list) {
			System.out.println("用户编号:"+user.getId()+",用户名："+user.getUserCode()+",真实姓名："+user.getUserName());
		}
	}

}
