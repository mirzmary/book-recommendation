package com.zenjob.bookrecommendation.api.model.bookrecommendation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zenjob.bookrecommendation.api.model.common.FacadeModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BookRecommendationResponseModel extends FacadeModel {

    private static final long serialVersionUID = -7612231742544576988L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("author")
    private String author;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("language")
    private String language;

    @JsonProperty("pageCount")
    private int pageCount;

    @JsonProperty("minAge")
    private int minAge;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(final int pageCount) {
        this.pageCount = pageCount;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(final int minAge) {
        this.minAge = minAge;
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
        BookRecommendationResponseModel rhs = (BookRecommendationResponseModel) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(this.id, rhs.id)
                .append(this.name, rhs.name)
                .append(this.author, rhs.author)
                .append(this.genre, rhs.genre)
                .append(this.language, rhs.language)
                .append(this.pageCount, rhs.pageCount)
                .append(this.minAge, rhs.minAge)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(id)
                .append(name)
                .append(author)
                .append(genre)
                .append(language)
                .append(pageCount)
                .append(minAge)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("id", id)
                .append("name", name)
                .append("author", author)
                .append("genre", genre)
                .append("language", language)
                .append("pageCount", pageCount)
                .append("minAge", minAge)
                .toString();
    }
}
