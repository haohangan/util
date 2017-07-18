package eva.cglibtest.scan;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

//参考http://www.oschina.net/code/snippet_129830_8767
//
public class ScannerClassHelper {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String packageName = "eva.cglibtest.demo1";
		// System.out.println(ScannerClassHelper.class.getClassLoader());
		List<Class<?>> list = new ArrayList<>();
		String packageUri = packageName.replace(".", "/");
		// System.out.println(packageUri);
		Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageUri);
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			// 得到协议的名称
			String protocol = url.getProtocol();
			if ("file".equals(protocol)) {
				// 获取包的物理路径
				String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
				System.out.println(filePath);
				File file = new File(filePath);
				if (file.isDirectory()) {
					File[] files = file.listFiles();
					for (File f : files) {
						String className = f.getName().substring(0, f.getName().length() - 6);
						Class<?> clazz = Thread.currentThread().getContextClassLoader()
								.loadClass(packageName + '.' + className);
						System.out.println(clazz);
					}
				} else {
					System.out.println(file.getPath());
				}
				// 以文件的方式扫描整个包下的文件 并添加到集合中
			} else if ("jar".equals(protocol)) {

			}
		}
	}
}
