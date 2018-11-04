package com.lp.f2000;

import java.util.HashSet;

import com.lp.f2000.util.StringUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<String> set = new HashSet<String>();
		StringUtil.randomCodeSet(200000, set);
        for (String j : set) {  
            System.out.println(j);  
        } 
        
	}

}
