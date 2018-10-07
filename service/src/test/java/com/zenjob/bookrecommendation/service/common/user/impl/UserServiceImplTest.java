package com.zenjob.bookrecommendation.service.common.user.impl;

import com.zenjob.bookrecommendation.persistence.book.entity.Book;
import com.zenjob.bookrecommendation.persistence.user.UserRepository;
import com.zenjob.bookrecommendation.persistence.user.entity.User;
import com.zenjob.bookrecommendation.persistence.userbookpreferences.UserBookPreferenceRepository;
import com.zenjob.bookrecommendation.persistence.userbookpreferences.entity.UserBookPreference;
import com.zenjob.bookrecommendation.service.common.AbstractServiceImplTest;
import com.zenjob.bookrecommendation.service.common.exception.LoggerAwareServiceRuntimeException;
import com.zenjob.bookrecommendation.service.helper.CommonTestHelper;
import com.zenjob.bookrecommendation.service.user.UserService;
import com.zenjob.bookrecommendation.service.user.dto.UserDto;
import com.zenjob.bookrecommendation.service.user.impl.UserServiceImpl;
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
 * Time: 1:09 AM
 */
public class UserServiceImplTest extends AbstractServiceImplTest {

    @TestSubject
    private UserService userService = new UserServiceImpl();

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserBookPreferenceRepository userBookPreferenceRepository;

