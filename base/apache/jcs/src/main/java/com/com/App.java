package com.com;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

import com.com.bean.BookManager;
import com.com.bean.BookVO;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	StopWatch watch = new StopWatch();
    	watch.start();
    	BookVO book = BookManager.getInstance().getBook(1);
    	System.out.println(book);
    	watch.stop();
    	System.out.println(watch.getTime(TimeUnit.SECONDS));
    	watch.reset();
    	watch.start();
    	BookVO book2 = BookManager.getInstance().getBook(1);
    	System.out.println(book2);
    	watch.stop();
    	System.out.println(watch.getTime(TimeUnit.SECONDS));
    	
    }
}
