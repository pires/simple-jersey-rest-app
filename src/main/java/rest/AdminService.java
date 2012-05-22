/*
 * TODO put header
 */
package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 */
@Path("/admin")
public class AdminService {

        ISP zon = new ISP(0, "ZON");
        ISP meo = new ISP(1, "Meo");
        ISP vodafone = new ISP(2, "Vodafone");
        private ISP[] isps = new ISP[] {zon, meo, vodafone};

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/isp")
	public ISP[] getISPs() {
            return isps;
	}

	@GET
        @Produces(MediaType.APPLICATION_JSON)
	@Path("/isp/{id}")
	public ISP getISP(@PathParam("id") Integer id) {
            return isps[id];
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/isp/{isp}")
	public void addISP(ISP isp) {
	    System.out.println("ISP name: " + isp.getName());
        }

	@DELETE
	@Path("/isp/{id}")
	public void removeISP(@PathParam("id") Long id) {
            // TODO
	}

}
