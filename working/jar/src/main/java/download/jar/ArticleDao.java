package download.jar;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public class ArticleDao {
	private DBI dbi;

	public ArticleDao() {
		dbi = DBUtils.getDBI();
	}

	public Article findById(long id) {
		// h.execute("create table something (id int primary key, name
		// varchar(100))");
		// h.execute("insert into something (id, name) values (?, ?)", 1,
		// "Brian");
		try (Handle h = dbi.open();) {
			Article a = h.createQuery("select * from ES_ARTICLE  where id = :id").bind("id", id).map(Article.class)
					.first();
			return a;
		}
	}
	
	
	public static void main(String[] args) {
		ArticleDao ad = new ArticleDao();
		System.out.println(ad.findById(1));
	}
}
