package cn.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.springmvc.interceptor.Page;
import cn.springmvc.service.AddressService;
import cn.springmvc.service.HotService;
import cn.springmvc.service.UserService;
import cn.springmvc.util.EasyUIQueryResult;
import cn.springmvc.util.PageData;
import cn.springmvc.util.ReturnCode;
@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	UserService userService;
	@Autowired
	HotService hotService;
	@Autowired
	AddressService addressService;
	
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
	@RequestMapping(value={"/community"})
	public ModelAndView community(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//1、收集参数//2、绑定参数到命令对象//3、调用业务对象//4、选择下一个页面
		ModelAndView mv = new ModelAndView();
		mv.setViewName("content/community");
		return mv;
	}
	/**
	 *关联热点查询
	 */
	@RequestMapping(value={"/getCommunity"})
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
			int total = hotService.selectListCount(page);
			List<PageData> list = null;
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
			List<PageData> list = null;
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
}
