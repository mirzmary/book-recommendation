package com.zenjob.bookrecommendation.facade.bookrecommendation.impl;

import com.zenjob.bookrecommendation.api.model.bookrecommendation.BookFeedbackRequestModel;
import com.zenjob.bookrecommendation.api.model.bookrecommendation.BookRecommendationResponseModel;
import com.zenjob.bookrecommendation.api.model.common.ResponseListModel;
import com.zenjob.bookrecommendation.api.model.common.ResponseModel;
import com.zenjob.bookrecommendation.facade.bookrecommendation.BookRecommendationFacade;
import com.zenjob.bookrecommendation.persistence.book.entity.Book;
import com.zenjob.bookrecommendation.service.book.BookService;
import com.zenjob.bookrecommendation.service.feedback.FeedbackService;
import com.zenjob.bookrecommendation.service.feedback.dto.FeedbackDto;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component
public class BookRecommendationFacadeImpl implements BookRecommendationFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookRecommendationFacadeImpl.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    public ResponseListModel<BookRecommendationResponseModel> recommendBooks(final Long userId) {
        Assert.notNull(userId, "User Id can not be null when recommending books to a user");
        final List<Book> books = bookService.recommendBooksToUser(userId);
        final List<BookRecommendationResponseModel> bookRecommendationResponseModels = mapperFacade.mapAsList(books, BookRecommendationResponseModel.class);

        return new ResponseListModel<>(bookRecommendationResponseModels);
    }

    @Override
    public ResponseModel feedback(final BookFeedbackRequestModel feedbackRequestModel) {
        assertBookFeedbackModel(feedbackRequestModel);
        final FeedbackDto feedbackDto = mapperFacade.map(feedbackRequestModel, FeedbackDto.class);
        feedbackService.create(feedbackDto);
        return new ResponseModel();
    }

    private void assertBookFeedbackModel(final BookFeedbackRequestModel feedbackRequestModel) {
        Assert.notNull(feedbackRequestModel, "BookFeedbackRequestModel can not be null when giving a feedback for a book");
        Assert.notNull(feedbackRequestModel.getBookId(), "Book Id can not be null when giving a feedback for a book");
        Assert.notNull(feedbackRequestModel.getUserId(), "User Id can not be null when giving a feedback for a book");
        Assert.notNull(feedbackRequestModel.getFeedback(), "Feedback can not be null when giving a feedback for a book");
    }
}
