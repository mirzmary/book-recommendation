package com.zenjob.bookrecommendation.service.user.impl;

import com.zenjob.bookrecommendation.persistence.user.UserRepository;
import com.zenjob.bookrecommendation.persistence.user.entity.User;
import com.zenjob.bookrecommendation.persistence.userbookpreferences.UserBookPreferenceRepository;
import com.zenjob.bookrecommendation.persistence.userbookpreferences.entity.UserBookPreference;
import com.zenjob.bookrecommendation.service.common.exception.LoggerAwareServiceRuntimeException;
import com.zenjob.bookrecommendation.service.user.UserService;
import com.zenjob.bookrecommendation.service.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 7:39 PM
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBookPreferenceRepository userBookPreferenceRepository;

    @Override
    public boolean checkIfUserExistsForLogin(final String login) {
        Assert.hasText(login, "Login should not be null when validating if a user with the same login exists");
        final User user = userRepository.findByLogin(login);
        return user != null;
    }

    @Override
    @Transactional
    public User register(final UserDto userDto) {
        assertUserDto(userDto);
        if(checkIfUserExistsForLogin(userDto.getLogin())) {
            throw new LoggerAwareServiceRuntimeException(this, "User with the same login %s already exists", userDto.getLogin());
        }
        else {
            final User user = convertUserDtoToEntity(userDto);
            userRepository.save(user);

            final UserBookPreference userPreference = createUserPreference(userDto);
            userPreference.setUser(user);
            userBookPreferenceRepository.save(userPreference);
            return user;
        }
    }

    private UserBookPreference createUserPreference(final UserDto userDto) {
        final UserBookPreference userBookPreference = new UserBookPreference();
        userBookPreference.setPreferredGenres(userDto.getPreferredGenres().stream().map(Enum::name).collect(Collectors.toList()));
        userBookPreference.setPreferredLanguages(userDto.getPreferredLanguages().stream().map(Enum::name).collect(Collectors.toList()));
        userBookPreference.setMaxPage(userDto.getMaxPreferredPage());
        return userBookPreference;
    }

    @Override
    public User getById(final Long userId) {
        Assert.notNull(userId, "userId can not be null when retrieving user by userId");
        return Optional.ofNullable(userRepository.findOne(userId))
                .orElseThrow(() -> new LoggerAwareServiceRuntimeException(this, "Could not find a user by specific id %s", userId));
    }

    @Override
    public UserBookPreference getByUserId(final Long userId) {
        Assert.notNull(userId, "userId should not be null when getting user preference");
        return Optional.ofNullable(userBookPreferenceRepository.findByUserId(userId))
                .orElseThrow(() -> new LoggerAwareServiceRuntimeException(this, "Could not find book preferences for User with id %s ", userId));
    }

    private User convertUserDtoToEntity(final UserDto userDto) {
        final User user = new User();
        user.setLogin(userDto.getLogin());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setBirthDate(userDto.getBirthDate());

        return user;
    }

    private void assertUserDto(final UserDto userDto) {
        Assert.notNull(userDto, "userDto can not be null when registering a new user");
        Assert.hasText(userDto.getLogin(), "User login should not be null when registering a new user");
        Assert.hasText(userDto.getFirstName(), "User first name should not be null when registering a new user");
        Assert.hasText(userDto.getLastName(), "User last name should not be null when registering a new user");
        Assert.notNull(userDto.getBirthDate(), "User birth date should not be null when registering a new user");
        Assert.notEmpty(userDto.getPreferredGenres(), "User preferred genres should not be empty when registering a new user");
        Assert.notEmpty(userDto.getPreferredLanguages(), "User preferred languages should not be empty when registering a new user");
        Assert.notNull(userDto.getMaxPreferredPage(), "User preferred maximum count of pages should not be null when registering a new user");
    }
}
