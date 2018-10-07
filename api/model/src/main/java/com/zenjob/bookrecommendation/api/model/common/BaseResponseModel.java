package com.zenjob.bookrecommendation.api.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zenjob.bookrecommendation.api.model.common.enums.ErrorEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class BaseResponseModel<T> implements Serializable {

    private static final long serialVersionUID = 4567898765456789983L;

    @JsonProperty("success")
    protected boolean success;

    @JsonProperty("errorList")
    private List<ErrorEnum> errorList;

    public BaseResponseModel() {
        this.success = true;
        this.errorList = new ArrayList<>();
    }

    public BaseResponseModel(final boolean success, final List<ErrorEnum> errorList) {
        this.success = success;
        this.errorList = errorList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ErrorEnum> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ErrorEnum> errorList) {
        this.success = false;
        this.errorList = errorList;
    }

    public void addError(final ErrorEnum errorCode) {
        this.success = false;
        this.errorList.add(errorCode);
    }

    public void addErrors(final List<ErrorEnum> errorList) {
        this.success = false;
        this.errorList.addAll(errorList);
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
        BaseResponseModel rhs = (BaseResponseModel) obj;
        return new EqualsBuilder()
                .append(this.success, rhs.success)
                .append(this.errorList, rhs.errorList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(success)
                .append(errorList)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("success", success)
                .append("errorList", errorList)
                .toString();
    }
}
