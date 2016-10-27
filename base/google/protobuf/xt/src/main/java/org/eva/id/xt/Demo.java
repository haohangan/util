package org.eva.id.xt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Demo {
	public static void main(String[] args) throws IOException {
		read();
	}

	private static void write() throws FileNotFoundException, IOException {
		GHProtos.gh.Builder builder = GHProtos.gh.newBuilder().setName("柜号").setId(9761).setMail("9761@qq.com");
		GHProtos.gh gh1 = builder.build();
		FileOutputStream output = new FileOutputStream("D://a.data");
		gh1.writeTo(output);
		output.close();
	}
	
	private static void read() throws FileNotFoundException, IOException {
		InputStream fis = new FileInputStream("D://a.data");
		GHProtos.gh gh2 = GHProtos.gh.parseFrom(fis);
		fis.close();
		System.out.println(gh2);
	}
}
