package com.com.bean;

import java.util.Objects;
import java.util.concurrent.atomic.LongAdder;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;

public class BookManager {
	private static volatile BookManager instance;
	private static LongAdder checkedOut = new LongAdder();
	private static CacheAccess<String,BookVO> bookCache;
	private static final String PREFIX= "book_";
	private ImitateDao dao = new ImitateDao();
	
	private BookManager() {
		bookCache = JCS.getInstance("bookCache");
	}
	
	public static BookManager getInstance() {
		if(instance==null) {
			synchronized(BookManager.class) {
				if(instance==null) {
					instance = new BookManager();
				}
			}
		}
		checkedOut.increment();
		return instance;
	}
	
	
	public BookVO getBook(Integer id) {
		return getBook(id,true);
	}
	
	
	public BookVO getBook(Integer id,boolean fromCache) {
		BookVO vo = null;
		if(fromCache) {
			vo = bookCache.get(PREFIX+id);
		}
		if(Objects.isNull(vo)) {
			vo = loadById(id);
		}
		return vo;
	}
	
	
	private BookVO loadById(Integer id) {
		BookVO vo =null;
		try {
			vo =  dao.get(id);
			bookCache.put(PREFIX + id, vo);
		}catch(Exception e) {
			//do nothing
			bookCache.put(PREFIX + id, null);
		}
		return vo;
	}
	
}
