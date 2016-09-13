package path;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * 获取某package下的所有的类
 * 
 * @author Administrator
 *
 */
public class PackUtils {
	public static List<Class<?>> getClassFromPackage(String packageName) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replace(".", "/");
		URL url = loader.getResource(packagePath);
		List<Class<?>> fileNames = null;
		if (url != null) {
			String type = url.getProtocol();
			if (type.equals("file")) {
				fileNames = getClassNameByFile(url.getPath());
			} else if (type.equals("jar")) {
				// fileNames = getClassNameByJar(url.getPath(), childPackage);
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

}
