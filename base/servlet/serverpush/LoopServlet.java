package nginx.serverpush.servlet.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="loop",urlPatterns="/loop")
public class LoopServlet extends HttpServlet {
	private static final long serialVersionUID = -156692852673139318L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletOutputStream out = resp.getOutputStream();
		resp.setContentType("multipart/x-mixed-replace;boundary=End");
		out.println();
		out.println("--End");
		int i = 0;
		while (i < 1000) {
			out.println("Content-Type:text/plain");
			out.println();
			i++;
		   System.out.println("输出："+i);
			out.print(i);
			out.println();
			out.println("--End");
			out.flush();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		out.close();
	}

}
