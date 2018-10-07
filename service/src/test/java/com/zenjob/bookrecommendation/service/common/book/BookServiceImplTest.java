package com.zenjob.bookrecommendation.service.common.book;

import com.zenjob.bookrecommendation.persistence.book.BookRepository;
import com.zenjob.bookrecommendation.persistence.book.entity.Book;
import com.zenjob.bookrecommendation.persistence.book.enums.Genre;
import com.zenjob.bookrecommendation.persistence.book.enums.Language;
import com.zenjob.bookrecommendation.persistence.user.entity.User;
import com.zenjob.bookrecommendation.persistence.userbookpreferences.entity.UserBookPreference;
import com.zenjob.bookrecommendation.service.book.BookService;
import com.zenjob.bookrecommendation.service.book.impl.BookServiceImpl;
import com.zenjob.bookrecommendation.service.common.AbstractServiceImplTest;
import com.zenjob.bookrecommendation.service.common.exception.LoggerAwareServiceRuntimeException;
import com.zenjob.bookrecommendation.service.feedback.FeedbackService;
import com.zenjob.bookrecommendation.service.helper.CommonTestHelper;
import com.zenjob.bookrecommendation.service.user.UserService;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.easymock.EasyMock.anyLong;
import static org.easymock.EasyMock.expect;

/**
 * User: Mary Mirzoyan
 * Date: 10/8/18
 * Time: 12:32 AM
 */
public class BookServiceImplTest extends AbstractServiceImplTest {

    @TestSubject
    private BookService bookService = new BookServiceImpl();

    @Before
    public void beforeTestStart() {
        ReflectionTestUtils.setField(bookService, "recommendationsCount", CommonTestHelper.BOOK_RECOMMENDATION_COUNT);
    }

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserService userService;

    @Mock
    private FeedbackService feedbackService;

    // region book recommendation

    @Test(expected = IllegalArgumentException.class)
    public void testBookRecommendationWithIllegalArguments() throws Exception {
        // Test data
        // Reset
        resetAll();
        // Expectations

        // Replay
        replayAll();

        // Run test scenario
        bookService.recommendBooksToUser(null);
        // Verify
        verifyAll();
    }

    @Test
    public void testBookRecommendationWithNoBooksToRecommend() throws Exception {
        // Test data
        final Long userId = 1L;
        final User user = getHelper().createUser();
        final UserBookPreference userBookPreference = getHelper().createUserPreference(user);
        final List<Genre> genres = userBookPreference.getPreferredGenres().stream().map(Genre::valueOf).collect(Collectors.toList());
        final List<Language> languages = userBookPreference.getPreferredLanguages().stream().map(Language::valueOf).collect(Collectors.toList());
        final Integer userAge = (int) ChronoUnit.YEARS.between(user.getBirthDate(), LocalDate.now());
        // Reset
        resetAll();
        // Expectations
        expect(userService.getById(userId)).andReturn(user);
        expect(userService.getByUserId(userId)).andReturn(userBookPreference);
        expect(bookRepository.findByGenreInAndLanguageInAndPageCountLessThanEqualAndMinAgeLessThanEqual(genres, languages, userBookPreference.getMaxPage(), userAge)).andReturn(new ArrayList<>());
        // Replay
        replayAll();

        // Run test scenario
        final List<Book> books = bookService.recommendBooksToUser(userId);
        // Verify
        Assert.assertTrue(books.isEmpty());
        verifyAll();
    }

    @Test
    public void testBookRecommendationWithLessThanNeededToRecommend() throws Exception {
        // Test data
        final Long userId = 1L;
        final User user = getHelper().createUser();
        final UserBookPreference userBookPreference = getHelper().createUserPreference(user);
        final List<Genre> genres = userBookPreference.getPreferredGenres().stream().map(Genre::valueOf).collect(Collectors.toList());
        final List<Language> languages = userBookPreference.getPreferredLanguages().stream().map(Language::valueOf).collect(Collectors.toList());
        final Integer userAge = (int) ChronoUnit.YEARS.between(user.getBirthDate(), LocalDate.now());

        final Book bookToRecommend = getHelper().createBook();
        // Reset
        resetAll();
        // Expectations
        expect(userService.getById(userId)).andReturn(user);
        expect(userService.getByUserId(userId)).andReturn(userBookPreference);
        expect(bookRepository.findByGenreInAndLanguageInAndPageCountLessThanEqualAndMinAgeLessThanEqual(genres, languages, userBookPreference.getMaxPage(), userAge)).andReturn(Collections.singletonList(bookToRecommend));
        // Replay
        replayAll();

        // Run test scenario
        final List<Book> books = bookService.recommendBooksToUser(userId);
        // Verify
        Assert.assertFalse(books.isEmpty());
        Assert.assertEquals(books.size(), 1);
        Assert.assertTrue(books.contains(bookToRecommend));
        verifyAll();
    }

