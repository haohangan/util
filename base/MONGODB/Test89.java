package com.grgbanking.aptoto.config.database.mongodb;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.CommandResult;

public class Test89 {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfig.class);
		System.out.println(ctx);

		MongoTemplate template = ctx.getBean(MongoTemplate.class);
//		Query query = new Query(new Criteria().andOperator(Criteria.where("_id").is("0"),
//				Criteria.where("details").elemMatch(Criteria.where("status").is("0"))));
//		printlist(template.findOne(query, DBMessage.class).getDetails());
		
		CommandResult cr = template.executeCommand("");
		Set<Entry<String, Object>>  set = cr.entrySet();
		for(Entry<String, Object> entry:set){
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		}
//		Update update = new Update();
//		update.set("details.$.status", "1");
//		template.updateMulti(query, update, DBMessage.class);
		System.out.println("结束");
	}

	public static void printlist(List<?> list) {
		System.out.println("list.size:" + list.size());
		for (Object obj : list) {
			System.out.println(obj);
		}
	}
}
