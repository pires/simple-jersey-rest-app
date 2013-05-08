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

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collection;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import com.github.pires.example.rest.model.ISP;
import com.github.pires.example.rest.services.ISPService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Tests ISP service.
 * 
 * @see ISPService
 */
public class ISPServiceTest {

	private static final String PATH = "http://localhost:9090/rest/isp";

	private Type collectionType = new TypeToken<Collection<ISP>>() {
	}.getType();

	@Test
	public void testListIsps() throws ClientProtocolException, IOException {
		HttpResponse httpResponse = RESTClient.doHttpGet(PATH.concat("/list"),
		        null, null);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
		Reader reader = new InputStreamReader(httpResponse.getEntity()
		        .getContent(), "UTF-8");
		Collection<ISP> isps = new Gson().fromJson(reader, collectionType);
		assertEquals(isps.size(), 3);
	}

	@Test
	public void addIsp() throws ClientProtocolException, IOException {
		ISP tmn = new ISP("tmn", "TMN");
		HttpResponse httpResponse = RESTClient.doJSONPut(PATH, null,
		        new Gson().toJson(tmn));
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
	}

	@Test
	public void testDeleteIsp() throws ClientProtocolException, IOException {
		HttpResponse httpResponse = RESTClient.doHttpDelete(
		        PATH.concat("/zon"), null);
		assertEquals(httpResponse.getStatusLine().getStatusCode(), 200);
	}

}