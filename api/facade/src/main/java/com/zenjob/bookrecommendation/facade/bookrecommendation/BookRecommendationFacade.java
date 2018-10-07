package com.zenjob.bookrecommendation.facade.bookrecommendation;

import com.zenjob.bookrecommendation.api.model.bookrecommendation.BookFeedbackRequestModel;
import com.zenjob.bookrecommendation.api.model.common.ResponseListModel;
import com.zenjob.bookrecommendation.api.model.bookrecommendation.BookRecommendationResponseModel;
import com.zenjob.bookrecommendation.api.model.common.ResponseModel;

public interface BookRecommendationFacade {

    ResponseListModel<BookRecommendationResponseModel> recommendBooks(final Long userId);

    ResponseModel feedback(final BookFeedbackRequestModel feedbackRequestModel);
}
