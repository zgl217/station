package cn.springmvc.util;

import java.util.Comparator;

public class MapComparator implements Comparator<PageData> {
	 
    @Override
    public int compare(PageData o1, PageData o2) {
        // TODO Auto-generated method stub
        long b1 = Long.parseLong((String) o1.get("distance"));
        long b2 = Long.parseLong((String) o2.get("distance"));
        if (b1 > b2) 
            return 1;
        else if(b1 < b2)
        	return -1;
        else
        	return 0;
    }

}

