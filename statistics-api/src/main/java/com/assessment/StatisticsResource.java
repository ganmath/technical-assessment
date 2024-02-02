package com.assessment;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/api/statistics")
public class StatisticsResource {

    @GET
    @Path("/{vectorID}")
    public Response calculateStatistics(@PathParam("vectorID") int vectorID) {
        // Assume that you have a service or DAO to retrieve the vector by its ID
        int[] vector = getVectorById(vectorID);

        if (vector == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Vector not found").build();
        }

        double mean = calculateMean(vector);
        double standardDeviation = calculateStandardDeviation(vector);

        String result = String.format("Mean: %.2f, Standard Deviation: %.2f", mean, standardDeviation);

        return Response.ok(result).build();
    }

    // Dummy method to retrieve vector by ID (replace with your actual logic)
    private int[] getVectorById(int vectorID) {
        // Replace this with your logic to fetch the vector from the database or service
        // For simplicity, returning a dummy vector here
        return new int[]{1, 2, 3, 4, 5};
    }

    // Dummy method to calculate mean (replace with your actual logic)
    private double calculateMean(int[] vector) {
        return vector.length > 0 ? (double) sum(vector) / vector.length : 0;
    }

    // Dummy method to calculate standard deviation (replace with your actual logic)
    private double calculateStandardDeviation(int[] vector) {
        if (vector.length == 0) {
            return 0;
        }

        double mean = calculateMean(vector);
        double sumSquaredDeviations = 0;

        for (int value : vector) {
            sumSquaredDeviations += Math.pow(value - mean, 2);
        }

        return Math.sqrt(sumSquaredDeviations / vector.length);
    }

    private int sum(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
}
