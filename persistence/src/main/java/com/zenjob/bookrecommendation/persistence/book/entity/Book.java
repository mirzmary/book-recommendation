package com.zenjob.bookrecommendation.persistence.book.entity;

import com.zenjob.bookrecommendation.persistence.book.enums.Genre;
import com.zenjob.bookrecommendation.persistence.book.enums.Language;
import com.zenjob.bookrecommendation.persistence.common.entity.AbstractDomainEntityModel;
import com.zenjob.bookrecommendation.persistence.feedback.entity.Feedback;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 12:52 PM
 */
@Entity
@Table(name = "book")
public class Book extends AbstractDomainEntityModel {

    private static final long serialVersionUID = 9013411584567872238L;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private Genre genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Language language;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "min_age_restriction")
    private Integer minAge;

    @OneToMany(mappedBy = "book")
    private List<Feedback> bookFeedbacks;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(final Genre genre) {
        this.genre = genre;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(final Language language) {
        this.language = language;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(final Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(final Integer minAge) {
        this.minAge = minAge;
    }

    public List<Feedback> getBookFeedbacks() {
        return bookFeedbacks;
    }

    public void setBookFeedbacks(final List<Feedback> bookFeedbacks) {
        this.bookFeedbacks = bookFeedbacks;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Book rhs = (Book) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.name, rhs.name)
                .append(this.author, rhs.author)
                .append(this.genre, rhs.genre)
                .append(this.language, rhs.language)
                .append(this.pageCount, rhs.pageCount)
                .append(this.minAge, rhs.minAge)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(name)
                .append(author)
                .append(genre)
                .append(language)
                .append(pageCount)
                .append(minAge)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("name", name)
                .append("author", author)
                .append("genre", genre)
                .append("language", language)
                .append("pageCount", pageCount)
                .append("minAge", minAge)
                .toString();
    }
}
