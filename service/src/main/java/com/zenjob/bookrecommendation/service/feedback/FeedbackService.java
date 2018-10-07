package com.zenjob.bookrecommendation.service.feedback;

import com.zenjob.bookrecommendation.persistence.feedback.entity.Feedback;
import com.zenjob.bookrecommendation.service.feedback.dto.FeedbackDto;

import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 8:42 PM
 */
public interface FeedbackService {

    Feedback create(final FeedbackDto feedbackDto);

    List<Feedback> getByBookId(final Long bookId);
}
