/**
 * 
 */
package alokawi.elasticsearch.core;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * @author alokkumar
 *
 */
public class ElasticSearchIndexManager {

	public void prepareIndex() throws IOException {
		try (@SuppressWarnings("resource")
		TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))) {

			IndexResponse response = client
					.prepareIndex("twitter", "tweet", "1").setSource(jsonBuilder().startObject().field("user", "kimchy")
							.field("postDate", new Date()).field("message", "trying out Elasticsearch").endObject())
					.get();

			System.out.println(response.toString());
		}
	}

}
