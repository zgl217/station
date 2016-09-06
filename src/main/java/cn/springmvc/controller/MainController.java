package cn.springmvc.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.springmvc.interceptor.Page;
import cn.springmvc.service.AddressService;
import cn.springmvc.service.HotService;
import cn.springmvc.service.TransService;
import cn.springmvc.service.UserService;
import cn.springmvc.util.EasyUIQueryResult;
import cn.springmvc.util.HandleMessyCode;
import cn.springmvc.util.PageData;
import cn.springmvc.util.ReturnCode;
@Controller
@RequestMapping("/")
public class MainController {

	/** 日志对象*/  
    private Log logger = LogFactory.getLog(this.getClass());
    
    /** 上传目录名*/  
    private static final String uploadFolderName = "uploadFiles";  
  
    /** 允许上传的扩展名*/  
    private static final String [] extensionPermit = {"xls", "xlsx"}; 
	
	@Autowired
	UserService userService;
	@Autowired
	HotService hotService;
	@Autowired
	AddressService addressService;
	@Autowired
	TransService transService;
	
	@RequestMapping(value={"","/","/index"})
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//1、收集参数//2、绑定参数到命令对象//3、调用业务对象//4、选择下一个页面
		ModelAndView mv = new ModelAndView();
		//添加模型数据 可以是任意的POJO对象
		mv.addObject("message", "Hello World!");
		//设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
		mv.setViewName("layout/index");
		return mv;
	}
	
	@RequestMapping(value={"/userList"})
	public ModelAndView userList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//1、收集参数//2、绑定参数到命令对象//3、调用业务对象//4、选择下一个页面
		ModelAndView mv = new ModelAndView();
		mv.setViewName("content/test");
		return mv;
	}
	/**
	 *user查询
	 */
	@RequestMapping(value={"/getUserList"})
	@ResponseBody
	public EasyUIQueryResult<PageData> getUserList(
			HttpServletRequest req, 
			HttpServletResponse resp,
			@RequestParam(value = "page", defaultValue = "1") int pageNo,
			@RequestParam(value = "rows", defaultValue = "10") int pageSize)  {
		
		Page page = new Page(pageNo,pageSize,new PageData(req));
		EasyUIQueryResult<PageData> result = new EasyUIQueryResult<PageData>(ReturnCode.SUCCESS);
		try {
			int total = userService.selectUserListCount(page);
			List<PageData> list = null;
			if(total>0){
				list = userService.selectUserListPage(page);
			}
			result.setReturnCode(ReturnCode.SUCCESS);
			result.setTotal(total);
			result.setRows(list);
			result.setMessage("处理成功!");
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.EXCEPTION);
			result.setMessage("处理失败!");
		}
		return result;
	}
	/**
	 *跳转到关联热点页面
	 */
	@RequestMapping(value={"/hot"})
	public ModelAndView community(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//1、收集参数//2、绑定参数到命令对象//3、调用业务对象//4、选择下一个页面
		ModelAndView mv = new ModelAndView();
		mv.setViewName("content/hot");
		return mv;
	}
	/**
	 *关联热点查询
	 */
	@RequestMapping(value={"/getHot"})
	@ResponseBody
	public EasyUIQueryResult<PageData> getCommunity(
			HttpServletRequest req, 
			HttpServletResponse resp,
			@RequestParam(value = "page", defaultValue = "1") int pageNo,
			@RequestParam(value = "rows", defaultValue = "10") int pageSize)  {
		
		Page page = new Page(pageNo,pageSize,new PageData(req));
		EasyUIQueryResult<PageData> result = new EasyUIQueryResult<PageData>(ReturnCode.SUCCESS);
		try {
			int total = hotService.selectListCount(page);
			List<PageData> list = new ArrayList<PageData>();
			if(total>0){
				list = hotService.selectListPage(page);
			}
			result.setReturnCode(ReturnCode.SUCCESS);
			result.setTotal(total);
			result.setRows(list);
			result.setMessage("处理成功!");
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.EXCEPTION);
			result.setMessage("处理失败!");
		}
		return result;
	}
	/**
	 *附近基站查询
	 */
	@RequestMapping(value={"/trans"})
	public ModelAndView trans(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//1、收集参数//2、绑定参数到命令对象//3、调用业务对象//4、选择下一个页面
		ModelAndView mv = new ModelAndView();
		mv.addObject("address", HandleMessyCode.handle(req.getParameter("address")));
		mv.setViewName("content/trans");
		return mv;
	}
	/**
	 *附近基站查询
	 */
	@RequestMapping(value={"/getTrans"})
	@ResponseBody
	public EasyUIQueryResult<PageData> getTrans(
			HttpServletRequest req, 
			HttpServletResponse resp,
			@RequestParam(value = "page", defaultValue = "1") int pageNo,
			@RequestParam(value = "rows", defaultValue = "10") int pageSize)  {
		
		Page page = new Page(pageNo,pageSize,new PageData(req));
		EasyUIQueryResult<PageData> result = new EasyUIQueryResult<PageData>(ReturnCode.SUCCESS);
		try {
			int total = 50;
			List<PageData> list = null;
			if(total>0){
				list = transService.selectListPage(page);
			}
			result.setReturnCode(ReturnCode.SUCCESS);
			result.setTotal(total);
			result.setRows(list);
			result.setMessage("处理成功!");
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.EXCEPTION);
			result.setMessage("处理失败!");
		}
		return result;
	}
	/**
	 *跳转到地址库录入页面
	 */
	@RequestMapping(value={"/address"})
	public ModelAndView address(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//1、收集参数//2、绑定参数到命令对象//3、调用业务对象//4、选择下一个页面
		ModelAndView mv = new ModelAndView();
		mv.setViewName("content/address");
		return mv;
	}
	/**
	 *地址库录入查询
	 */
	@RequestMapping(value={"/getAddress"})
	@ResponseBody
	public EasyUIQueryResult<PageData> getAddress(
			HttpServletRequest req, 
			HttpServletResponse resp,
			@RequestParam(value = "page", defaultValue = "1") int pageNo,
			@RequestParam(value = "rows", defaultValue = "10") int pageSize)  {
		
		Page page = new Page(pageNo,pageSize,new PageData(req));
		EasyUIQueryResult<PageData> result = new EasyUIQueryResult<PageData>(ReturnCode.SUCCESS);
		try {
			int total = addressService.selectListCount(page);
			List<PageData> list = new ArrayList<PageData>();
			if(total>0){
				list = addressService.selectListPage(page);
			}
			result.setReturnCode(ReturnCode.SUCCESS);
			result.setTotal(total);
			result.setRows(list);
			result.setMessage("处理成功!");
		} catch (Exception e) {
			result.setReturnCode(ReturnCode.EXCEPTION);
			result.setMessage("处理失败!");
		}
		return result;
	}
	/**
	 *地址库录入
	 */
	@RequestMapping(value={"/saveAddress"})
	@ResponseBody
	public Map<String, Object> saveAddress(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//返回值
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			//参数
			req.setCharacterEncoding("UTF-8");
			//返回对象
			addressService.insert(new PageData(req));
			
			result.put("returnCode", "SUCCESS");
			result.put("message", "查询成功");
		}catch(Exception e){
			result.put("returnCode", "ERROR");
			result.put("message", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	/**
	 *跳转到数据上传页面
	 */
	@RequestMapping(value={"/data"})
	public ModelAndView data(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//1、收集参数//2、绑定参数到命令对象//3、调用业务对象//4、选择下一个页面
		ModelAndView mv = new ModelAndView();
		mv.setViewName("content/data");
		return mv;
	}
	@RequestMapping(value={"/imgOnline"})
	@ResponseBody
    public Map<String,Object> imageOnlie(HttpServletResponse response,
    		HttpServletRequest request,
    		@RequestParam(value="fileid",defaultValue="") String fileid,
    		@RequestParam(value="myFiles") MultipartFile file){
        Map<String,Object> result=new HashMap<String, Object>();
        String path="";
        try {
        	logger.info("UploadController#fileUpload() start");  
        	  
            //清除上次上传进度信息  
            String curProjectPath = request.getSession().getServletContext().getRealPath("/");
            String saveDirectoryPath = curProjectPath + "/" + uploadFolderName;  
            File saveDirectory = new File(saveDirectoryPath);
            if(!saveDirectory.exists()){
            	saveDirectory.mkdir();
            }
            path = saveDirectory.getAbsolutePath();
            // 判断文件是否存在  
            if (!file.isEmpty()) {  
                
                logger.info("文件长度：" + file.getSize());
                logger.info("文件类型：" + file.getContentType());
                logger.info("文件名称：" + file.getName());
                logger.info("文件原名：" + file.getOriginalFilename());
                
                String fileName = file.getOriginalFilename(); 
                String filePathName = path+File.separator+fileName;
                //判断是否存在
                File temFile = new File(filePathName);
                if(temFile.exists()){
                	temFile.delete();
                }
                file.transferTo(new File(saveDirectory, fileName)); 
                
                //插入热点关联数据
                if(fileid.equals("hotFile"))
                	hotService.initData(filePathName);
                //插入地址库数据
                if(fileid.equals("addressFile"))
                	addressService.initData(filePathName);
                //插入lte数据
                if(fileid.equals("lteFile"))
                	transService.initLteData(filePathName);
                //插入gsm数据
                if(fileid.equals("gsmFile"))
                	transService.initGsmData(filePathName);
            }  
            result.put("path", path);
            result.put("fileId", fileid);
            logger.info("UploadController#fileUpload() end"); 
        }catch(Exception e){
        	e.getStackTrace();
        }
        return result;  
    }
}
