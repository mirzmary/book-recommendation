package com.zenjob.bookrecommendation.service.common.book;

import com.zenjob.bookrecommendation.persistence.book.entity.Book;
import com.zenjob.bookrecommendation.persistence.user.entity.User;
import com.zenjob.bookrecommendation.service.book.BookService;
import com.zenjob.bookrecommendation.service.common.AbstractServiceIntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 2:02 PM
 */
public class BookServiceIntegrationTest extends AbstractServiceIntegrationTest {

    @Autowired
    private BookService bookService;

    @Test
    public void test() throws Exception {
        //Given
        final Book book = getHelper().createAndPersistBook();
        final User user = getHelper().createAndPersistUser();
        flushAndClear();
        getHelper().createAndPersistUserBookPreferences(user);
        // When
        final List<Book> books = bookService.recommendBooksToUser(user.getId());
        // Then
        Assert.assertTrue(books.contains(book));
    }
}
