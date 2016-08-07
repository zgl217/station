package cn.springmvc.model;

import java.sql.Date;

/**
 * 用户表
 * @author fenghaifeng
 * 2014年2月11日
 */
public class User {

	private String id;
	private String code;
	private String name;
	private String phone;
	private String age;
	private Date createTime;
	private String createCode;
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateCode() {
		return createCode;
	}
	public void setCreateCode(String createCode) {
		this.createCode = createCode;
	}
	@Override
    public String toString() {
        return this.id+","+this.name+","+this.age+","+this.phone+","+this.createTime;
    }
}
