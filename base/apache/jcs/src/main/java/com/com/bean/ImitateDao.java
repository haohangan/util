package com.com.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ImitateDao {
	
	static final String[] names = {"金阁寺","潮骚","罗生门","通向奴役之路","我们生活的世界","独裁者手册","中国文化的深层结构","动物农场","1984","现代C语言"};
	
	private static List<BookVO> dataBase = new ArrayList<BookVO>();
	
	static {
		for(int i = 0;i<names.length;i++) {
			dataBase.add(new BookVO(i,names[i]));
		}
	}
	
	
	public BookVO get(Integer id) {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(Objects.isNull(id) || id.compareTo(0)<0 || id.compareTo(9)>0) {
			return null;
		}
		return dataBase.get(id);
	}
	
}
