package test;

import java.util.List;

import com.eva.core.db.basedao.impl.BlogDaoImpl;
import com.eva.core.db.baseutils.DBConnection;
import com.eva.core.db.entity.Blog;

public class TestDao {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		Blog blog = new Blog();
		blog.setBid(3);
		blog.setAccess(0);
		blog.setBname("²âÊÔµÄ²©¿Í");
		BlogDaoImpl dao = new BlogDaoImpl(DBConnection.getConn());
		
		List<Blog> list = dao.query(blog);
		System.out.println("size:"+list.size());
		for(Blog b:list){
			TestHelper.printAObject(b);
		}
	}
}
//BlogDaoImpl dao = new BlogDaoImpl(DBConnection.getConn());
//dao.delete(b.getBid());
