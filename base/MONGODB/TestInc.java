package com.grgbanking.aptoto.config.database.mongodb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.grgbanking.aptoto.mongo.domain.DBMessage;

public class TestInc {
public static void main(String[] args) {
	ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfig.class);
	System.out.println(ctx);
	MongoTemplate template = ctx.getBean(MongoTemplate.class);
	
	Query query = new Query();
	query.addCriteria(new Criteria("_id").is("1"));
	Update update = new Update();
	update.inc("size", 1);
	template.updateFirst(query, update, DBMessage.class);
}
}
