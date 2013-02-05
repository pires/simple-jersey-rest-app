/*
 * TODO put header
 */
package rest;

import java.util.Arrays;
import java.util.List;
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
@Path("/isp")
public class ISPService {

        ISP zon = new ISP(0, "ZON");
        ISP meo = new ISP(1, "Meo");
        ISP vodafone = new ISP(2, "Vodafone");
        private ISP[] isps = new ISP[] {zon, meo, vodafone};

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public List<ISP> getISPs() {
            return Arrays.asList(isps);
	}

	@GET
        @Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public ISP getISP(@PathParam("id") Integer id) {
            return isps[id];
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addISP(ISP isp) {
	    System.out.println("Created ISP with name: " + isp.getName());
        }

	@DELETE
	@Path("/{id}")
	public void removeISP(@PathParam("id") Long id) {
            System.out.println("Removed ISP with ID " + id);
	}

}
