package com.atguigu.junit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestForeach {

	@Test
	public void test() {
		
		String str = new String();//这里赋值为null会报空指针异常
		List<String> list = new ArrayList<String>();
		list.add(str);
		for (String string : list) {
			System.out.println(string);
			System.out.println(string.isEmpty());
		}
		
	}

}
