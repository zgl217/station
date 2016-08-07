package cn.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.dao.UserDao;
import cn.springmvc.interceptor.Page;
import cn.springmvc.util.PageData;


@Service
public class UserService{

	@Autowired
	private UserDao userDao;
	
	
//	public int insertUser(User user) {
//		// TODO Auto-generated method stub
//		return userDAO.insertUser(user);
//	}
//	public List<User> selectUserListPage(Page pageParam) {
//		// TODO Auto-generated method stub
//		return userDAO.selectUserListPage(pageParam);
//	}
	
	public int selectUserListCount(Page page) throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectUserListCount(page);
	}
	
	public List<PageData> selectUserListPage(Page page) throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectUserListPage(page);
	}
}
