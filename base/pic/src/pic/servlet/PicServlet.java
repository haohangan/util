package pic.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年3月30日 上午11:41:53 类说明
 */
@WebServlet(name = "pic", urlPatterns = "/pic.do", asyncSupported = true)
public class PicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String path = "D:\\img\\";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String pid = req.getParameter("pid");
		Integer i = Integer.parseInt(pid);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int picid = i % 9;
		final String imgpath = path + picid + ".jpg";
		InputStream is = new FileInputStream(new File(imgpath));
		OutputStream os = resp.getOutputStream();
		byte[] buff = new byte[1024];
		int flag = -1;
		while ((flag = is.read(buff)) != -1) {
			os.write(buff, 0, flag);
		}
		is.close();
		os.flush();
		os.close();
	}

}
