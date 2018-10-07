package com.zenjob.bookrecommendation.persistence.feedback.entity;

import com.zenjob.bookrecommendation.persistence.book.entity.Book;
import com.zenjob.bookrecommendation.persistence.common.entity.AbstractDomainEntityModel;
import com.zenjob.bookrecommendation.persistence.feedback.enums.FeedbackEnum;
import com.zenjob.bookrecommendation.persistence.user.entity.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 7:02 PM
 */
@Entity
@Table(name = "feedback")
public class Feedback extends AbstractDomainEntityModel {

    private static final long serialVersionUID = -8175384297516412949L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "feedback")
    private FeedbackEnum feedback;

    public Book getBook() {
        return book;
    }

    public void setBook(final Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public FeedbackEnum getFeedback() {
        return feedback;
    }

    public void setFeedback(final FeedbackEnum feedback) {
        this.feedback = feedback;
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
        Feedback rhs = (Feedback) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(getIdOrNull(this.book), getIdOrNull(rhs.book))
                .append(getIdOrNull(this.user), getIdOrNull(rhs.user))
                .append(this.feedback, rhs.feedback)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(getIdOrNull(book))
                .append(getIdOrNull(user))
                .append(feedback)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("book", getIdOrNull(book))
                .append("user", getIdOrNull(user))
                .append("feedback", feedback)
                .toString();
    }
}
