package download.jar;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {

		HttpHost hh = new HttpHost("127.0.0.1", 9200, "http");

		try (RestClient rc = RestClient.builder(hh).build()) {
			RestHighLevelClient client = new RestHighLevelClient(rc);

			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.matchAllQuery());
			searchSourceBuilder.aggregation(AggregationBuilders.terms("top_10_states").field("state").size(10));
			SearchRequest searchRequest = new SearchRequest();
			searchRequest.indices("social-*");
			searchRequest.source(searchSourceBuilder);
			SearchResponse searchResponse = client.search(searchRequest);
			System.out.println(searchResponse);

		}

	}
}
