package com.grgbanking.aptoto.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * FastDFS分为storage 和 tracker 两台机器<br>
 * tracker：用于获取文件存储位置的服务 <br>
 * storage：文件实际存放的地址<br>
 * 
 * @author GuiHao<br>
 * @author 李成业<br>
 *         ghao3@grgbanking.com <br>
 *         2016年6月27日 下午7:10:01 <br>
 * 
 */
public class FastDFSClient {
	public static final String HEAD = "http://";

	private static String port;// storage服务器上对应的的nginx 端口

	private static String config;// FastDFS 相关配置文件的位置

	private TrackerServer trackerServer;

	private StorageClient1 client;

	/**
	 * 配置
	 */
	static {
		String path = FastDFSClient.class.getClassLoader().getResource("./").getPath() + "fdfs_client.conf";
		File file = new File(path);
		if (!file.exists() || file.isDirectory()) {
			System.out.println(FastDFSClient.class.getClassLoader().getParent().getResource("./").getFile());
			System.out.println(FastDFSClient.class.getResource("/").getFile());
			path = FastDFSClient.class.getResource("/").getFile() +"fdfs_client.conf";
		}
		FastDFSClient.config(path, "90");
	}

	/**
	 * 初始化相关配置
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MyException
	 */
	public StorageClient init() throws FileNotFoundException, IOException, MyException {
		ClientGlobal.init(config);
		TrackerClient tracker = new TrackerClient();
		this.trackerServer = tracker.getConnection();
		StorageServer storageServer = null;
		this.client = new StorageClient1(trackerServer, storageServer);
		return client;
	}

	/**
	 * 上传文件，获取文件服务器返回的文件id，文件id是今后在文件服务器上获取该文件的key
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public String uploadFile(File file) throws Exception {
		NameValuePair[] metaList = new NameValuePair[1];
		metaList[0] = new NameValuePair("fileName", file.getName());
		String fileId = client.upload_file1(file.getAbsolutePath(), null, metaList);
		return fileId;
	}

	/**
	 * 
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	public String getUrlFromId(String fileId) throws Exception {
		TrackerClient tracker = new TrackerClient();
		StorageServer ss = tracker.getFetchStorage1(trackerServer, fileId);
		StringBuffer sb = new StringBuffer(HEAD);
		sb.append(ss.getInetSocketAddress().getHostString()).append(":").append(port);
		sb.append("/").append(fileId);
		return sb.toString();
	}

	/**
	 * 静态方法，方便注入的时候使用：配置 nginx服务的端口 和 fastdfs配置文件
	 * 
	 * @param config
	 * @param port
	 */
	public static void config(String config, String port) {
		FastDFSClient.config = config;
		FastDFSClient.port = port;
	}

	/**
	 * 关闭连接
	 */
	public void destroy() throws Exception {
		close();
	}

	protected void close() {
		try {
			trackerServer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * demo
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 初始化
		FastDFSClient client = new FastDFSClient();
		try {
			client.init();
		} catch (Exception e) {
			System.err.println("配置错误");
		} // 初始化结束

		// 上传文件
		// String fid = client.uploadFile(new File("D://image.png"));//返回fid
		// System.out.println(fid);//上传文件结束

		// 根据fid获取文件路径
		String url = client.getUrlFromId("group1/M00/00/00/CgEDL1dxzoyAQjotAABsuoQHXnE453.png");
		System.out.println(url);
	}
}
