package com.zenjob.bookrecommendation.persistence.user.entity;

import com.zenjob.bookrecommendation.persistence.common.entity.AbstractDomainEntityModel;
import com.zenjob.bookrecommendation.persistence.userbookpreferences.entity.UserBookPreference;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 7:03 PM
 */
@Entity
@Table(name = "user")
public class User extends AbstractDomainEntityModel {

    private static final long serialVersionUID = 4491238515334460950L;

    @Column(name = "login")
    private String login;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST,
//            fetch = FetchType.LAZY, optional = false)
//    private UserBookPreference userBookPreference;

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

//    public UserBookPreference getUserBookPreference() {
//        return userBookPreference;
//    }
//
//    public void setUserBookPreference(final UserBookPreference userBookPreference) {
//        this.userBookPreference = userBookPreference;
//    }

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
        User rhs = (User) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.login, rhs.login)
                .append(this.firstName, rhs.firstName)
                .append(this.lastName, rhs.lastName)
                .append(this.birthDate, rhs.birthDate)
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
                .toString();
    }
}
