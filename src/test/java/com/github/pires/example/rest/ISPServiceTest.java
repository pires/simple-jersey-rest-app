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

import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.testng.annotations.BeforeClass;
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

  private static final String PATH = "http://localhost:8181/rest/isp";
  private static final int STATUS_OK = 200;
  private Client client;
  private WebTarget target;

  private Type type = new TypeToken<List<ISP>>() {
  }.getType();

  @BeforeClass
  private void setUp() {
    client = ClientBuilder.newClient();
    target = client.target(PATH);
  }

  @Test
  public void testListIsps() {
    Response response = target.request().get();
    assertEquals(response.getStatus(), STATUS_OK);
    final String json = response.readEntity(String.class);
    List<ISP> isps = new Gson().fromJson(json, type);
    assertEquals(isps.size(), 3);
  }

  @Test
  public void testAddIsp() {
    ISP tmn = new ISP("tmn", "TMN");
    Response response = target.request(MediaType.APPLICATION_JSON).put(
        Entity.json(tmn));
    assertEquals(response.getStatus(), STATUS_OK);
  }

  @Test
  public void testDeleteIsp() {
    Response response = target.path("/zon").request().delete();
    assertEquals(response.getStatus(), STATUS_OK);
  }

}