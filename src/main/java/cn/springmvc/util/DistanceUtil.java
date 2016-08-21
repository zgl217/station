package cn.springmvc.util;

import java.util.HashMap;
import java.util.Map;

/**   
 * 类描述：   计算经纬度两点之间的距离
 * 项目名称：station   
 * 类名称：DistanceUtil   
 * 创建人：zhugl  
 * 创建时间：2016年7月6日 上午8:07:58    
 * @version 1.0       
 */
public class DistanceUtil { 
    
    private static double EARTH_RADIUS = 6378.137; //地球赤道半径
   
    private static double rad(double d) { 
        return d * Math.PI / 180.0; 
    }
     
    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为M）
     * 参数为String类型
     * @param lat1 用户纬度
     * @param lng1 用户经度
     * @param lat2 商家纬度
     * @param lng2 商家经度
     * @return
     */
    public static String getDistance(String lat1Str, String lng1Str, String lat2Str, String lng2Str) throws Exception{
    	System.out.println(lat1Str+"---"+lat2Str);
        Double lat1 = Double.parseDouble(rightTrim(lat1Str));
        Double lng1 = Double.parseDouble(rightTrim(lng1Str));
        Double lat2 = Double.parseDouble(rightTrim(lat2Str));
        Double lng2 = Double.parseDouble(rightTrim(lng2Str));
         
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)+ Math.cos(radLat1) * Math.cos(radLat2)* Math.pow(Math.sin(mdifference / 2), 2)));
        distance = Math.round(distance * EARTH_RADIUS*1000);
        
        String result = String.valueOf(distance);
        result = result.substring(0,result.indexOf("."));
        return result;
    }
     
    /**
     * 获取当前用户一定距离以内的经纬度值
     * 单位米 return minLat
     * 最小经度 minLng
     * 最小纬度 maxLat
     * 最大经度 maxLng
     * 最大纬度 minLat
     */
    public static Map getAround(String latStr, String lngStr, String raidus) {
        Map map = new HashMap();
         
        Double latitude = Double.parseDouble(latStr);// 传值给经度
        Double longitude = Double.parseDouble(lngStr);// 传值给纬度
 
        Double degree = (24901 * 1609) / 360.0; // 获取每度
        double raidusMile = Double.parseDouble(raidus);
         
        Double mpdLng = Double.parseDouble((degree * Math.cos(latitude * (Math.PI / 180))+"").replace("-", ""));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        //获取最小经度
        Double minLat = longitude - radiusLng;
        // 获取最大经度
        Double maxLat = longitude + radiusLng;
         
        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        // 获取最小纬度
        Double minLng = latitude - radiusLat;
        // 获取最大纬度
        Double maxLng = latitude + radiusLat;
         
        map.put("minLat", minLat+"");
        map.put("maxLat", maxLat+"");
        map.put("minLng", minLng+"");
        map.put("maxLng", maxLng+"");
         
        return map;
    }
    
    /**
     * 去掉字符串右边的空格
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */
     public static String rightTrim(String str) {
       if (str == null) {
           return "";
       }
       int length = str.length();
       for (int i = length - 1; i >= 0; i--) {
           if (str.charAt(i) != 0x20) {
              break;
           }
           length--;
       }
       return str.substring(0, length);
    }
     
    public static void main(String[] args) {
        //济南国际会展中心经纬度：117.11811  36.68484
        //趵突泉：117.00999000000002  36.66123
    	
    	//于祥大厦 121.430236322352 37.4621078376302
    	//121.523888  37.411666
//        System.out.println(getDistance("37.4621078376302","121.430236322352","37.411666","121.523888"));
         
//        System.out.println(getAround("117.11811", "36.68484", "13000"));
        //117.01028712333508(Double), 117.22593287666493(Double),
        //36.44829619896034(Double), 36.92138380103966(Double)
         
    }
     
}
