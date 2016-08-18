package cn.springmvc.model;


/**
 * gsm表
 * @author zgl
 * 2014年2月11日
 */
public class Gsm {

	private String id;
	private String cell;
	private String cellName;
	private String area;
	private String location;
	private String gcLng;
	private String gcLat;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
