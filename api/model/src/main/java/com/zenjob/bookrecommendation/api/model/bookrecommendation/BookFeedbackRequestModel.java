package com.zenjob.bookrecommendation.api.model.bookrecommendation;

import com.zenjob.bookrecommendation.api.model.common.RequestModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 11:36 PM
 */
public class BookFeedbackRequestModel extends RequestModel {

    private static final long serialVersionUID = 8088338226241142604L;

    private Long userId;

    private Long bookId;

    private String feedback;

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

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(final String feedback) {
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
        BookFeedbackRequestModel rhs = (BookFeedbackRequestModel) obj;
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
