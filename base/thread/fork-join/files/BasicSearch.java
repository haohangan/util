package algorithm.files;

import java.io.File;

public class BasicSearch {

	public static FileAttr cal(File file) {
		FileAttr fa = new FileAttr();
		if (file.isFile()) {
			fa.setFileNum(1);
			fa.setSize(file.length());
			return fa;
		}
		File[] fs = file.listFiles();
		if (fs != null) {
			for (File f : fs) {
				fa = add(fa, cal(f));
			}
		}
		return fa;
	}

	static class FileAttr {
		private int fileNum;
		private long size;

		public int getFileNum() {
			return fileNum;
		}

		public void setFileNum(int fileNum) {
			this.fileNum = fileNum;
		}

		public long getSize() {
			return size;
		}

		public void setSize(long size) {
			this.size = size;
		}
	}

	static FileAttr add(FileAttr fa1, FileAttr fa2) {
		FileAttr f = new FileAttr();
		f.setFileNum(fa1.getFileNum() + fa2.getFileNum());
		f.setSize(fa1.getSize() + fa2.getSize());
		return f;
	}

	public static void main(String[] args) {
		FileAttr f = cal(new File(SizeCounter.path));
		System.out.println(f.getFileNum());
		System.out.println(f.getSize());
		// 334
		// 173945233408

	}
}
