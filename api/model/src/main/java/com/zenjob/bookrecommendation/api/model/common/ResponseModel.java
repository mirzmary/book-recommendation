package com.zenjob.bookrecommendation.api.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ResponseModel<T extends FacadeModel> extends BaseResponseModel<T> {

    private static final long serialVersionUID = 5718585722318692983L;

    @JsonProperty("entity")
    private T response;

    public ResponseModel() {
        super();
    }

    public ResponseModel(final T response) {
        super();
        this.response = response;
    }
    
    public T getResponse() {
        return response;
    }

    public void setResponse(final T response) {
        this.response = response;
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
        final ResponseModel rhs = (ResponseModel) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.response, rhs.response)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(response)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("response", response)
                .toString();
    }
}