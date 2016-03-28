package com.common.util;

import java.io.File;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年2月26日 下午3:07:59
 * 类说明
 */
public class FileCleanUtil {
    public static void main(String[] args) {
		String url = "D:\\m2\\repository";//清空文件夹下的某个后缀文件
		String[] extensions = {"lastUpdated"};
		Iterator<File> it = FileUtils.iterateFiles(new File(url), extensions, true);
		while(it.hasNext()){
			File f = it.next();
			FileUtils.deleteQuietly(f);
			System.out.println(f.getName());
		}
	}
}
