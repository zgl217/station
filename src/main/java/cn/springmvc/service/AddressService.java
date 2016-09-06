package cn.springmvc.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.dao.AddressDao;
import cn.springmvc.interceptor.Page;
import cn.springmvc.model.Point;
import cn.springmvc.util.ConvertUtil;
import cn.springmvc.util.ExcelUtil;
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
	
	public void initData(String filePathName) throws Exception{
		//读取文件数据
		File file = new File(filePathName);
		String[][] data = null;
		if(file.getName().endsWith(".xls")){
			data = ExcelUtil.getData(file, 1);
		}
		if(file.getName().endsWith(".xlsx")){
			data = ExcelUtil.getData2(file, 1);
		}
		//执行数据插入
		if(data.length>0){
			//先删除旧数据
			addressDao.delete();
		}
		for(int i=0;i<data.length;i++){
			PageData pd = new PageData();
			pd.put("area", data[i][0]);
			pd.put("address", data[i][1]);
			pd.put("bdLng", data[i][2]);
			pd.put("bdLat", data[i][3]);
			pd.put("gcLng", data[i][4]);
			pd.put("gcLat", data[i][5]);
			
			addressDao.insert(pd);
		}
	}
}
