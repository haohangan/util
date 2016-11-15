package jersey.jaspring.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import jersey.jaspring.entity.Ticket;

public interface TicketDao {
	
//	@SqlQuery("select * from ticket")
//    public List<Ticket> list();
	
//	@SqlQuery("select * from ticket where id = :id")
//    public Ticket get(@Bind("id")int id);
	
	@SqlUpdate("insert into ticket (id,name,date) values(:id,:name,:date)")
	public void add(@BindBean Ticket ticket);//() int id,String name,Date date
	
	@SqlUpdate("delete from ticket where id = :id")
    public void delete(@Bind("id")int id);
	
	void close();
}
