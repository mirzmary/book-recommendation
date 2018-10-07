package com.zenjob.bookrecommendation.service.common.feedback.impl;

import com.zenjob.bookrecommendation.persistence.book.entity.Book;
import com.zenjob.bookrecommendation.persistence.feedback.FeedBackRepository;
import com.zenjob.bookrecommendation.persistence.feedback.entity.Feedback;
import com.zenjob.bookrecommendation.persistence.user.entity.User;
import com.zenjob.bookrecommendation.service.book.BookService;
import com.zenjob.bookrecommendation.service.common.AbstractServiceImplTest;
import com.zenjob.bookrecommendation.service.feedback.FeedbackService;
import com.zenjob.bookrecommendation.service.feedback.dto.FeedbackDto;
import com.zenjob.bookrecommendation.service.feedback.impl.FeedbackServiceImpl;
import com.zenjob.bookrecommendation.service.user.UserService;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.expect;

/**
 * User: Mary Mirzoyan
 * Date: 10/8/18
 * Time: 1:44 AM
 */
public class FeedbackServiceImplTest extends AbstractServiceImplTest {

    @TestSubject
    private FeedbackService feedbackService = new FeedbackServiceImpl();

    @Mock
    private FeedBackRepository feedBackRepository;

    @Mock
    private UserService userService;

    @Mock
    private BookService bookService;

    // region get feedbacks for a book

    @Test(expected = IllegalArgumentException.class)
    public void testGetByBookIdWithIllegalArgument() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        feedbackService.getByBookId(null);
        // Verify
        verifyAll();
    }

    @Test
    public void testGetByBookIdNoFeedbacksFound() throws Exception {
        // Test data
        final Long bookId = 1L;
        // Reset
        resetAll();
        // Expectations
        expect(feedBackRepository.findByBookId(bookId)).andReturn(new ArrayList<>());

        // Replay
        replayAll();

        // Run test scenario
        final List<Feedback> feedbacks = feedbackService.getByBookId(bookId);
        // Verify
        Assert.assertEquals(feedbacks.size(), 0);
        verifyAll();
    }

    @Test
    public void testGetByBookIdNormalFlow() throws Exception {
        // Test data
        final Long bookId = 1L;
        final List<Feedback> feedbacks = getHelper().createMorePositiveThandNegativeFeedbacks();
        // Reset
        resetAll();
        // Expectations
        expect(feedBackRepository.findByBookId(bookId)).andReturn(feedbacks);

        // Replay
        replayAll();

        // Run test scenario
        final List<Feedback> result = feedbackService.getByBookId(bookId);
        // Verify
        Assert.assertTrue(result.size() > 0);
        Assert.assertEquals(feedbacks, result);
        verifyAll();
    }

    // endregion

    // region Create a feedback

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFeedbackWithNullDto() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        feedbackService.create(null);
        // Verify
        verifyAll();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFeedbackWithNullUser() throws Exception {
        // Test data
        final FeedbackDto feedbackDto = getHelper().createFeedbackDto();
        feedbackDto.setUserId(null);
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        feedbackService.create(feedbackDto);
        // Verify
        verifyAll();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFeedbackWithNullBook() throws Exception {
        // Test data
        final FeedbackDto feedbackDto = getHelper().createFeedbackDto();
        feedbackDto.setBookId(null);
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        feedbackService.create(feedbackDto);
        // Verify
        verifyAll();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFeedbackWithNullFeedback() throws Exception {
        // Test data
        final FeedbackDto feedbackDto = getHelper().createFeedbackDto();
        feedbackDto.setFeedback(null);
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        feedbackService.create(feedbackDto);
        // Verify
        verifyAll();
    }

    @Test
    public void testCreateFeedbackNormalFlow() throws Exception {
        // Test data
        final FeedbackDto feedbackDto = getHelper().createFeedbackDto();
        final User user = getHelper().createUser();
        final Book book = getHelper().createBook();
        final Feedback feedback = getHelper().createFeedback(user, book);
        // Reset
        resetAll();
        // Expectations
        expect(userService.getById(feedbackDto.getUserId())).andReturn(user);
        expect(bookService.getById(feedbackDto.getBookId())).andReturn(book);
        expect(feedBackRepository.save(feedback)).andReturn(feedback);
        // Replay
        replayAll();

        // Run test scenario
        final Feedback result = feedbackService.create(feedbackDto);
        // Verify
        Assert.assertEquals(feedback, result);
        verifyAll();
    }

    // endregion
}
