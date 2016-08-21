package cn.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.dao.AddressDao;
import cn.springmvc.interceptor.Page;
import cn.springmvc.model.Point;
import cn.springmvc.util.ConvertUtil;
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
	
	public boolean insert(PageData pd)throws Exception {
		boolean result = false;
		//根据百度经纬度获取谷歌经纬度
		String bdjw = pd.getString("bdjw");
		
		String bd_lng = bdjw.split(",")[0];
		String bd_lat = bdjw.split(",")[1];
		
		Point p = ConvertUtil.Convert_BD09_To_GCJ02(Double.parseDouble(bd_lat), Double.parseDouble(bd_lng));
		
		String gc_lng = String.valueOf(p.getLng());
		String gc_lat = String.valueOf(p.getLat());
		
		pd.put("bdLng", bd_lng);
		pd.put("bdLat", bd_lat);
		pd.put("gcLng", gc_lng);
		pd.put("gcLat", gc_lat);
		
		//插入address记录
		int num = addressDao.insert(pd);
		if(num>0)
			result = true;
		return result;
	}
}
