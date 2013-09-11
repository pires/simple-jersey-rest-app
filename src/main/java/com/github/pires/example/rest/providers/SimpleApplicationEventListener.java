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
package com.github.pires.example.rest.providers;

import java.util.logging.Logger;

import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;

public class SimpleApplicationEventListener implements ApplicationEventListener {

  private static final Logger logger = Logger
      .getLogger(ApplicationEventListener.class.getName());

  @Override
  public void onEvent(ApplicationEvent event) {
    switch (event.getType()) {
    case INITIALIZATION_FINISHED:
      System.out.println("Jersey application started.");
      break;
    default:
      break;
    }
  }

  @Override
  public RequestEventListener onRequest(RequestEvent requestEvent) {
    return new RequestEventListener() {

      private volatile long startTime;

      @Override
      public void onEvent(RequestEvent event) {
        switch (event.getType()) {
        case RESOURCE_METHOD_START:
          startTime = System.currentTimeMillis();
          logger.info("Resource method "
              + event.getUriInfo().getMatchedResourceMethod().getHttpMethod()
              + " started at " + startTime);
          break;
        case FINISHED:
          logger.info("Request finished. Processing time "
              + (System.currentTimeMillis() - startTime) + " ms.");
          break;
        default:
          break;
        }
      }
    };

  }
}
