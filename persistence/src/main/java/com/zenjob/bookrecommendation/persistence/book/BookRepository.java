package com.zenjob.bookrecommendation.persistence.book;

import com.zenjob.bookrecommendation.persistence.book.entity.Book;
import com.zenjob.bookrecommendation.persistence.book.enums.Genre;
import com.zenjob.bookrecommendation.persistence.book.enums.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 12:52 PM
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByGenreInAndLanguageInAndPageCountLessThanEqualAndMinAgeLessThanEqual(final List<Genre> genres, final List<Language> languages, final Integer pageCount, final Integer minAge);

    List<Book> findByGenreIn(final List<Genre> genres);

    List<Book> findByLanguageIn(final List<Language> languages);

    List<Book> findByPageCountLessThanEqual(final Integer pageCount);

    List<Book> findByMinAgeGreaterThanEqual(final Integer minAge);


}
