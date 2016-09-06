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
	public int selectListCount(Page pageParam);
	
	/**
	 * 查询分页列表
	 * @param user
	 * @return
	 */
	public List<PageData> selectListPage(Page pageParam);
	
	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public int delete();
	
	/**
	 * 插入记录
	 * @param user
	 * @return
	 */
	public int insert(PageData pd);
}
