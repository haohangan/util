package com.grgbanking.aptoto.config.database.mongodb;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.grgbanking.aptoto.mongo.domain.DBMessage;

public class Test88 {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfig.class);
		System.out.println(ctx);

		MongoTemplate template = ctx.getBean(MongoTemplate.class);
		Query query = new Query();
		query.addCriteria(new Criteria("_id").is("0"));
		Criteria msgIdCrit = Criteria.where("msgId").is("X0");
		Criteria fromCrit = Criteria.where("status").is("444");
		query.addCriteria(Criteria.where("details").elemMatch(msgIdCrit).elemMatch(fromCrit));
		
		printlist(template.findOne(query, DBMessage.class).getDetails());
		
		Update update = new Update();
		update.set("details.$.status", "0").inc("isRead", -1);
		template.updateMulti(query, update, DBMessage.class);
		System.out.println("结束");
	}

	public static void printlist(List<?> list) {
		System.out.println("list.size:" + list.size());
		for (Object obj : list) {
			System.out.println(obj);
		}
	}
}
