package com.grgbanking.aptoto.config.database.mongodb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.grgbanking.aptoto.controller.cometd.vo.CometdMessage;
import com.grgbanking.aptoto.mongo.domain.ChatDetail;
import com.grgbanking.aptoto.mongo.domain.DBMessage;
import com.grgbanking.aptoto.mongo.domain.MessageNotice;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfig.class);
		System.out.println(ctx);
		// Query query = new Query();
		// query.addCriteria(new Criteria("_id").is("123"));
		MongoTemplate template = ctx.getBean(MongoTemplate.class);
		// List<DBMessage> list = template.find(query, DBMessage.class);
		// System.out.println(list.size());
		// System.out.println(list.get(0).getMsgId());.java
		// DBMessageService service = ctx.getBean(DBMessageService.class);
		// Query query = new Query();
		// query.addCriteria(new Criteria("user").is("xhua"));
		// DBMessage db = template.findOne(query, DBMessage.class);
		// System.out.println(db);
		// template.remove(query, "messages");
		// template.remove(query, DBMessage.class);
		 for(int i = 4;i<10000;i++){
		 template.insert(getObj(i+""));
		 }
		// ChartFile cf = new ChartFile();
		// cf.setCfId("132222");
		// cf.setDate(new Date());
		// cf.setFileName("小糖豆");
		// cf.setUserId("jack");
		// template.insert(cf);
		// MessageNotice mn = getNotice(UUID.randomUUID().toString());
		// template.insert(mn);
		// Query query = new Query();
		// query.addCriteria(new Criteria("TYPE").is("1"));
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date date1 = null;
		// Date date2 = null;
		// Query query = new Query();
		// Criteria criteria1 = null;
		// Criteria criteria2 = null;
		// try {
		// date1 = sdf.parse("2015-12-12 12:12:12");
		// date2 = sdf.parse("2017-12-12 12:12:12");
		//// criteria1 = new Criteria("CREATE_TIME").lte();
		//// criteria2 = new Criteria("CREATE_TIME").gte();
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// criteria1.andOperator(criteria2);
		//
		// query.addCriteria(criteria1);
		// query.addCriteria(criteria2);

		// BasicDBObject qobj = new BasicDBObject("fieldName",
		// new BasicDBObject("$gte",date1).append("$lt",date2 ));
		// query.addCriteria(qobj);
		// query.addCriteria(Criteria.where("CREATE_TIME").gte(date1).lte(date2));
		// List<MessageNotice> list = template.find(query, MessageNotice.class);
		// for(MessageNotice mv:list){
		// System.out.println(mv);
		// }
		// DBMessage dm = getObj(UUID.randomUUID().toString());
		// template.insert(dm);
		// String chatid = "";
		// Query query = new Query();
		//
		// List<MessageNotice> list = template.find(query, MessageNotice.class);
		// printlist(list);

		// insertOneWitTwo(template);//z增加一条记录

//		Query query = new Query();
//		query.addCriteria(new Criteria("_id").is("36c9377a-2679-428d-b7f2-26a0b9434052"));
//		query.addCriteria(new Criteria("details.msgId").is("93ac6bf8-7005-4354-95de-7f515d4e1885"));
//		query.addCriteria(new Criteria("details.status").is("0"));
		// query.addCriteria(new
		// Criteria("details.msgId").is("93ac6bf8-7005-4354-95de-7f515d4e1885"));
//		query.addCriteria();
//		Where
		
//		System.out.println(template.findOne(query, DBMessage.class));
//		Update update = new Update();
		// update.set("details.status", "3");
		// update.update("details.status", "3");
//		update.set("details.$.status", "1");
//		template.updateFirst(query, update, DBMessage.class);

		////////////////////////////////////////////////////////
//		QueryBuilder queryBuilder = new QueryBuilder();
//		queryBuilder.all(new BasicDBObject("bussId","XMMXMXMXMX"));
		/*BasicDBObject q = new BasicDBObject("bussId","XMMXMXMXMX");
		BasicDBObject bo = new BasicDBObject();
		bo.put("details", false);
		Query query = new BasicQuery(q, bo);
		DBMessage d = template.findOne(query, DBMessage.class);
		System.out.println(d.getChatId());
		System.out.println(d.getDetails());*/
		///////////////////////////////////////////////////////////
		/*Query query = new Query();
		query.addCriteria(Criteria.where("chatId").is("1"));
		query.fields().slice("details", 0, 5);
		DBMessage d = template.findOne(query, DBMessage.class);
		System.out.println(d.getDetails().size());*/
		///////////////////////////////////////////////////////////
		System.out.println("结束");
	}

	@SuppressWarnings("unused")
	private static void insertOneWitTwo(MongoTemplate template) {
		String uuid = UUID.randomUUID().toString();
		template.insert(getObj(uuid));

		CometdMessage message = new CometdMessage();

		message.setMsgId(UUID.randomUUID().toString());
		message.setUser("A");
		message.setTid("B");
		message.setContent("你好");

		CometdMessage message2 = new CometdMessage();

		message2.setMsgId(UUID.randomUUID().toString());
		message2.setUser("B");
		message2.setTid("A");
		message2.setContent("你好ok");

		List<ChatDetail> list = new ArrayList<ChatDetail>();
		list.add(message.toDetail(new Date()));
		list.add(message2.toDetail(new Date()));

		Query query = new Query();
		query.addCriteria(new Criteria("_id").is(uuid));
		Update update = new Update();
		update.pushAll("details", list.toArray());
		template.updateFirst(query, update, DBMessage.class);
	}

	public static DBMessage getObj(String id) {
		DBMessage message = new DBMessage();
		message.setChatId(id);// UUID.randomUUID().toString()
		message.setUser("xhua");
		message.setUname("小华");
		message.setTid("xhuang");
		message.setTname("小黄");
		message.setBussType("3");
		message.setBussId("XMMXMXMXMX");
		message.setDate(new Date());
		 List<ChatDetail> list = new ArrayList<>();
		 for (int i = 0; i < 10; i++) {
		 ChatDetail cd = new ChatDetail();
		 cd.setMsgId("X" + i);
		 cd.setFrom("xhua");
		 cd.setTo("xhong");
		 cd.setContent("nihao");
		 cd.setDate(new Date(System.currentTimeMillis()+1000*i));
		 cd.setStatus("1");
		 list.add(cd);
		 }
		 message.setDetails(list);
		return message;
	}

	public static MessageNotice getNotice(String id) {
		String TITLE = "小标题";
		String ODD_NUM = "单号";
		String TYPE = "类别";
		String MESSAGE = "xinxneirong ";
		String RECEIVER = "接收者";
		String SENDER = "发送者";
		MessageNotice mn = new MessageNotice(TITLE, ODD_NUM, TYPE, MESSAGE, RECEIVER, SENDER);
		return mn;
	}

	public static void printlist(List<?> list) {
		System.out.println("list.size:"+list.size());
		for (Object obj : list) {
			System.out.println(obj);
		}
	}
}
