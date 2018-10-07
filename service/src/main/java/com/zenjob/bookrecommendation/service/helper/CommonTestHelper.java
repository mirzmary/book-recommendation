package com.zenjob.bookrecommendation.service.helper;


import com.zenjob.bookrecommendation.persistence.book.entity.Book;
import com.zenjob.bookrecommendation.persistence.book.enums.Genre;
import com.zenjob.bookrecommendation.persistence.book.enums.Language;
import com.zenjob.bookrecommendation.persistence.feedback.entity.Feedback;
import com.zenjob.bookrecommendation.persistence.feedback.enums.FeedbackEnum;
import com.zenjob.bookrecommendation.persistence.user.entity.User;
import com.zenjob.bookrecommendation.persistence.userbookpreferences.entity.UserBookPreference;
import com.zenjob.bookrecommendation.service.feedback.dto.FeedbackDto;
import com.zenjob.bookrecommendation.service.user.dto.UserDto;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommonTestHelper {

    public static final Integer BOOK_RECOMMENDATION_COUNT = 1;
    public static final String USER_LOGIN = "test";
    public static final String USER_FIRST_NAME = "Amy";
    public static final String USER_LAST_NAME = "Green";
    public static final LocalDate USER_BIRTH_DATE = LocalDate.now().minusYears(24);

    public Book createBook() {
        final Book book = new Book();
        book.setName("Harry Potter");
        book.setGenre(Genre.FICTION);
        book.setLanguage(Language.ENGLISH);
        book.setPageCount(560);
        book.setMinAge(6);

        return book;
    }

    public User createUser() {
        final User user = new User();
        user.setLogin(USER_LOGIN);
        user.setFirstName(USER_FIRST_NAME);
        user.setLastName(USER_LAST_NAME);
        user.setBirthDate(USER_BIRTH_DATE);

        return user;
    }

    public UserBookPreference createUserPreference(final User user) {
        final UserBookPreference userBookPreference = new UserBookPreference();
        userBookPreference.setMaxPage(700);
        userBookPreference.setPreferredLanguages(Collections.singletonList(Language.ENGLISH.name()));
        userBookPreference.setPreferredGenres(Collections.singletonList(Genre.FICTION.name()));
        userBookPreference.setUser(user);
        return userBookPreference;
    }

    public List<Feedback> createOnlyPositiveFeedbacks() {
        final List<Feedback> feedbacks = new ArrayList<>();
        final Feedback feedback = new Feedback();
        feedback.setUser(createUser());
        feedback.setBook(createBook());
        feedback.setFeedback(FeedbackEnum.LIKE);
        feedbacks.add(feedback);
        return feedbacks;
    }

    public List<Feedback> createOnlyNegativeFeedbacks() {
        final List<Feedback> feedbacks = new ArrayList<>();
        final Feedback feedback = new Feedback();
        feedback.setUser(createUser());
        feedback.setBook(createBook());
        feedback.setFeedback(FeedbackEnum.DISLIKE);
        feedbacks.add(feedback);
        return feedbacks;
    }

    public List<Feedback> createEqualPositiveAndNegativeFeedbacks() {
        final List<Feedback> feedbacks = createOnlyPositiveFeedbacks();
        feedbacks.addAll(createOnlyNegativeFeedbacks());
        return feedbacks;
    }

    public List<Feedback> createMorePositiveThandNegativeFeedbacks() {
        final List<Feedback> feedbacks = createOnlyPositiveFeedbacks();
        feedbacks.addAll(createOnlyPositiveFeedbacks());
        feedbacks.addAll(createOnlyPositiveFeedbacks());
        feedbacks.addAll(createOnlyNegativeFeedbacks());
        return feedbacks;
    }

    public List<Feedback> createMoreNegativeThanPositiveFeedbacks() {
        final List<Feedback> feedbacks = createOnlyNegativeFeedbacks();
        feedbacks.addAll(createOnlyNegativeFeedbacks());
        feedbacks.addAll(createOnlyNegativeFeedbacks());
        feedbacks.addAll(createOnlyNegativeFeedbacks());
        feedbacks.addAll(createOnlyNegativeFeedbacks());
        feedbacks.addAll(createOnlyPositiveFeedbacks());

        return feedbacks;
    }

    public UserDto createUserDto() {
        final UserDto user = new UserDto();
        user.setLogin(USER_LOGIN);
        user.setFirstName(USER_FIRST_NAME);
        user.setLastName(USER_LAST_NAME);
        user.setBirthDate(USER_BIRTH_DATE);
        user.setPreferredGenres(Collections.singletonList(Genre.FICTION));
        user.setPreferredLanguages(Collections.singletonList(Language.ENGLISH));
        user.setMaxPreferredPage(700);
        return user;
    }


    public Feedback createFeedback(final User user, final Book book) {
        final Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setBook(book);
        feedback.setFeedback(FeedbackEnum.LIKE);
        return feedback;
    }

    public FeedbackDto createFeedbackDto() {
        final FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setBookId(1L);
        feedbackDto.setUserId(1L);
        feedbackDto.setFeedback(FeedbackEnum.LIKE);
        return feedbackDto;
    }
}
