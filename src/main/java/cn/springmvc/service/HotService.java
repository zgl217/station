package cn.springmvc.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.dao.HotDao;
import cn.springmvc.interceptor.Page;
import cn.springmvc.util.ExcelUtil;
import cn.springmvc.util.PageData;


@Service
public class HotService{

	@Autowired
	private HotDao hotDao;
	
	
	public int selectListCount(Page page) throws Exception {
		// TODO Auto-generated method stub
		return hotDao.selectListCount(page);
	}
	
	public List<PageData> selectListPage(Page page) throws Exception {
		// TODO Auto-generated method stub
		return hotDao.selectListPage(page);
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
			hotDao.delete();
		}
		for(int i=0;i<data.length;i++){
			PageData pd = new PageData();
			pd.put("area", data[i][0]);
			pd.put("complain", data[i][1]);
			pd.put("solution", data[i][2]);
			hotDao.insert(pd);
		}
	}
}
