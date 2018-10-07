package com.zenjob.bookrecommendation.persistence.userbookpreferences.entity;

import com.zenjob.bookrecommendation.persistence.common.entity.AbstractDomainEntityModel;
import com.zenjob.bookrecommendation.persistence.common.converter.*;
import com.zenjob.bookrecommendation.persistence.user.entity.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 7:32 PM
 */
@Entity
@Table(name = "user_book_preferances")
public class UserBookPreference extends AbstractDomainEntityModel {

    private static final long serialVersionUID = -8132133919157296839L;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "preferred_genres")
    @Convert(converter = StringListConverter.class)
    private List<String> preferredGenres;

    @Column(name = "preferred_languages")
    @Convert(converter = StringListConverter.class)
    private List<String> preferredLanguages;

    @Column(name = "max_page")
    private Integer maxPage;

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public List<String> getPreferredGenres() {
        return preferredGenres;
    }

    public void setPreferredGenres(final List<String> preferredGenres) {
        this.preferredGenres = preferredGenres;
    }

    public List<String> getPreferredLanguages() {
        return preferredLanguages;
    }

    public void setPreferredLanguages(final List<String> preferredLanguages) {
        this.preferredLanguages = preferredLanguages;
    }

    public Integer getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(final Integer maxPage) {
        this.maxPage = maxPage;
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
        UserBookPreference rhs = (UserBookPreference) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(getIdOrNull(this.user), getIdOrNull(rhs.user))
                .append(this.preferredGenres, rhs.preferredGenres)
                .append(this.preferredLanguages, rhs.preferredLanguages)
                .append(this.maxPage, rhs.maxPage)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(getIdOrNull(user))
                .append(preferredGenres)
                .append(preferredLanguages)
                .append(maxPage)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("user", getIdOrNull(user))
                .append("preferredGenres", preferredGenres)
                .append("preferredLanguages", preferredLanguages)
                .append("maxPage", maxPage)
                .toString();
    }
}
