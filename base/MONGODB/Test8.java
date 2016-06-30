package com.grgbanking.aptoto.config.database.mongodb;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.grgbanking.aptoto.mongo.domain.DBMessage;

public class Test8 {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfig.class);
		System.out.println(ctx);

		MongoTemplate template = ctx.getBean(MongoTemplate.class);
		Query q = new Query();
		q.addCriteria(Criteria.where("_id").is("0"));
		q.addCriteria(Criteria.where("details.status").all("2","3333"));//Criteria.where("msgId").
		printlist(template.findOne(q, DBMessage.class).getDetails());
		
		Update update = new Update();
		update.set("details.$.status", "444").inc("isRead", -1);
		template.updateMulti(q, update, DBMessage.class);
		System.out.println("结束");
	}

	public static void printlist(List<?> list) {
		System.out.println("list.size:" + list.size());
		for (Object obj : list) {
			System.out.println(obj);
		}
	}
}
