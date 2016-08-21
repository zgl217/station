package cn.springmvc.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.springmvc.dao.GsmDao;
import cn.springmvc.dao.LteDao;
import cn.springmvc.interceptor.Page;
import cn.springmvc.model.Point;
import cn.springmvc.util.ConvertUtil;
import cn.springmvc.util.DistanceUtil;
import cn.springmvc.util.MapComparator;
import cn.springmvc.util.PageData;



@Service
public class TransService{

	@Autowired
	private LteDao lteDao;
	@Autowired
	private GsmDao gsmDao;
	
	public int selectListCount(Page page) throws Exception {
		// TODO Auto-generated method stub
//		return transDao.selectListCount(page);
		return 1;
	}
	
	public List<PageData> selectListPage(Page page) throws Exception {
		// TODO Auto-generated method stub
//		return transDao.selectListPage(page);
		//返回值
		List<PageData> result = new ArrayList<PageData>();
		String address = page.getPd().getString("address");
		//构造投诉点
		Point p = new Point();
		String bdLng = page.getPd().getString("bdLng");
		String bdLat = page.getPd().getString("bdLat");
		String gcLng = page.getPd().getString("gcLng");
		String gcLat = page.getPd().getString("gcLat");
		if(StringUtils.isEmpty(gcLng)||StringUtils.isEmpty(gcLat)){
			p = ConvertUtil.Convert_BD09_To_GCJ02(Double.parseDouble(bdLat), Double.parseDouble(bdLng));
		}else{
			p.setLat(Double.parseDouble(gcLat));
			p.setLng(Double.parseDouble(gcLng));
		}
		
		//查询最近的4g投诉点
		Page ltePage = new Page(1,999999);
		List<PageData> temp_lteList = new ArrayList<PageData>();
		List<PageData> lteList = lteDao.selectListPage(ltePage);
		for(int i=0;i<lteList.size();i++){
			String cell = lteList.get(i).getString("cell_name");
			String lat = lteList.get(i).getString("gc_lat");//纬度
			String lng = lteList.get(i).getString("gc_lng");//经度
			String distance = DistanceUtil.getDistance(String.valueOf(p.getLat()), String.valueOf(p.getLng()), lat, lng);
			PageData temp = new PageData();
			temp.put("cell", cell);
			temp.put("distance", distance);
			temp_lteList.add(temp);
		}
		//lte排序
		Collections.sort(temp_lteList, new MapComparator());
		
		//查询最近的2g投诉点
		Page gsmPage = new Page(1,999999);
		List<PageData> temp_gsmList = new ArrayList<PageData>();
		List<PageData> gsmList = gsmDao.selectListPage(gsmPage);
		for(int i=0;i<gsmList.size();i++){
			String cell = gsmList.get(i).getString("cell");
			String lat = gsmList.get(i).getString("gc_lat");//纬度
			String lng = gsmList.get(i).getString("gc_lng");//经度
			String distance = DistanceUtil.getDistance(String.valueOf(p.getLat()), String.valueOf(p.getLng()), lat, lng);
			PageData temp = new PageData();
			temp.put("cell", cell);
			temp.put("distance", distance);
			temp_gsmList.add(temp);
		}
		//gsm排序
		Collections.sort(temp_gsmList, new MapComparator());
		
		//top50数据
		
		for(int i=0;i<50;i++){
			PageData temp = new PageData();
			temp.put("complain", address);
			if(temp_lteList.size()>i){
				temp.put("lteCell", temp_lteList.get(i).get("cell"));
				temp.put("lteDistance", temp_lteList.get(i).get("distance"));
			}
			if(temp_gsmList.size()>i){
				temp.put("gsmCell", temp_gsmList.get(i).get("cell"));
				temp.put("gsmDistance", temp_gsmList.get(i).get("distance"));
			}
			result.add(temp);
		}
		return result;
	}
}
