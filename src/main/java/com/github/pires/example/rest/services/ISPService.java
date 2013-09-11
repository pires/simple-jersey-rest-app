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
package com.github.pires.example.rest.services;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.pires.example.rest.model.ISP;

@Path("/isp")
public class ISPService {

  private static Map<String, ISP> isps;

  static {
    isps = new HashMap<String, ISP>();
    isps.put("zon", new ISP("zon", "ZON"));
    isps.put("meo", new ISP("meo", "Meo"));
    isps.put("vod", new ISP("vod", "Vodafone"));
  }

  /*
   * This is ctor'ed on every request, so it's lamme. But is enough for simple
   * Jersey testing.
   */
  public ISPService() {

  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<ISP> getISPs() {
    return new ArrayList<ISP>(isps.values());
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{key}")
  public ISP getISP(@PathParam("key") String key) {
    return isps.get(key);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addISP(ISP isp) {
    checkNotNull(isp);
    System.out.println("Creating ISP: " + isp.toString());
    isps.put(isp.getKey(), isp);
    return Response.ok().build();
  }

  @DELETE
  @Path("/{key}")
  public Response removeISP(@PathParam("key") String key) {
    System.out.println("Removing ISP with key: " + key);
    isps.remove(key);
    return Response.ok().build();
  }

}