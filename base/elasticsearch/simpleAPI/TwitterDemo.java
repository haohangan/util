package es;

import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class TwitterDemo {
     
	public static void main(String[] args) throws IOException {
//		RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200, "http")).build()
		try(RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200, "http")).build()){
			RestHighLevelClient client = new RestHighLevelClient(restClient);
			query(client);
			System.out.println("over");
		}
	}
	
	
	private static void index(RestHighLevelClient client) throws IOException {
		Twitter twitter1 = Twitter.builder().user("kimchy").post_date(new Date()).message("Trying out Elasticsearch, so far so good?").build();
		Twitter twitter2 = Twitter.builder().user("kimchy").post_date(new Date(System.currentTimeMillis()-1000*60*60*1)).message("Another tweet, will it be indexed?").build();
		Twitter twitter3 = Twitter.builder().user("elastic").post_date(new Date(System.currentTimeMillis()+1000*60*60*1)).message("Building the site, should be kewl").build();
		
		String name = "twitter";
		index(client,name,"1",twitter1);
		index(client,name,"2",twitter2);
		index(client,name,"3",twitter3);
	}
	
	
	
	private static void index(RestHighLevelClient client,String  name,String id,Twitter twitter) throws IOException {
		IndexRequest request = new IndexRequest(name, "doc", id);
		request.source(JsonUtils.toJson(twitter), XContentType.JSON);
		IndexResponse indexResponse = client.index(request);
		System.out.println(indexResponse.getResult() + "\t" + indexResponse.getIndex());
		System.out.println(indexResponse.getType() + "\t" + indexResponse.getVersion());
		System.out.println(indexResponse.getResult());
	}
	
	static void delete(RestHighLevelClient client) throws IOException {
		DeleteRequest request1 = new DeleteRequest("twitter", "doc", "1");
		DeleteResponse dr1 = client.delete(request1);
		System.out.println(dr1.getResult());
		
		DeleteRequest request2 = new DeleteRequest("twitter", "doc", "2");
		DeleteResponse dr2 = client.delete(request2);
		System.out.println(dr2.getResult());
		
		DeleteRequest request3 = new DeleteRequest("twitter", "doc", "3");
		DeleteResponse dr3 = client.delete(request3);
		System.out.println(dr3.getResult());
	}
	
	static void query(RestHighLevelClient client) throws IOException {
		SearchRequest request = new SearchRequest();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.fuzzyQuery("user", "im"));
		
		SearchResponse sr = client.search(request);
		
		sr.getHits().forEach(a->{
			System.out.println("====================start------------");
			System.out.println(a.getIndex()+"\t"+a.getId());
			System.out.println(a.getSourceAsString());
			System.out.println("====================end------------");
		});
	}
}
