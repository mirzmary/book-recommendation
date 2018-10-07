package com.zenjob.bookrecommendation.facade.user.impl;

import com.zenjob.bookrecommendation.api.model.common.FacadeModel;
import com.zenjob.bookrecommendation.api.model.common.IdResponseModel;
import com.zenjob.bookrecommendation.api.model.common.ResponseModel;
import com.zenjob.bookrecommendation.api.model.common.enums.ErrorEnum;
import com.zenjob.bookrecommendation.api.model.user.UserRegistrationRequestModel;
import com.zenjob.bookrecommendation.facade.user.UserFacade;
import com.zenjob.bookrecommendation.persistence.user.entity.User;
import com.zenjob.bookrecommendation.service.user.UserService;
import com.zenjob.bookrecommendation.service.user.dto.UserDto;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 10:34 PM
 */
@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    public ResponseModel<IdResponseModel> registerUser(final UserRegistrationRequestModel model) {
        assertUserRegistrationRequestModel(model);
        final ResponseModel<IdResponseModel> response = new ResponseModel<>();
        if(userService.checkIfUserExistsForLogin(model.getLogin())) {
            response.addError(ErrorEnum.USER_LOGIN_ALREADY_IN_USE);
            return response;
        }

        final UserDto userDto = mapperFacade.map(model, UserDto.class);
        final User user = userService.register(userDto);
        final IdResponseModel idResponseModel = new IdResponseModel(user.getId());
        response.setResponse(idResponseModel);
        return response;
    }


    private void assertUserRegistrationRequestModel(final UserRegistrationRequestModel userRegistrationRequestModel) {
        Assert.notNull(userRegistrationRequestModel, "userRegistrationRequestModel can not be null when registering a new user");
        Assert.notNull(userRegistrationRequestModel.getLogin(), "User login should not be null when registering a new user");
        Assert.notNull(userRegistrationRequestModel.getFirstName(), "User first name should not be null when registering a new user");
        Assert.notNull(userRegistrationRequestModel.getLastName(), "User last name should not be null when registering a new user");
        Assert.notNull(userRegistrationRequestModel.getBirthDate(), "User birth date should not be null when registering a new user");
        Assert.notEmpty(userRegistrationRequestModel.getPreferredGenres(), "User preferred genres should not be empty when registering a new user");
        Assert.notEmpty(userRegistrationRequestModel.getPreferredLanguages(), "User preferred languages should not be empty when registering a new user");
        Assert.notNull(userRegistrationRequestModel.getMaxPreferredPage(), "User preferred maximum count of pages should not be null when registering a new user");
    }
}
