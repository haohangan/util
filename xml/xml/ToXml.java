package xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import bean.Kid;
import bean.Location;
import bean.UserInfo;

import com.thoughtworks.xstream.XStream;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version ����ʱ�䣺2015��11��13�� ����11:27:38
 * ��˵��
 */
public class ToXml {
    public static void main(String[] args) throws IOException {
    	UserInfo ui = UserInfo.createUser();
		XStream xs = new XStream();
		xs.alias("user", UserInfo.class);
		xs.aliasAttribute("uid", "12");
//		xs.aliasAttribute(UserInfo.class, "userId", "uid");
		xs.alias("children", Kid.class);
		xs.alias("Location", Location.class);
		String xml = xs.toXML(ui);
		System.out.println(xml);
		OutputStream output = new FileOutputStream(new File("D://a.xml"));
		IOUtils.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"+xml, output, "UTF-8");
	}
    
    public void test(){
    }
}
