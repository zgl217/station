package cn.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.dao.AddressDao;
import cn.springmvc.interceptor.Page;
import cn.springmvc.util.PageData;


@Service
public class AddressService{

	@Autowired
	private AddressDao addressDao;
	
	
	public int selectListCount(Page page) throws Exception {
		// TODO Auto-generated method stub
		return addressDao.selectListCount(page);
	}
	
	public List<PageData> selectListPage(Page page) throws Exception {
		// TODO Auto-generated method stub
		return addressDao.selectListPage(page);
	}
}
