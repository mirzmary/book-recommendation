package com.zenjob.bookrecommendation.service.book.dto;

import com.zenjob.bookrecommendation.persistence.feedback.enums.FeedbackEnum;
import com.zenjob.bookrecommendation.service.common.dto.BaseDTO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 11:41 PM
 */
public class BookFeedbackDto extends BaseDTO {

    private static final long serialVersionUID = 6745016813776550496L;

    private Long userId;

    private Long bookId;

    private FeedbackEnum feedback;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(final Long bookId) {
        this.bookId = bookId;
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
        BookFeedbackDto rhs = (BookFeedbackDto) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.userId, rhs.userId)
                .append(this.bookId, rhs.bookId)
                .append(this.feedback, rhs.feedback)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(userId)
                .append(bookId)
                .append(feedback)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("userId", userId)
                .append("bookId", bookId)
                .append("feedback", feedback)
                .toString();
    }
}
