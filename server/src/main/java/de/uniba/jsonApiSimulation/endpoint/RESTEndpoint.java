package de.uniba.jsonApiSimulation.endpoint;

import de.uniba.jsonApiSimulation.backend.bean.JSONApiSimulationEndpointBean;
import de.uniba.jsonApiSimulation.shared.model.Faculty;
import sun.rmi.runtime.Log;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.logging.Logger;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * Creates the REST endpoint and expose data.
 * Created by chandan on 04.05.17.
 */
@Path("rest")
@Stateless
public class RESTEndpoint {

    private static final Logger logger = Logger.getLogger(RESTEndpoint.class.getName());

    @EJB
    JSONApiSimulationEndpointBean jsonApiSimulationEndpointBean;


    /**
     * Expose the collection of recorded Faculties with the help of JSON schema.
     * @return
     */
    @GET
    @Path("/faculties")
    @Produces(APPLICATION_JSON)
    public Response getAllWiAiFaculties(){

        try{
            List<Faculty> facultyList = jsonApiSimulationEndpointBean.getFaculties();
            if(facultyList.size() <= 0){
                logger.info("We have got o size list of faculty");
            }

            GenericEntity<List<Faculty>> response = new GenericEntity<List<Faculty>>(facultyList) {};

            return Response.ok(response, MediaType.APPLICATION_JSON).build();

        }catch (InternalServerErrorException iSE){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error").build();
        }
    }
}
