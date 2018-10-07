package com.zenjob.bookrecommendation.service.helper;

import com.zenjob.bookrecommendation.persistence.book.BookRepository;
import com.zenjob.bookrecommendation.persistence.book.entity.Book;
import com.zenjob.bookrecommendation.persistence.feedback.FeedBackRepository;
import com.zenjob.bookrecommendation.persistence.feedback.entity.Feedback;
import com.zenjob.bookrecommendation.persistence.feedback.enums.FeedbackEnum;
import com.zenjob.bookrecommendation.persistence.user.UserRepository;
import com.zenjob.bookrecommendation.persistence.user.entity.User;
import com.zenjob.bookrecommendation.persistence.userbookpreferences.UserBookPreferenceRepository;
import com.zenjob.bookrecommendation.persistence.userbookpreferences.entity.UserBookPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ServiceIntegrationTestHelper extends CommonTestHelper {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedBackRepository feedBackRepository;

    @Autowired
    private UserBookPreferenceRepository userBookPreferenceRepository;

    public Book createAndPersistBook() {
        final Book book = createBook();
        return bookRepository.save(book);
    }

    public User createAndPersistUser() {
        final User user = createUser();
        final User existingUser = userRepository.findByLogin(user.getLogin());
        if (existingUser != null) {
            return existingUser;
        } else {
            return userRepository.save(user);
        }
    }

    public UserBookPreference createAndPersistUserBookPreferences(final User user) {
        final UserBookPreference userPreference = createUserPreference(user);
        return userBookPreferenceRepository.save(userPreference);
    }

    public Feedback createAndPersistFeedback(final User user, final Book book) {
        final Feedback feedback = createFeedback(user, book);
        return feedBackRepository.save(feedback);
    }
}
