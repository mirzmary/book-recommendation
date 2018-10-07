package com.zenjob.bookrecommendation.service.common.user;

import com.zenjob.bookrecommendation.persistence.user.entity.User;
import com.zenjob.bookrecommendation.persistence.userbookpreferences.entity.UserBookPreference;
import com.zenjob.bookrecommendation.service.common.AbstractServiceIntegrationTest;
import com.zenjob.bookrecommendation.service.user.UserService;
import com.zenjob.bookrecommendation.service.user.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: Mary Mirzoyan
 * Date: 10/8/18
 * Time: 1:06 AM
 */
public class UserServiceIntegrationTest extends AbstractServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetById() throws Exception {
        // Given
        final User user = getHelper().createAndPersistUser();
        flushAndClear();
        // When
        final User resultUser = userService.getById(user.getId());
        // Then
        Assert.assertEquals(user, resultUser);
    }

    @Test
    public void testGetUserPreferenceByUserId() throws Exception {
        // Given
        final User user = getHelper().createAndPersistUser();
        flushAndClear();
        final UserBookPreference userBookPreference = getHelper().createAndPersistUserBookPreferences(user);
        // When
        final UserBookPreference result = userService.getByUserId(user.getId());
        // Then
        Assert.assertEquals(userBookPreference, result);
    }

    @Test
    public void testCheckIfUserExistsForLogin() throws Exception {
        // Given
        final User user = getHelper().createAndPersistUser();
        flushAndClear();
        // When
        final boolean loginExists = userService.checkIfUserExistsForLogin(user.getLogin());
        // Then
        Assert.assertTrue(loginExists);
    }

    @Test
    public void testRegisterUser() throws Exception {
        // Given
        final UserDto userDto = getHelper().createUserDto();
        flushAndClear();
        // When
        final User user = userService.register(userDto);
        // Then
        Assert.assertNotNull(user);
        Assert.assertEquals(userDto.getLogin(), user.getLogin());
        Assert.assertEquals(userDto.getFirstName(), user.getFirstName());
        Assert.assertEquals(userDto.getLastName(), user.getLastName());
        Assert.assertEquals(userDto.getBirthDate(), user.getBirthDate());
    }
}
