package com.zenjob.bookrecommendation.api.rest.config;

import com.zenjob.bookrecommendation.api.rest.resources.bookrecommendation.BookRecommendationResource;
import com.zenjob.bookrecommendation.api.rest.resources.user.UserResource;
import io.swagger.jaxrs.listing.ApiListingResource;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.server.validation.ValidationFeature;

public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(MultiPartFeature.class);
        register(BookRecommendationResource.class);
        register(UserResource.class);


        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        register(RequestContextFilter.class);
        register(JacksonObjectMapper.class);
        register(LoggingFeature.class);
        register(ValidationFeature.class);
        register(MultiPartFeature.class);
        register(ApiListingResource.class);
        register(JacksonObjectMapper.class);
    }
}
