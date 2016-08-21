package cn.springmvc.model;

/**   
 * 类描述：   坐标点实体
 * 项目名称：station   
 * 类名称：Point   
 * 创建人：zhugl  
 * 创建时间：2016年7月5日 下午5:46:41    
 * @version 1.0       
 */
public class Point {
	//维度
	private double lat;
	//经度
	private double lng;
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	
	
}
