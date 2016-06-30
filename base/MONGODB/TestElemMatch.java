
package com.grgbanking.aptoto.config.database.mongodb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.grgbanking.aptoto.mongo.domain.DBMessage;
import com.mongodb.WriteResult;

public class TestElemMatch {
    public static void main(String[] args) {
    	ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfig.class);
		System.out.println(ctx);
		MongoTemplate template = ctx.getBean(MongoTemplate.class);
    	
//    	testElemMatchMethod(template);
    	
		testQueryOne(template);
	}



	private static void testQueryOne(MongoTemplate template) {
		String chatId = "36c9377a-2679-428d-b7f2-26a0b9434052";
    	String msgId = "93ac6bf8-7005-4354-95de-7f515d4e1885";
		Query query = new Query();
		query.addCriteria(new Criteria("_id").is(chatId));
		Criteria msgIdCrit = Criteria.where("msgId").is(msgId);
		query.addCriteria(Criteria.where("details").elemMatch(msgIdCrit));
		query.addCriteria(new Criteria("details.$").is(1));
//		query.limit(1);//
		System.out.println(query);
//		query.addCriteria(new Criteria("details.$").is(1));
		DBMessage dbm = template.findOne(query, DBMessage.class);
		System.out.println("dbm:"+dbm);
//		Test.printlist(dbm.getDetails());
	}
    
    

	@SuppressWarnings("unused")
	private static void testElemMatchMethod(MongoTemplate template) {
		String chatId = "36c9377a-2679-428d-b7f2-26a0b9434052";
    	String msgId = "93ac6bf8-7005-4354-95de-7f515d4e1885";
    	String from = "100";
    	String status = "0";
    	Query query = new Query();
		query.addCriteria(new Criteria("_id").is(chatId));
		Criteria msgIdCrit = Criteria.where("msgId").is(msgId);
		Criteria fromCrit = Criteria.where("status").is(from);
		query.addCriteria(Criteria.where("details").elemMatch(msgIdCrit).elemMatch(fromCrit));
		Update update = new Update();
		update.set("details.$.status", status);
		WriteResult wr = template.updateFirst(query, update, DBMessage.class);
		System.out.println(wr.getN());
	}
}
