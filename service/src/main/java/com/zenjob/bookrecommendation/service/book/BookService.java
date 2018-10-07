package com.zenjob.bookrecommendation.service.book;

import com.zenjob.bookrecommendation.persistence.book.entity.Book;

import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 1:08 PM
 */

public interface BookService {

    List<Book> recommendBooksToUser(final Long userId);

    Book getById(final Long bookId);
}
