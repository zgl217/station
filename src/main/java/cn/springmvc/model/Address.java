package cn.springmvc.model;


/**
 * 地址库表
 * @author zgl
 * 2014年2月11日
 */
public class Address {

	private String id;
	private String area;
	private String address;
	private String bdLng;
	private String bgLat;
	private String gcLng;
	private String gcLat;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBdLng() {
		return bdLng;
	}
	public void setBdLng(String bdLng) {
		this.bdLng = bdLng;
	}
	public String getBgLat() {
		return bgLat;
	}
	public void setBgLat(String bgLat) {
		this.bgLat = bgLat;
	}
	public String getGcLng() {
		return gcLng;
	}
	public void setGcLng(String gcLng) {
		this.gcLng = gcLng;
	}
	public String getGcLat() {
		return gcLat;
	}
	public void setGcLat(String gcLat) {
		this.gcLat = gcLat;
	}
	
}
