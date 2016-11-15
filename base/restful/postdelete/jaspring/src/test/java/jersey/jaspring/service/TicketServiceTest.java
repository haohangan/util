package jersey.jaspring.service;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import jersey.jaspring.entity.Ticket;

public class TicketServiceTest {

	TicketService service;

	@Before
	public void before() {
		service = new TicketService();
	}

	@Test
	public void testList() throws Exception {
		List<Ticket> list = service.list();
		for (Ticket t : list) {
			System.out.println(t);
		}
	}

	@Test
	public void testGet(){
		try {
			System.out.println(service.get(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsert(){
		try {
			Ticket ticket = new Ticket();
			ticket.setId(3);
			ticket.setName("JK");
			ticket.setDate(new Date());
			service.add(ticket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelete(){
		try {
			service.delete(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
