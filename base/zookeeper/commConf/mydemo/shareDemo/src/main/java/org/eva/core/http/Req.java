package org.eva.core.http;

import java.io.IOException;
import org.apache.hc.client5.http.fluent.Request;

public class Req {

	public static String req(String url) throws IOException {
		return Request.Get(url).execute().returnContent().toString();
	}
}
