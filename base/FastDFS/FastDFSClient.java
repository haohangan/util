package com.grgbanking.aptoto.fastdfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.DisposableBean;

public class FastDFSClient implements DisposableBean{
	public static final String HEAD = "http://";

	private static String port;

	private static String config;

	private TrackerServer trackerServer;

	private StorageClient1 client;

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
	 * 获取文件访问路径
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public String getUrlFromId(String fileId) throws Exception {
		FileInfo fio = client.get_file_info1(fileId);
		StringBuffer sb = new StringBuffer(HEAD);
		sb.append(fio.getSourceIpAddr()).append(":").append(port);
		sb.append("/").append(fileId);
		return sb.toString();
	}

	/**
	 * 
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	public String getUrlFromId2(String fileId) throws Exception {
		TrackerClient tracker = new TrackerClient();
		StorageServer ss = tracker.getFetchStorage1(trackerServer, fileId);
		StringBuffer sb = new StringBuffer(HEAD);
		sb.append(ss.getInetSocketAddress().getHostString()).append(":").append(port);
		sb.append("/").append(fileId);
		return sb.toString();
	}
	
	public String getUrlFromId3(String fileId) throws Exception {
		FileInfo fio = client.query_file_info1(fileId);
		StringBuffer sb = new StringBuffer(HEAD);
		sb.append(fio.getSourceIpAddr()).append(":").append(port);
		sb.append("/").append(fileId);
		return sb.toString();
	}

	public void close() {
		try {
			trackerServer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void config(String config, String port) {
		FastDFSClient.config = config;
		FastDFSClient.port = port;
	}

	public static void main(String[] args) throws Exception {
		FastDFSClient.config("D:\\fdfs_client.conf", "90");
		FastDFSClient client = new FastDFSClient();
		client.init();
		// File file = new File("D:\\b.txt");
		// String fid = client.uploadFile(file);
		System.out.println(client.getUrlFromId3("group1/M00/00/00/CgEDLFdp2hqAAj6NAAAAM5PhdMs400.txt"));
		client.close();
		// System.out.println(file.getName());
		// System.out.println(file.getAbsolutePath());
	}

	@Override
	public void destroy() throws Exception {
		close();
	}

}
