package cn.springmvc.dao;

import java.util.List;

import cn.springmvc.interceptor.Page;
import cn.springmvc.util.PageData;


public interface HotDao {

	
	/**
	 * 查询总数
	 * @param user
	 * @return
	 */
	public int selectHotListCount(Page pageParam);
	
	/**
	 * 查询分页列表
	 * @param user
	 * @return
	 */
	public List<PageData> selectHotListPage(Page pageParam);
	
	
}
