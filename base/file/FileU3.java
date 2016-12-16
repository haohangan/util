package fileu;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

/**
 * @author guihao
 * @date 2016年12月14日上午11:33:59
 * @desc
 */
public class FileU3 {
	static String Baseuri = "D:\\db\\sql\\";

	public static void main(String[] args) throws IOException {
		// in memory
		// RandomAccessFile raf = new
		// RandomAccessFile(Paths.get("D://a.txt").toFile(), "r");
//		divide("D://", "a.txt", 2);
		divide(Baseuri, "gv_ticket_report.sql", 8);
	}

	public static void divide(String baseUri, String fileName, int num) throws IOException {
		Path path = Paths.get(baseUri, fileName);
		long total = Files.size(path);
		int size = (int) (total / num);
		int realSize = size;
		Stopwatch watch = Stopwatch.createStarted();
		RandomAccessFile raf = new RandomAccessFile(path.toFile(), "rw");
		int start = 0;
		Files.createDirectories(Paths.get(baseUri + "\\new\\"));
		for (int i = 0; i < num; i++) {
			start = i * realSize;
			realSize = getEnd(raf, start + size);
			byte[] bytes = readFromPosition(raf, start, realSize);
			String newFileName = newName(fileName, i);
			Path newFilePath = Paths.get(baseUri + "\\new\\", newFileName);
			Files.createFile(newFilePath);
			writeToFile(newFilePath, bytes);
		}
		raf.close();
		watch.stop();
		System.out.println("耗时：" + watch.elapsed(TimeUnit.SECONDS));
	}

	/**
	 * 分割后的新名称
	 * 
	 * @param name
	 * @param i
	 * @return
	 */
	static String newName(String name, int i) {
		if (!name.contains(".")) {
			return name + "_" + i;
		}
		int point = name.lastIndexOf(".");
		return name.substring(0, point) + "_" + i + name.substring(point);
	}

	static int getEnd(RandomAccessFile raf, int end) throws IOException {
		if(end == -1){
			return -1;
		}
		long length = raf.length();
		while (end < length) {
			raf.seek(end);
			switch (raf.read()) {
			case -1:
				break;
			case '\n':
				return -2;
			case '\r':
				if (raf.read() != '\n') {

				} else {
					long cur3 = raf.getFilePointer();
					return (int) cur3;
				}
			default:
			}
			end++;
		}
		return -1;
	}

	/**
	 * 考虑读取到文件末尾的情况
	 * 
	 * @param raf
	 * @param start
	 * @param size
	 * @return
	 * @throws IOException
	 */
	static byte[] readFromPosition(RandomAccessFile raf, int start, int size) throws IOException {
		long total = raf.length();
		byte[] bytes = null;
		if (((start + size) > total) || (size == -1)) {
			bytes = new byte[(int) (total - start)];
		} else {
			bytes = new byte[size];
		}
		raf.seek(start);
		raf.read(bytes);
		return bytes;
	}

	static void writeToFile(Path newFilePath, byte[] bytes) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(newFilePath.toFile(), "rw");
		raf.seek(0);
		raf.write(bytes);
		raf.close();
	}

	@Deprecated
	static void print(List<String> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		System.out.println("start to print__________");
		for (String s : list) {
			System.out.println(s);
		}
		System.out.println("end print__________");
	}
}
