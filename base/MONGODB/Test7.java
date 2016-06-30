package com.grgbanking.aptoto.config.database.mongodb;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import com.grgbanking.aptoto.mongo.vo.ChartDetailVO;

public class Test7 {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfig.class);
		System.out.println(ctx);

		MongoTemplate template = ctx.getBean(MongoTemplate.class);
		Aggregation agg = Aggregation.newAggregation(Aggregation.match(Criteria.where("_id").is("0")),
				Aggregation.project().and("details").as("detail").andInclude("unRead").andExclude("_id"),
				Aggregation.unwind("$detail"), Aggregation.match(Criteria.where("detail.status").is("1")),
				Aggregation.limit(10), Aggregation.skip(0), Aggregation.sort(Direction.DESC, "detail.date"));
		AggregationResults<ChartDetailVO> groupResults = template.aggregate(agg, "messages", ChartDetailVO.class);
		List<ChartDetailVO> list = groupResults.getMappedResults();
		printlist(list);
		System.out.println("结束");
	}

	public static void printlist(List<?> list) {
		System.out.println("list.size:" + list.size());
		for (Object obj : list) {
			System.out.println(obj);
		}
	}
}
