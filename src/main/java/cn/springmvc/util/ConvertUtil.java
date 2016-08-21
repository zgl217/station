package cn.springmvc.util;

import cn.springmvc.model.Point;

/**   
 * 类描述：   
 * 项目名称：station   
 * 类名称：ConvertUtil   
 * 创建人：zhugl  
 * 创建时间：2016年7月5日 下午3:03:59    
 * @version 1.0       
 */
public class ConvertUtil {

	private static double x_pi = Math.PI * 3000.0 / 180.0;
	/// <summary>
	/// 中国正常坐标系GCJ02协议的坐标，转到 百度地图对应的 BD09 协议坐标
	/// </summary>
	/// <param name="lat">维度</param>
	/// <param name="lng">经度</param>
	public static Point Convert_GCJ02_To_BD09(	double lat, double lng)
	{
		double x = lng, y = lat;
		double z =Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
		lng = z * Math.cos(theta) + 0.0065;
		lat = z * Math.sin(theta) + 0.006;
		Point p = new Point();
		p.setLat(lat);
		p.setLng(lng);
		return p;
	}
	/// <summary>
	/// 百度地图对应的 BD09 协议坐标，转到 中国正常坐标系GCJ02协议的坐标
	/// </summary>
	/// <param name="lat">维度</param>
	/// <param name="lng">经度</param>
	public static Point Convert_BD09_To_GCJ02( double lat,  double lng){
		double x = lng - 0.0065, y = lat - 0.006;
		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
		lat = z * Math.sin(theta);
		lng = z * Math.cos(theta);
		Point p = new Point();
		p.setLat(lat);
		p.setLng(lng);
		return p;
	}
	
	public static void main(String args[]) throws Exception{
		Point p = ConvertUtil.Convert_BD09_To_GCJ02( 36.974457,120.70681);
		System.out.println(p.getLat());
		System.out.println(p.getLng());
	}
}
