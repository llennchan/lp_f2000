package com.lp.f2000.mapper;

import java.util.List;
import java.util.Map;

import com.lp.f2000.entity.CouponCode;

public class CouponProvider {
    public String insertCouponCodes(Map map) {  
        List<CouponCode> codes = (List<CouponCode>) map.get("list");  
        StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO coupon_code ");  
        sb.append("(code, coupon_id) ");  
        sb.append("VALUES ");  
        
        String str = null;
        for (int i = 0; i < codes.size(); i++) {  
        	str = "('" + codes.get(i).getCode()  + "'," + codes.get(i).getCouponId() + ")";
            sb.append(str);  
            if (i < codes.size() - 1) {  
                sb.append(",");  
            }  
        }  
        return sb.toString();  
    }  
}
