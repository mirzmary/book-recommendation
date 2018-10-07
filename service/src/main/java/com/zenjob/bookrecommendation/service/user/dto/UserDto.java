package com.zenjob.bookrecommendation.service.user.dto;

import com.zenjob.bookrecommendation.persistence.book.enums.Genre;
import com.zenjob.bookrecommendation.persistence.book.enums.Language;
import com.zenjob.bookrecommendation.service.common.dto.BaseDTO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;
import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 7:54 PM
 */
public class UserDto extends BaseDTO {

    private static final long serialVersionUID = -888642810699802823L;

    private String login;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private List<Genre> preferredGenres;

    private List<Language> preferredLanguages;

    private Integer maxPreferredPage;

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Genre> getPreferredGenres() {
        return preferredGenres;
    }

    public void setPreferredGenres(final List<Genre> preferredGenres) {
        this.preferredGenres = preferredGenres;
    }

    public List<Language> getPreferredLanguages() {
        return preferredLanguages;
    }

    public void setPreferredLanguages(final List<Language> preferredLanguages) {
        this.preferredLanguages = preferredLanguages;
    }

    public Integer getMaxPreferredPage() {
        return maxPreferredPage;
    }

    public void setMaxPreferredPage(final Integer maxPreferredPage) {
        this.maxPreferredPage = maxPreferredPage;
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
        UserDto rhs = (UserDto) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.login, rhs.login)
                .append(this.firstName, rhs.firstName)
                .append(this.lastName, rhs.lastName)
                .append(this.birthDate, rhs.birthDate)
                .append(this.preferredGenres, rhs.preferredGenres)
                .append(this.preferredLanguages, rhs.preferredLanguages)
                .append(this.maxPreferredPage, rhs.maxPreferredPage)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(login)
                .append(firstName)
                .append(lastName)
                .append(birthDate)
                .append(preferredGenres)
                .append(preferredLanguages)
                .append(maxPreferredPage)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("login", login)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("birthDate", birthDate)
                .append("preferredGenres", preferredGenres)
                .append("preferredLanguages", preferredLanguages)
                .append("maxPreferredPage", maxPreferredPage)
                .toString();
    }
}
