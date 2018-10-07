package com.zenjob.bookrecommendation.service.book.impl;

import com.zenjob.bookrecommendation.persistence.book.BookRepository;
import com.zenjob.bookrecommendation.persistence.book.entity.Book;
import com.zenjob.bookrecommendation.persistence.book.enums.Genre;
import com.zenjob.bookrecommendation.persistence.book.enums.Language;
import com.zenjob.bookrecommendation.persistence.feedback.entity.Feedback;
import com.zenjob.bookrecommendation.persistence.feedback.enums.FeedbackEnum;
import com.zenjob.bookrecommendation.persistence.user.entity.User;
import com.zenjob.bookrecommendation.persistence.userbookpreferences.entity.UserBookPreference;
import com.zenjob.bookrecommendation.service.book.BookService;
import com.zenjob.bookrecommendation.service.common.exception.LoggerAwareServiceRuntimeException;
import com.zenjob.bookrecommendation.service.feedback.FeedbackService;
import com.zenjob.bookrecommendation.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 1:08 PM
 */
@Service
public class BookServiceImpl implements BookService {


    @Value("${book.recommendation.count}")
    private Integer recommendationsCount;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FeedbackService feedbackService;

    @Override
    public List<Book> recommendBooksToUser(final Long userId) {
        Assert.notNull(userId, "User Id can not be null when recommending a book");
        final User user = userService.getById(userId);
        final UserBookPreference userBookPreference = userService.getByUserId(userId);
        final List<Genre> genres = userBookPreference.getPreferredGenres().stream().map(Genre::valueOf).collect(Collectors.toList());
        final List<Language> languages = userBookPreference.getPreferredLanguages().stream().map(Language::valueOf).collect(Collectors.toList());
        final Integer userAge = (int) ChronoUnit.YEARS.between(user.getBirthDate(), LocalDate.now());
        final List<Book> books = bookRepository.findByGenreInAndLanguageInAndPageCountLessThanEqualAndMinAgeLessThanEqual(genres, languages, userBookPreference.getMaxPage(), userAge);
        if (books.size() <= recommendationsCount) {
            return books;
        } else {
            final Map<Book, BigDecimal> bookPositiveFeedbacks = new HashMap<>();
            for (final Book book : books) {
                final List<Feedback> feedbacks = feedbackService.getByBookId(book.getId());
                BigDecimal ratio = BigDecimal.ZERO;
                if (!feedbacks.isEmpty()) {
                    final long negativeFeedbacks = feedbacks.stream().filter(f -> f.getFeedback().equals(FeedbackEnum.DISLIKE)).count();
                    final long positiveFeedbacks = feedbacks.stream().filter(f -> f.getFeedback().equals(FeedbackEnum.LIKE)).count();
                    ratio = BigDecimal.valueOf(negativeFeedbacks == 0 ? 1000 : positiveFeedbacks / negativeFeedbacks).setScale(2, RoundingMode.HALF_DOWN);
                }
                bookPositiveFeedbacks.put(book, ratio);
            }

            final LinkedHashMap<Book, BigDecimal> sortedBooks = bookPositiveFeedbacks.entrySet().stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                            LinkedHashMap::new));
            return new ArrayList<>(sortedBooks.keySet()).subList(0, recommendationsCount);
        }

    }

    @Override
    public Book getById(final Long bookId) {
        Assert.notNull(bookId, "bookId can not be null when retrieving book by id");
        return Optional.ofNullable(bookRepository.findOne(bookId))
                .orElseThrow(() -> new LoggerAwareServiceRuntimeException(this, "Could not find a book by specific id %s", bookId));
    }
}
