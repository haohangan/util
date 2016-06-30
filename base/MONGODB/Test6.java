package com.grgbanking.aptoto.config.database.mongodb;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.grgbanking.aptoto.mongo.domain.DBMessage;
import com.mongodb.DBCollection;
import com.mongodb.MongoException;

public class Test6 {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfig.class);
		System.out.println(ctx);
		MongoTemplate template = ctx.getBean(MongoTemplate.class);
		template.execute(DBMessage.class, new CollectionCallback<DBMessage>() {

			@Override
			public DBMessage doInCollection(DBCollection collection) throws MongoException, DataAccessException {
				
				return null;
			}
			
		});
		System.out.println("结束");
	}

	
	public static void printlist(List<?> list) {
		System.out.println("list.size:"+list.size());
		for (Object obj : list) {
			System.out.println(obj);
		}
	}
}
