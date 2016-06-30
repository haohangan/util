package com.grgbanking.aptoto.config.database.mongodb;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.grgbanking.aptoto.mongo.domain.DBMessage;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Test5 {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfig.class);
		System.out.println(ctx);
		MongoTemplate template = ctx.getBean(MongoTemplate.class);
		Query idQuery =new Query();
		idQuery.addCriteria(new Criteria("_id").is("0"));
		/*query.addCriteria()*/
		DBObject field = new BasicDBObject();
		Criteria.where("details").elemMatch(new Criteria("status").is("1"));
		Query query =new BasicQuery(idQuery.getQueryObject(), field);
		DBMessage dbmsg = template.findOne(query, DBMessage.class);
		System.out.println(dbmsg);
		if(dbmsg!=null){
			printlist(dbmsg.getDetails());
		}
		System.out.println("结束");
	}

	
	public static void printlist(List<?> list) {
		System.out.println("list.size:"+list.size());
		for (Object obj : list) {
			System.out.println(obj);
		}
	}
}
