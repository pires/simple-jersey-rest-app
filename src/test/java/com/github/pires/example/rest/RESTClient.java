/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.pires.example.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * REST client.
 */
public final class RESTClient {

	private static final int DEFAULT_TIMEOUT = 10000; // milliseconds

	/**
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static HttpResponse doJSONPost(String path, String cookie,
			String json) throws ClientProtocolException, IOException {
		HttpResponse response = null;
		HttpPost httpPost = new HttpPost(path);
		httpPost.setHeader("accept", "application/json");

		if (json != null) {
			StringEntity se = new StringEntity(json, "utf-8");
			se.setContentType("application/json");
			httpPost.setEntity(se);
		}

		if (cookie != null)
			httpPost.setHeader("Cookie", cookie);

		// set response value
		HttpClient client = getNewHtppClient();
		response = client.execute(httpPost);

		return response;
	}

	/**
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static HttpResponse doJSONPut(String path, String cookie, String json)
			throws ClientProtocolException, IOException {
		HttpResponse response = null;
		HttpPut httpPut = new HttpPut(path);
		httpPut.setHeader("accept", "application/json");

		if (json != null) {
			StringEntity se = new StringEntity(json, "utf-8");
			se.setContentType("application/json");
			httpPut.setEntity(se);
		}

		if (cookie != null)
			httpPut.setHeader("Cookie", cookie);

		// set response value
		HttpClient client = getNewHtppClient();
		response = client.execute(httpPut);

		return response;
	}

	/**
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static HttpResponse doHttpDelete(String path, String cookie)
			throws ClientProtocolException, IOException {
		HttpResponse response = null;
		HttpDelete httpDelete = new HttpDelete(path);
		httpDelete.setHeader("accept", "application/json");

		if (cookie != null)
			httpDelete.setHeader("Cookie", cookie);

		// set response value
		HttpClient client = getNewHtppClient();
		response = client.execute(httpDelete);

		return response;
	}

	/**
	 * If values are null, then a no-body request will be made.
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static HttpResponse doHttpGet(String path, String cookie,
			Map<String, String> values) throws ClientProtocolException,
			IOException {
		HttpResponse response = null;
		// populate http params if any
		if (values != null) {
			List<NameValuePair> params = new ArrayList<NameValuePair>(
					values.size());
			for (Map.Entry<String, String> entry : values.entrySet())
				params.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));

			String query = URLEncodedUtils.format(params, "utf-8");
			path += "/?" + query;
		}

		// instantiate HttpGet only after its path is defined.
		HttpGet httpGet = new HttpGet(path);
		httpGet.setHeader("accept", "application/json");

		if (cookie != null)
			httpGet.setHeader("Cookie", cookie);

		// set response value
		HttpClient client = getNewHtppClient();
		response = client.execute(httpGet);

		return response;
	}

	/**
	 * @return a new HTTP client with default configuration.
	 */
	private static HttpClient getNewHtppClient() {
		return getNewHtppClient(DEFAULT_TIMEOUT);
	}

	/**
	 * 
	 * @param timeout
	 *            the timeout for HTTP connection.
	 * @return a new HTTP client with parameterized timeout.
	 */
	private static HttpClient getNewHtppClient(int timeout) {
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, timeout);
		HttpConnectionParams.setSoTimeout(params, timeout);
		return new DefaultHttpClient(params);
	}

}