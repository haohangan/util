package com.grgbanking.aptoto.config.database.mongodb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.grgbanking.aptoto.mongo.domain.DBMessage;
import com.mongodb.BasicDBObject;

public class Test4 {
public static void main(String[] args) {
	ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfig.class);
	System.out.println(ctx);
	MongoTemplate template = ctx.getBean(MongoTemplate.class);
	Query q = new Query();
	q.addCriteria(Criteria.where("bussId").is("1234567"));
	q.addCriteria(Criteria.where("msgType").is("1"));
	BasicDBObject bo = new BasicDBObject();
	bo.put("details", false);
	Query query = new BasicQuery(q.getQueryObject(), bo);
	DBMessage db = template.findOne(query, DBMessage.class);
	System.out.println(db);
}
}