    // region register user

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterUserWithNullDto() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        userService.register(null);
        // Verify
        verifyAll();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterUserWithIllegalDtoNullLogin() throws Exception {
        // Test data
        final UserDto userDto = getHelper().createUserDto();
        userDto.setLogin(null);
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        userService.register(userDto);
        // Verify
        verifyAll();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterUserWithIllegalDtoNullFirstName() throws Exception {
        // Test data
        final UserDto userDto = getHelper().createUserDto();
        userDto.setFirstName(null);
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        userService.register(userDto);
        // Verify
        verifyAll();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterUserWithIllegalDtoNullLastName() throws Exception {
        // Test data
        final UserDto userDto = getHelper().createUserDto();
        userDto.setLastName(null);
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        userService.register(userDto);
        // Verify
        verifyAll();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterUserWithIllegalDtoNullBirthDate() throws Exception {
        // Test data
        final UserDto userDto = getHelper().createUserDto();
        userDto.setBirthDate(null);
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        userService.register(userDto);
        // Verify
        verifyAll();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterUserWithIllegalDtoEmptyGenres() throws Exception {
        // Test data
        final UserDto userDto = getHelper().createUserDto();
        userDto.setPreferredGenres(new ArrayList<>());
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        userService.register(userDto);
        // Verify
        verifyAll();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterUserWithIllegalDtoEmptyLanguages() throws Exception {
        // Test data
        final UserDto userDto = getHelper().createUserDto();
        userDto.setPreferredLanguages(new ArrayList<>());
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        userService.register(userDto);
        // Verify
        verifyAll();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterUserWithIllegalDtoNullMaxCount() throws Exception {
        // Test data
        final UserDto userDto = getHelper().createUserDto();
        userDto.setMaxPreferredPage(null);
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        userService.register(userDto);
        // Verify
        verifyAll();
    }

    @Test(expected = LoggerAwareServiceRuntimeException.class)
    public void testRegisterUserWithExistingLogin() throws Exception {
        // Test data
        final UserDto userDto = getHelper().createUserDto();

        // Reset
        resetAll();
        // Expectations
        expect(userRepository.findByLogin(userDto.getLogin())).andReturn(getHelper().createUser());
        // Replay
        replayAll();

        // Run test scenario
        userService.register(userDto);
        // Verify
        verifyAll();
    }

    @Test
    public void testRegisterUserNormalFlow() throws Exception {
        // Test data
        final UserDto userDto = getHelper().createUserDto();
        final User user = getHelper().createUser();
        final UserBookPreference userBookPreference = getHelper().createUserPreference(user);
        // Reset
        resetAll();
        // Expectations
        expect(userRepository.findByLogin(userDto.getLogin())).andReturn(null);
        expect(userRepository.save(user)).andReturn(user);
        expect(userBookPreferenceRepository.save(userBookPreference)).andReturn(userBookPreference);
        // Replay
        replayAll();

        // Run test scenario
        final User registeredUser = userService.register(userDto);
        // Verify
        Assert.assertEquals(user, registeredUser);
        verifyAll();
    }

    // endregion

    // region get by id

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIdWithIllegalArguments() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        userService.getById(null);
        // Verify
        verifyAll();
    }

    @Test(expected = LoggerAwareServiceRuntimeException.class)
    public void testGetByIdWithNoBookFound() throws Exception {
        // Test data
        final Long userId = 1L;
        // Reset
        resetAll();
        // Expectations
        expect(userRepository.findOne(userId)).andReturn(null);
        // Replay
        replayAll();

        // Run test scenario
        userService.getById(userId);
        // Verify
        verifyAll();
    }

    @Test
    public void testGetByIdNormalFlow() throws Exception {
        // Test data
        final Long userId = 1L;
        final User user = getHelper().createUser();
        // Reset
        resetAll();
        // Expectations
        expect(userRepository.findOne(userId)).andReturn(user);
        // Replay
        replayAll();

        // Run test scenario
        final User result = userService.getById(userId);
        // Verify
        Assert.assertEquals(user, result);
        verifyAll();
    }

    // endregion

    // region check if user exists for login

    @Test(expected = IllegalArgumentException.class)
    public void testCheckIfUserExistsForLoginWithIllegalArgument() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        userService.checkIfUserExistsForLogin(null);
        // Verify
        verifyAll();
    }

    @Test
    public void testCheckIfUserExistsForLoginNotWhenExists() throws Exception {
        // Test data
        final String login = CommonTestHelper.USER_LOGIN;
        // Reset
        resetAll();
        // Expectations
        expect(userRepository.findByLogin(login)).andReturn(null);
        // Replay
        replayAll();

        // Run test scenario
        final boolean userExists = userService.checkIfUserExistsForLogin(login);
        // Verify
        Assert.assertFalse(userExists);
        verifyAll();
    }

    @Test
    public void testCheckIfUserExistsForLoginWhenExists() throws Exception {
        // Test data
        final String login = CommonTestHelper.USER_LOGIN;
        final User user = getHelper().createUser();
        // Reset
        resetAll();
        // Expectations
        expect(userRepository.findByLogin(login)).andReturn(user);
        // Replay
        replayAll();

        // Run test scenario
        final boolean userExists = userService.checkIfUserExistsForLogin(login);
        // Verify
        Assert.assertTrue(userExists);
        verifyAll();
    }

    // endregion

    // region get user book preferences by user

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserBookPreferencesByUserIdWithIllegalArgument() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        userService.getByUserId(null);
        // Verify
        verifyAll();
    }

    @Test(expected = LoggerAwareServiceRuntimeException.class)
    public void testGetUserBookPreferencesByUserIdWithNoPreferences() throws Exception {
        // Test data
        final Long userId = 1L;
        // Reset
        resetAll();
        // Expectations
        expect(userBookPreferenceRepository.findByUserId(userId)).andReturn(null);
        // Replay
        replayAll();

        // Run test scenario
        userService.getByUserId(userId);
        // Verify
        verifyAll();
    }

    @Test
    public void testGetUserBookPreferencesByUserIdNormalFlow() throws Exception {
        // Test data
        final Long userId = 1L;
        final User user = getHelper().createUser();
        final UserBookPreference userBookPreference = getHelper().createUserPreference(user);
        // Reset
        resetAll();
        // Expectations
        expect(userBookPreferenceRepository.findByUserId(userId)).andReturn(userBookPreference);
        // Replay
        replayAll();

        // Run test scenario
        final UserBookPreference result = userService.getByUserId(userId);
        // Verify
        Assert.assertEquals(userBookPreference, result);
        verifyAll();
    }

    // endregion
}
