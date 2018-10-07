package com.zenjob.bookrecommendation.service.common.feedback;

import com.zenjob.bookrecommendation.persistence.book.entity.Book;
import com.zenjob.bookrecommendation.persistence.feedback.entity.Feedback;
import com.zenjob.bookrecommendation.persistence.feedback.enums.FeedbackEnum;
import com.zenjob.bookrecommendation.persistence.user.entity.User;
import com.zenjob.bookrecommendation.service.common.AbstractServiceIntegrationTest;
import com.zenjob.bookrecommendation.service.feedback.FeedbackService;
import com.zenjob.bookrecommendation.service.feedback.dto.FeedbackDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/8/18
 * Time: 1:30 AM
 */
public class FeedbackServiceIntegrationTest extends AbstractServiceIntegrationTest {

    @Autowired
    private FeedbackService feedbackService;

    @Test
    public void testGetFeedbacksByBookId() throws Exception {
        // Given
        final User user = getHelper().createAndPersistUser();
        final Book book = getHelper().createAndPersistBook();
        flushAndClear();
        final Feedback feedback = getHelper().createAndPersistFeedback(user, book);
        // When
        final List<Feedback> feedbacks = feedbackService.getByBookId(book.getId());
        // Then
        Assert.assertEquals(feedbacks.size(), 1);
        Assert.assertTrue(feedbacks.contains(feedback));
    }

    @Test
    public void testCreateFeedback() throws Exception {
        // Given
        final User user = getHelper().createAndPersistUser();
        final Book book = getHelper().createAndPersistBook();
        flushAndClear();
        final FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setBookId(book.getId());
        feedbackDto.setUserId(user.getId());
        feedbackDto.setFeedback(FeedbackEnum.LIKE);

        // When
        final Feedback feedback = feedbackService.create(feedbackDto);
        // Then
        Assert.assertEquals(feedbackDto.getBookId(), feedback.getBook().getId());
        Assert.assertEquals(feedbackDto.getUserId(), feedback.getUser().getId());
        Assert.assertEquals(feedbackDto.getFeedback(), feedback.getFeedback());
    }
}
