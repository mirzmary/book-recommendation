package com.zenjob.bookrecommendation.api.rest.resources.bookrecommendation;

import com.zenjob.bookrecommendation.api.model.bookrecommendation.BookFeedbackRequestModel;
import com.zenjob.bookrecommendation.api.model.common.ResponseListModel;
import com.zenjob.bookrecommendation.api.model.bookrecommendation.BookRecommendationResponseModel;
import com.zenjob.bookrecommendation.api.model.common.ResponseModel;
import com.zenjob.bookrecommendation.facade.bookrecommendation.BookRecommendationFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/book")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "Film resource", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
public class BookRecommendationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookRecommendationResource.class);

    @Autowired
    private BookRecommendationFacade bookRecommendationFacade;

    @POST
    @Path("/test")
    @ApiOperation(value = "API Say Hello", notes = "This is just an test API to check if Server is working", response = Response.class)
    public String hello() {
        return "Hello";
    }

    @GET
    @Path("/recommend")
    @ApiOperation(value = "API For getting book recommendations for a user", response = Response.class)
    public Response recommend(@QueryParam("userId") final Long userId, @Context final HttpServletRequest requestContext) {
        LOGGER.debug("Processing book recommendation getting request - {}");
        final ResponseListModel<BookRecommendationResponseModel> response = bookRecommendationFacade.recommendBooks(userId);
        LOGGER.debug("Book recommendation getting processed successfully with response - {}", response);
        return Response.ok(response).build();
    }

    @PUT
    @Path("/feedback")
    @ApiOperation(value = "API For leaving a feedback for a book", response = Response.class)
    public Response feedback(final BookFeedbackRequestModel model, @Context final HttpServletRequest requestContext) {
        LOGGER.debug("Processing feedback leaving request - {}", model);
        final ResponseModel response = bookRecommendationFacade.feedback(model);
        LOGGER.debug("Feedback leaving request processed successfully with response - {}", response);
        return Response.ok(response).build();
    }
}
