package com.zenjob.bookrecommendation.service.feedback.dto;

import com.zenjob.bookrecommendation.persistence.feedback.enums.FeedbackEnum;
import com.zenjob.bookrecommendation.service.common.dto.BaseDTO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 8:43 PM
 */
public class FeedbackDto extends BaseDTO {

    private static final long serialVersionUID = 3774448763918794061L;

    private Long bookId;

    private Long userId;

    private FeedbackEnum feedback;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(final Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
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
        FeedbackDto rhs = (FeedbackDto) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.bookId, rhs.bookId)
                .append(this.userId, rhs.userId)
                .append(this.feedback, rhs.feedback)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(bookId)
                .append(userId)
                .append(feedback)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("bookId", bookId)
                .append("userId", userId)
                .append("feedback", feedback)
                .toString();
    }
}