    @Test
    public void testBookRecommendationWithMoreThanNeededToRecommendOnlyPositiveFeedbacks() throws Exception {
        // Test data
        final Long userId = 1L;
        final User user = getHelper().createUser();
        final UserBookPreference userBookPreference = getHelper().createUserPreference(user);
        final List<Genre> genres = userBookPreference.getPreferredGenres().stream().map(Genre::valueOf).collect(Collectors.toList());
        final List<Language> languages = userBookPreference.getPreferredLanguages().stream().map(Language::valueOf).collect(Collectors.toList());
        final Integer userAge = (int) ChronoUnit.YEARS.between(user.getBirthDate(), LocalDate.now());

        final Book bookToRecommend = getHelper().createBook();
        final Book anotherBookToRecommend = getHelper().createBook();
        final List<Book> recommendedBooks = new ArrayList<>();
        recommendedBooks.add(bookToRecommend);
        recommendedBooks.add(anotherBookToRecommend);


        // Reset
        resetAll();
        // Expectations
        expect(userService.getById(userId)).andReturn(user);
        expect(userService.getByUserId(userId)).andReturn(userBookPreference);
        expect(bookRepository.findByGenreInAndLanguageInAndPageCountLessThanEqualAndMinAgeLessThanEqual(genres, languages, userBookPreference.getMaxPage(), userAge)).andReturn(recommendedBooks);
        expect(feedbackService.getByBookId(anyLong())).andReturn(getHelper().createOnlyPositiveFeedbacks()).times(2);
        // Replay
        replayAll();

        // Run test scenario
        final List<Book> books = bookService.recommendBooksToUser(userId);
        // Verify
        Assert.assertFalse(books.isEmpty());
        Assert.assertEquals(books.size(), 1);
        verifyAll();
    }

    @Test
    public void testBookRecommendationWithMoreThanNeededToRecommendHalfPositiveFeedbacks() throws Exception {
        // Test data
        final Long userId = 1L;
        final User user = getHelper().createUser();
        final UserBookPreference userBookPreference = getHelper().createUserPreference(user);
        final List<Genre> genres = userBookPreference.getPreferredGenres().stream().map(Genre::valueOf).collect(Collectors.toList());
        final List<Language> languages = userBookPreference.getPreferredLanguages().stream().map(Language::valueOf).collect(Collectors.toList());
        final Integer userAge = (int) ChronoUnit.YEARS.between(user.getBirthDate(), LocalDate.now());

        final Book bookToRecommend = getHelper().createBook();
        final Book anotherBookToRecommend = getHelper().createBook();
        final List<Book> recommendedBooks = new ArrayList<>();
        recommendedBooks.add(bookToRecommend);
        recommendedBooks.add(anotherBookToRecommend);


        // Reset
        resetAll();
        // Expectations
        expect(userService.getById(userId)).andReturn(user);
        expect(userService.getByUserId(userId)).andReturn(userBookPreference);
        expect(bookRepository.findByGenreInAndLanguageInAndPageCountLessThanEqualAndMinAgeLessThanEqual(genres, languages, userBookPreference.getMaxPage(), userAge)).andReturn(recommendedBooks);
        expect(feedbackService.getByBookId(anyLong())).andReturn(getHelper().createOnlyPositiveFeedbacks());
        expect(feedbackService.getByBookId(anyLong())).andReturn(getHelper().createOnlyNegativeFeedbacks());
        // Replay
        replayAll();

        // Run test scenario
        final List<Book> books = bookService.recommendBooksToUser(userId);
        // Verify
        Assert.assertFalse(books.isEmpty());
        Assert.assertEquals(books.size(), 1);
        Assert.assertTrue(books.contains(bookToRecommend));
        verifyAll();
    }

