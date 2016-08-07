package cn.springmvc.model;


/**
 * 热点关联表
 * @author zgl
 * 2014年2月11日
 */
public class Hot {

	private String id;
	private String area;
	private String complain;
	private String solution;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getComplain() {
		return complain;
	}
	public void setComplain(String complain) {
		this.complain = complain;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	
	
}
