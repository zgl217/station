package cn.springmvc.dao;

import java.util.List;

import cn.springmvc.interceptor.Page;
import cn.springmvc.model.User;
import cn.springmvc.util.PageData;


public interface UserDao {

	/**
	 * 添加新用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
	
	
	/**
	 * 分页查询用户列表
	 * @param user
	 * @return
	 */
	public int selectUserListCount(Page pageParam);
	
	/**
	 * 分页查询用户列表
	 * @param user
	 * @return
	 */
	public List<PageData> selectUserListPage(Page pageParam);
	
	
}
