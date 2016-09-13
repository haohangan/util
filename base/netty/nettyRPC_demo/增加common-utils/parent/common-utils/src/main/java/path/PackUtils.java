package path;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.FileUtils;

import log.CommonLogger;

/**
 * 获取某package下的所有的类
 * 
 * @author Administrator
 *
 */
public class PackUtils {
	public static List<Class<?>> getClassFromPackage(String packageName) throws IOException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replace(POINT, SEP);
		URL url = loader.getResource(packagePath);
		List<Class<?>> fileNames = null;
		if (url != null) {
			String type = url.getProtocol();
			if (type.equals("file")) {
				fileNames = getClassNameByFile(url.getPath());
			} else if (type.equals("jar")) {
				try {
					fileNames = getClassNameByJar(url);
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		}
		// if (fileNames != null && fileNames.size() > 0) {
		// return fileNames.toArray(new Class<?>[fileNames.size()]);
		// }
		return fileNames;
	}

	static final String POINT = ".";
	static final String SEP = "/";
	static final String class_Str = "class";

	/**
	 * 读取文件夹下的class文件
	 * 
	 * @param path
	 * @return
	 */
	static List<Class<?>> getClassNameByFile(String path) {
		List<Class<?>> list = new ArrayList<>();
		File dir = new File(path);
		Collection<File> cf = FileUtils.listFiles(dir, new String[] { class_Str }, true);
		Iterator<File> it = cf.iterator();
		try {
			while (it.hasNext()) {
				File f = it.next();
				String rel = path.substring(path.indexOf(class_Str) + 8).replaceAll(SEP, POINT);
				Class<?> clazz = Class.forName(rel + POINT + f.getName().substring(0, f.getName().length() - 6));
				list.add(clazz);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	static final String JAR_SPE = ".jar!/";

	/**
	 * 读取jar中的class文件
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	static List<Class<?>> getClassNameByJar(URL path) throws IOException, URISyntaxException {
		List<Class<?>> list = new ArrayList<>();
		String[] arr = path.getPath().split(JAR_SPE);
		JarFile file = ((JarURLConnection) path.openConnection()).getJarFile();
		Enumeration<JarEntry> enums = file.entries();
		try {
			while (enums.hasMoreElements()) {
				JarEntry e = enums.nextElement();
				String name = e.getName();
				if (name.contains(arr[1])) {
					if (name.contains(class_Str)) {
						CommonLogger.info("load impl clss:" + name);
						String className = name.replaceAll(SEP, POINT).substring(0, name.length() - 6);
						Class<?> clazz = Class.forName(className);
						list.add(clazz);
					}
				}
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return list;
	}

}
