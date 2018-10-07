package com.zenjob.bookrecommendation.facade.user;

import com.zenjob.bookrecommendation.api.model.common.IdResponseModel;
import com.zenjob.bookrecommendation.api.model.common.ResponseModel;
import com.zenjob.bookrecommendation.api.model.user.UserRegistrationRequestModel;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 9:44 PM
 */
public interface UserFacade {

    ResponseModel<IdResponseModel> registerUser(final UserRegistrationRequestModel model);
}
