// StatisticsResource.java (JAX-RS Resource class)
package com.assessment.resource;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.assessment.service.VectorService;

@Path("/statistics")
public class StatisticsResource {

	private static final Logger logger = Logger.getLogger(StatisticsResource.class.getName());

	@Inject
	private VectorService vectorService;

	@GET
	@Path("/{vectorID}")
	public Response calculateStatistics(@PathParam("vectorID") int vectorID) {
		
		logger.info("Trying to use vectorService: " + vectorService);
		// Assume that you have a service or DAO to retrieve the vector by its ID
		int[] vector = vectorService.getVectorById(vectorID).getNumbers();

		if (vector == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Vector not found").build();
		}

		double mean = vectorService.calculateMean(vector);
		double standardDeviation = vectorService.calculateStandardDeviation(vector);

		String result = String.format("Mean: %.2f, Standard Deviation: %.2f", mean, standardDeviation);

		return Response.ok(result).build();
	}
}