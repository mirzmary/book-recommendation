package com.zenjob.bookrecommendation.api.rest.resources.user;

import com.zenjob.bookrecommendation.api.model.common.IdResponseModel;
import com.zenjob.bookrecommendation.api.model.common.ResponseModel;
import com.zenjob.bookrecommendation.api.model.user.UserRegistrationRequestModel;
import com.zenjob.bookrecommendation.facade.user.UserFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 10:39 PM
 */

@Path("/user")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "Film resource", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
public class UserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private UserFacade userFacade;

    @POST
    @Path("/register")
    @ApiOperation(value = "API For registering a new user", response = Response.class)
    public Response validateTradeList(final UserRegistrationRequestModel model, @Context final HttpServletRequest requestContext) {
        LOGGER.debug("Processing user registration request with model - {}", model);
        final ResponseModel<IdResponseModel> response = userFacade.registerUser(model);
        LOGGER.debug("User registration request processed successfully with response - {}", response);
        return Response.ok(response).build();
    }
}