    @Test
    public void testBookRecommendationWithMoreThanNeededToRecommendBothPositiveAndNegativeFeedbacks() throws Exception {
        // Test data
        final Long userId = 1L;
        final User user = getHelper().createUser();
        final UserBookPreference userBookPreference = getHelper().createUserPreference(user);
        final List<Genre> genres = userBookPreference.getPreferredGenres().stream().map(Genre::valueOf).collect(Collectors.toList());
        final List<Language> languages = userBookPreference.getPreferredLanguages().stream().map(Language::valueOf).collect(Collectors.toList());
        final Integer userAge = (int) ChronoUnit.YEARS.between(user.getBirthDate(), LocalDate.now());

        final Book bookToRecommend = getHelper().createBook();
        final Book anotherBookToRecommend = getHelper().createBook();
        final List<Book> recommendedBooks = new ArrayList<>();
        recommendedBooks.add(bookToRecommend);
        recommendedBooks.add(anotherBookToRecommend);


        // Reset
        resetAll();
        // Expectations
        expect(userService.getById(userId)).andReturn(user);
        expect(userService.getByUserId(userId)).andReturn(userBookPreference);
        expect(bookRepository.findByGenreInAndLanguageInAndPageCountLessThanEqualAndMinAgeLessThanEqual(genres, languages, userBookPreference.getMaxPage(), userAge)).andReturn(recommendedBooks);
        expect(feedbackService.getByBookId(anyLong())).andReturn(getHelper().createEqualPositiveAndNegativeFeedbacks());
        expect(feedbackService.getByBookId(anyLong())).andReturn(getHelper().createOnlyNegativeFeedbacks());
        // Replay
        replayAll();

        // Run test scenario
        final List<Book> books = bookService.recommendBooksToUser(userId);
        // Verify
        Assert.assertFalse(books.isEmpty());
        Assert.assertEquals(books.size(), 1);
        Assert.assertTrue(books.contains(bookToRecommend));
        verifyAll();
    }

    @Test
    public void testBookRecommendationWithMoreThanNeededToRecommendMoreNegativeThanPositiveFeedbacks() throws Exception {
        // Test data
        final Long userId = 1L;
        final User user = getHelper().createUser();
        final UserBookPreference userBookPreference = getHelper().createUserPreference(user);
        final List<Genre> genres = userBookPreference.getPreferredGenres().stream().map(Genre::valueOf).collect(Collectors.toList());
        final List<Language> languages = userBookPreference.getPreferredLanguages().stream().map(Language::valueOf).collect(Collectors.toList());
        final Integer userAge = (int) ChronoUnit.YEARS.between(user.getBirthDate(), LocalDate.now());

        final Book bookToRecommend = getHelper().createBook();
        final Book anotherBookToRecommend = getHelper().createBook();
        final List<Book> recommendedBooks = new ArrayList<>();
        recommendedBooks.add(bookToRecommend);
        recommendedBooks.add(anotherBookToRecommend);


        // Reset
        resetAll();
        // Expectations
        expect(userService.getById(userId)).andReturn(user);
        expect(userService.getByUserId(userId)).andReturn(userBookPreference);
        expect(bookRepository.findByGenreInAndLanguageInAndPageCountLessThanEqualAndMinAgeLessThanEqual(genres, languages, userBookPreference.getMaxPage(), userAge)).andReturn(recommendedBooks);
        expect(feedbackService.getByBookId(anyLong())).andReturn(getHelper().createMoreNegativeThanPositiveFeedbacks());
        expect(feedbackService.getByBookId(anyLong())).andReturn(getHelper().createMorePositiveThandNegativeFeedbacks());
        // Replay
        replayAll();

        // Run test scenario
        final List<Book> books = bookService.recommendBooksToUser(userId);
        // Verify
        Assert.assertFalse(books.isEmpty());
        Assert.assertEquals(books.size(), 1);
        Assert.assertTrue(books.contains(anotherBookToRecommend));
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
        bookService.getById(null);
        // Verify
        verifyAll();
    }

    @Test(expected = LoggerAwareServiceRuntimeException.class)
    public void testGetByIdWithNoBookFound() throws Exception {
        // Test data
        final Long bookId = 1L;
        // Reset
        resetAll();
        // Expectations
        expect(bookRepository.findOne(bookId)).andReturn(null);
        // Replay
        replayAll();

        // Run test scenario
        bookService.getById(bookId);
        // Verify
        verifyAll();
    }

    @Test
    public void testGetByIdNormalFlow() throws Exception {
        // Test data
        final Long bookId = 1L;
        final Book book = getHelper().createBook();
        // Reset
        resetAll();
        // Expectations
        expect(bookRepository.findOne(bookId)).andReturn(book);
        // Replay
        replayAll();

        // Run test scenario
        final Book result = bookService.getById(bookId);
        // Verify
        Assert.assertEquals(book, result);
        verifyAll();
    }

    // endregion
}
