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
		//����spring�����ļ�
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService service =  (UserService) ac.getBean("userService");
		List<User> list = service.findUserList();
		for (User user : list) {
			System.out.println("�û����:"+user.getId()+",�û�����"+user.getUserCode()+",��ʵ������"+user.getUserName());
		}
	}

}
