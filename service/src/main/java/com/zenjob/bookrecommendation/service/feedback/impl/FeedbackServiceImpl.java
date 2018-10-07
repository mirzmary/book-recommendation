package com.zenjob.bookrecommendation.service.feedback.impl;

import com.zenjob.bookrecommendation.persistence.feedback.FeedBackRepository;
import com.zenjob.bookrecommendation.persistence.feedback.entity.Feedback;
import com.zenjob.bookrecommendation.service.book.BookService;
import com.zenjob.bookrecommendation.service.feedback.FeedbackService;
import com.zenjob.bookrecommendation.service.feedback.dto.FeedbackDto;
import com.zenjob.bookrecommendation.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 8:42 PM
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedBackRepository feedBackRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Override
    public Feedback create(final FeedbackDto feedbackDto) {
        assertFeedbackDto(feedbackDto);
        final Feedback feedback = convertFeedbackDtoToEntity(feedbackDto);
        return feedBackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getByBookId(final Long bookId) {
        Assert.notNull(bookId, "bookId should not be null when getting book feedbacks for a book");
        return feedBackRepository.findByBookId(bookId);
    }

    private Feedback convertFeedbackDtoToEntity(final FeedbackDto feedbackDto) {
        final Feedback feedback = new Feedback();
        feedback.setUser(userService.getById(feedbackDto.getUserId()));
        feedback.setBook(bookService.getById(feedbackDto.getBookId()));
        feedback.setFeedback(feedbackDto.getFeedback());
        return feedback;
    }

    private void assertFeedbackDto(final FeedbackDto feedbackDto) {
        Assert.notNull(feedbackDto, "feedbackDto can not be null when creating user feedback");
        Assert.notNull(feedbackDto.getBookId(), "feedbackDto bookId can not be null when creating user feedback");
        Assert.notNull(feedbackDto.getUserId(), "feedbackDto userId can not be null when creating user feedback");
        Assert.notNull(feedbackDto.getFeedback(), "feedbackDto feedback can not be null when creating user feedback");
    }
}
