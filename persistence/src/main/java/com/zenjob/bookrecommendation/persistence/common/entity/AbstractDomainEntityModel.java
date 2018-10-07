package com.zenjob.bookrecommendation.persistence.common.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@MappedSuperclass
public abstract class AbstractDomainEntityModel implements Serializable {

    private static final long serialVersionUID = -4859229849741225529L;
    private static final long DATE_MINUTES_DIFF = 5;

    /* Fields */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "remove_date")
    private LocalDateTime removeDate;

    public AbstractDomainEntityModel() {
        creationDate = LocalDateTime.now();
        updateDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public LocalDateTime getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(LocalDateTime removeDate) {
        this.removeDate = removeDate;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        final AbstractDomainEntityModel rhs = (AbstractDomainEntityModel) obj;
        return new EqualsBuilder()
                .append(this.id, rhs.id)
                .isEquals() &&
                dateTimesNearlyEqual(this.creationDate,rhs.creationDate) &&
                dateTimesNearlyEqual(this.updateDate,rhs.updateDate) &&
                dateTimesNearlyEqual(this.removeDate,rhs.removeDate);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(creationDate)
                .append(updateDate)
                .append(removeDate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("creationDate", creationDate)
                .append("updateDate", updateDate)
                .append("removeDate", removeDate)
                .toString();
    }

    public static Long getIdOrNull(final AbstractDomainEntityModel entity) {
        return entity != null ? entity.getId() : null;
    }

    protected static boolean dateTimesNearlyEqual(final LocalDateTime dateTime, final LocalDateTime otherDateTime){
        return dateTime == null && otherDateTime == null || dateTime != null && otherDateTime != null && dateTime.until(otherDateTime, ChronoUnit.MINUTES) <= DATE_MINUTES_DIFF;
    }
}