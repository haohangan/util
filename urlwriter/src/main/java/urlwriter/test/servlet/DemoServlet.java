package urlwriter.test.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年3月17日 上午11:44:33 类说明
 */
@WebServlet(name = "demo", urlPatterns = "/demo.do")
public class DemoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String a = req.getParameter("a");
		String b = req.getParameter("b");
		System.out.println(a + "\t" + b);
		req.setAttribute("value", a + ":" + b);
		req.getRequestDispatcher("/login.jsp?").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
