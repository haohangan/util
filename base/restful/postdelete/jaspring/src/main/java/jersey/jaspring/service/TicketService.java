package jersey.jaspring.service;

import java.util.List;

import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import org.springframework.stereotype.Service;

import jersey.jaspring.common.dbutils.ConnectionUtils;
import jersey.jaspring.conf.errorhandling.CustomReasonPhraseException;
import jersey.jaspring.dao.TicketDao;
import jersey.jaspring.entity.Ticket;

@Service
public class TicketService {

	TicketDao dao;

	public List<Ticket> list() throws CustomReasonPhraseException {
		DataSource ds = ConnectionUtils.getDataSource();
		DBI dbi = new DBI(ds);
		Handle h = dbi.open();
		try {
			Query<Ticket> query = h.createQuery("select * from ticket").map(Ticket.class);
			return query.list();
		} catch (Exception e) {
			throw new CustomReasonPhraseException(1, "dao");
		} finally {
			h.close();
		}
	}

	public Ticket get(int id) throws CustomReasonPhraseException {
		DataSource ds = ConnectionUtils.getDataSource();
		DBI dbi = new DBI(ds);
		Handle h = dbi.open();
		try {
			// Query<Ticket> query = h.createQuery("select * from ticket where
			// id = ?").bind(0, id).map(Ticket.class);
			Query<Ticket> query = h.createQuery("select * from ticket where id = :id").bind("id", id).map(Ticket.class);
			return query.first();
		} finally {
			h.close();
		}
	}

	public void delete(int id) throws CustomReasonPhraseException {
		// try {
		// DataSource ds = ConnectionUtils.getDataSource();
		// DBI dbi = new DBI(ds);
		// dao = dbi.open(TicketDao.class);
		// dao.delete(id);
		// } catch (Exception e) {
		// throw new Exception(e.getMessage());
		// } finally {
		// dao.close();
		// }
		DataSource ds = ConnectionUtils.getDataSource();
		DBI dbi = new DBI(ds);
		Handle h = dbi.open();
		try {
			h.begin();
			h.execute("delete from ticket where id = ?", id);
			h.commit();
		} catch (Exception e) {
			h.rollback();
			throw new CustomReasonPhraseException(3, "删除错误");
		} finally {
			h.close();
		}
	}

	public void add(Ticket ticket) throws CustomReasonPhraseException {
		DataSource ds = ConnectionUtils.getDataSource();
		DBI dbi = new DBI(ds);
		Handle h = dbi.open();
		try {
			h.begin();
			h.execute("insert into ticket (id,name,date) values(?,?,?)", ticket.getId(), ticket.getName(),
					ticket.getDate());
			h.commit();
		} catch (Exception e) {
			h.rollback();
			throw new CustomReasonPhraseException(4, "增加错误");
		} finally {
			h.close();
		}
	}
}
