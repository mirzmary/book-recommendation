package com.zenjob.bookrecommendation.service.user;

import com.zenjob.bookrecommendation.persistence.user.entity.User;
import com.zenjob.bookrecommendation.persistence.userbookpreferences.entity.UserBookPreference;
import com.zenjob.bookrecommendation.service.user.dto.UserDto;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 7:39 PM
 */
public interface UserService {

    boolean checkIfUserExistsForLogin(final String login);

    User register(final UserDto userDto);

    User getById(final Long userId);

    UserBookPreference getByUserId(final Long userId);
}
