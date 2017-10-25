package es;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class ESClinet {

	public static void main(String[] args) throws IOException {

		try (RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();) {
			RestHighLevelClient client = new RestHighLevelClient(restClient);
			// search(client);
//			insert(client, title());
			// update(client);
			// get(client);
			// delete(client);
			search(client);
		}

	}

	private static Title title() {
		return Title.builder().id(3).title("now is my favorite time").auth("tiger").date(new Date()).context("who am i")
				.build();
		// return Title.builder().id(1).title("this is a first
		// start").auth("eva").date(new Date()).context("qqqqqqqqqq")
		// .build();
	}

	/**
	 * 增加
	 * 
	 * @param client
	 * @param title
	 * @throws IOException
	 */
	public static void insert(RestHighLevelClient client, Title title) throws IOException {
		IndexRequest request = new IndexRequest("posts", "doc", "3");
		request.source(JsonUtils.toJson(title), XContentType.JSON);
		IndexResponse indexResponse = client.index(request);
		System.out.println(indexResponse.getResult() + "\t" + indexResponse.getIndex());
		System.out.println(indexResponse.getType() + "\t" + indexResponse.getVersion());
		System.out.println(indexResponse.getResult());

	}

	/**
	 * 获取
	 * 
	 * @param client
	 * @throws IOException
	 */
	public static void get(RestHighLevelClient client) throws IOException {
		GetRequest getRequest = new GetRequest("posts", "doc", "2");
		GetResponse getResponse = client.get(getRequest);
		if (getResponse.isExists()) {
			long version = getResponse.getVersion();
			System.out.println("version:" + version);
			String sourceAsString = getResponse.getSourceAsString();
			System.out.println("sourceAsString:" + sourceAsString);
			Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();

			sourceAsMap.forEach((k, v) -> {
				System.out.println(k + "\t" + v);
			});

			byte[] sourceAsBytes = getResponse.getSourceAsBytes();

			System.out.println("sourceAsBytes:" + new String(sourceAsBytes));
		} else {
			System.out.println(getResponse.isExists());
		}

	}

	/**
	 * 删除
	 * 
	 * @param client
	 * @throws IOException
	 */
	static void delete(RestHighLevelClient client) throws IOException {
		DeleteRequest request = new DeleteRequest("posts", "doc", "1");
		DeleteResponse dr = client.delete(request);
		System.out.println(dr.getResult());
	}

	/**
	 * 更新
	 * 
	 * @param client
	 * @throws IOException
	 */
	static void update(RestHighLevelClient client) throws IOException {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("title", "11111111111111");
		UpdateRequest request = new UpdateRequest("posts", "doc", "2").doc(jsonMap);
		UpdateResponse ur = client.update(request);
		System.out.println(ur.getResult());
	}

	/**
	 * 查询
	 * 
	 * @param client
	 * @throws IOException
	 */
	public static void search(RestHighLevelClient client) throws IOException {
		SearchRequest searchRequest = new SearchRequest();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());

		SearchResponse sr = client.search(searchRequest);
//		System.out.println(sr.getHits().totalHits);
		if(sr.getHits().totalHits>0){
			sr.getHits().forEach(a->{
//				System.out.println(a.getId());
//				System.out.println(a.getSourceAsString());
				a.getSourceAsMap().forEach((k,v)->{
					System.out.println(k+"\t"+v);
				});
			});
		}
	}

	// public static void search(RestHighLevelClient client) throws IOException
	// {
	// SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
	// searchSourceBuilder.query(QueryBuilders.matchAllQuery());
	// searchSourceBuilder.aggregation(AggregationBuilders.terms("eva").field("auth").size(10));
	// SearchRequest searchRequest = new SearchRequest();
	// searchRequest.indices("social-*");
	// searchRequest.source(searchSourceBuilder);
	// SearchResponse searchResponse = client.search(searchRequest);
	// System.out.println(searchResponse.getHits().totalHits);
	// }

}
