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
package com.github.pires.example;

import java.io.File;
import java.io.IOException;

import org.glassfish.embeddable.Deployer;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;
import org.glassfish.embeddable.archive.ScatteredArchive;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Testing environment configuration.
 */
public final class SetupTestSuite {

  public final static int SERVER_PORT = 8181;
  private GlassFish gfServer;

  /**
   * This is meant to run before all tests are performed.
   * 
   * @throws GlassFishException
   * @throws IOException
   * 
   * @throws Throwable
   */
  @BeforeSuite
  public final void setUp() throws GlassFishException, IOException {
    // set-up embedded Glassfish instance
    GlassFishProperties gfProperties = new GlassFishProperties();
    gfProperties.setPort("http-listener", SERVER_PORT);
    gfServer = GlassFishRuntime.bootstrap().newGlassFish(gfProperties);
    gfServer.start();

    // create WAR
    Deployer deployer = gfServer.getDeployer();
    ScatteredArchive archive = new ScatteredArchive("simple",
        ScatteredArchive.Type.WAR);
    /*
     * by adding individual files and folders, we keep control of what is and
     * what isn't deployed for testing. for instance, we want to ignore EJB
     * timers, as they blow up :-/
     */
    archive.addClassPath(new File("target", "classes"));
    archive.addClassPath(new File("target", "test-classes"));
    archive.addMetadata(new File("src/main/webapp/WEB-INF", "web.xml"));

    // Deploy the scattered web archive.
    deployer.deploy(archive.toURI(), "--contextroot=rest");
  }

  /**
   * @throws InterruptedException
   * @throws GlassFishException
   *           This is meant to run after all tests are performed.
   * 
   * @throws
   */
  @AfterSuite
  public final void tearDown() throws GlassFishException {
    gfServer.stop();
    gfServer.dispose();
  }

}