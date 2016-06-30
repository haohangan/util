package com.grgbanking.aptoto.config.database.mongodb;

import java.util.List;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.grgbanking.aptoto.controller.cometd.svo.PlatformChatSearchVO;
import com.grgbanking.aptoto.mongo.domain.DBMessage;
import com.mongodb.BasicDBObject;

public class Test3 {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfig.class);
		System.out.println(ctx);
		MongoTemplate template = ctx.getBean(MongoTemplate.class);
		PlatformChatSearchVO svo = new PlatformChatSearchVO();
		svo.setUser("xhua");
		svo.setTid("xhong");
		
		 List<DBMessage> list = platformGetChat(template,svo);
		 Test.printlist(list);
	}

	public static List<DBMessage> platformGetChat(MongoTemplate template,PlatformChatSearchVO svo) {
		Query tempQuery = new Query();
		if (StringUtil.isNotBlank(svo.getUser())) {
			tempQuery.addCriteria(new Criteria("user").is(svo.getUser()));
		}
		if (StringUtil.isNotBlank(svo.getTid())) {
			tempQuery.addCriteria(new Criteria("tid").is(svo.getTid()));
		}
		Criteria dateCriteria = Criteria.where("date");
		if (svo.getStartDate() != null) {
			dateCriteria.gte(svo.getStartDate());
		}
		if (svo.getEndDate() != null) {
			dateCriteria.lte(svo.getEndDate());
		}
		if(svo.getStartDate() != null || svo.getEndDate() != null){
			tempQuery.addCriteria(dateCriteria);
		}
		BasicDBObject bo = new BasicDBObject();
		bo.put("details", false);
		Query query = new BasicQuery(tempQuery.getQueryObject(),bo);
		query.getQueryObject();
		query.skip(svo.getSikp()).limit(svo.getPageSize());
		return template.find(query, DBMessage.class);
	}

}
