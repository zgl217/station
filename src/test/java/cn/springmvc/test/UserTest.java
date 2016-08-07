package cn.springmvc.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.springmvc.service.UserService;



public class UserTest {

private UserService userService;
	
	@Before
	public void before(){                                                                    
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
				,"classpath:conf/spring-mybatis.xml"});
		userService = (UserService) context.getBean("userService");
	}
	
//	@Test
//	public void addUser(){
////		User user = new User();
////		user.setNickname("你好");
////		user.setState(2);
////		System.out.println(userService.insertUser(user));
//	}
	
//	@Test
//	public void selectUserList(){
//		List<User> list = userService.selectUserList();
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.get(i).toString());
//		}
//	}
	
	@Test
	public void selectUserListPage(){
	}
	
}
