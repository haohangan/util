package qn.qiniu.opt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import qn.qiniu.config.ConfigAuth;

public class DownloadUtils {
	public String picName;
	
    public void download(){
    	System.out.println(ConfigAuth.createPrivateUrl(picName));
    }
    
    public static void main(String[] args) {
    	DownloadUtils du = new DownloadUtils();
    	du.picName = "1.gif";
    	du.download();
	}
    
    public static void download(String url,String filePath){
    	try {
			IOUtils.copy(new URL(url).openStream(), new FileOutputStream(new File(filePath)));
		} catch (IOException e) {
		}
    }
}
