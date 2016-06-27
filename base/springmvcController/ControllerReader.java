package com.grgbanking.aptoto;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;

public class ControllerReader {
	static final String split = "classes";
	static final String targetFileExt = "class";

	public static void main(String[] args) {
		// 便利
		// String path = "com.grgbanking.aptoto";
		// FileUtils.listf
		String basepath = ControllerReader.class.getResource("./")
				.toString()/* .replace("classes", "java") */;
		// String[] basePackageArr = basepath.split(split);
		// String basePackage = basePackageArr[1].replace("/", ".");
		System.out.println(basepath.substring(6));
		File parent = new File(basepath.substring(6));
		System.out.println(parent.isDirectory());
		Collection<File> files = FileUtils.listFiles(parent, new String[] { targetFileExt }, true);
		Iterator<File> fs = files.iterator();
		while (fs.hasNext()) {
			File f = fs.next();
			String filePath = f.getPath();
			String[] arr = filePath.split(split);
			if (arr == null || arr.length < 2) {
				continue;
			}
			String classPath = filePath.split(split)[1].replaceAll("\\\\", ".");
			classPath = classPath.substring(1);
			classPath = classPath.substring(0, classPath.length() - 6);
			// System.out.println(classPath);
			Class<?> clazz = null;
			try {
				clazz = Class.forName(classPath);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if (clazz != null) {
				
				requestMappingAnno(clazz);
				
				Method[] ms = clazz.getDeclaredMethods();
				for(Method m:ms){
					
					System.out.println(m);
				}
			}
		}
	}

	private static void requestMappingAnno(Class<?> clazz) {
		Annotation[] annos = clazz.getAnnotations();
		for (Annotation anno : annos) {
//					if (anno.annotationType().equals(Controller.class)) {
//						Controller C =clazz.getAnnotation(Controller.class);
//						System.out.println(C);
//					}
			if (anno.annotationType().equals(RequestMapping.class)) {
				RequestMapping C =clazz.getAnnotation(RequestMapping.class);
				System.out.print("\t[");
				for(String s:C.value()){
					System.out.print(s+"\t");
				}
				System.out.print("]\t");
				
			}
			
			System.out.println();
		}
	}
}
