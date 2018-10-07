package com.zenjob.bookrecommendation.facade.common.mapping;

import com.zenjob.bookrecommendation.api.model.bookrecommendation.BookRecommendationResponseModel;
import com.zenjob.bookrecommendation.persistence.book.entity.Book;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

@Component
public class BookMappingConfigurer implements MappingConfigurer {

    @Override
    public void configure(final MapperFactory factory) {
        factory.classMap(Book.class, BookRecommendationResponseModel.class)
                .byDefault()
                .register();
    }
}
