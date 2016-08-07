package cn.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.dao.HotDao;
import cn.springmvc.interceptor.Page;
import cn.springmvc.util.PageData;


@Service
public class HotService{

	@Autowired
	private HotDao hotDao;
	
	
	public int selectHotListCount(Page page) throws Exception {
		// TODO Auto-generated method stub
		return hotDao.selectHotListCount(page);
	}
	
	public List<PageData> selectHotListPage(Page page) throws Exception {
		// TODO Auto-generated method stub
		return hotDao.selectHotListPage(page);
	}
}
