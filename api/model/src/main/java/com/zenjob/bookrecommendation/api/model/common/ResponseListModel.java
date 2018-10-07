package com.zenjob.bookrecommendation.api.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zenjob.bookrecommendation.api.model.common.enums.ErrorEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class ResponseListModel<T extends FacadeModel> extends BaseResponseModel<List<T>> {

    private static final long serialVersionUID = 5718585722318692983L;

    /* Properties */
    @JsonProperty("list")
    private List<T> response;

    @JsonProperty("count")
    private Long count;

    /* Constructors */
    public ResponseListModel() {
        super();
    }

    public ResponseListModel(final boolean success, final List<ErrorEnum> errorList, final List<T> response) {
        super(success, errorList);
        this.response = response;
    }

    public ResponseListModel(final List<T> response) {
        super();
        this.response = response;
        this.count = (long)response.size();
    }

    public ResponseListModel(final List<T> response, final Long count) {
        super();
        this.response = response;
        this.count = count;
    }

    public List<T> getResponse() {
        return response;
    }

    public void setResponse(List<T> response) {
        this.response = response;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResponseListModel)) {
            return false;
        }
        ResponseListModel rhs = (ResponseListModel) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.response, rhs.response)
                .append(this.count, rhs.count)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(response)
                .append(count)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("response", response)
                .append("count", count)
                .toString();
    }
}