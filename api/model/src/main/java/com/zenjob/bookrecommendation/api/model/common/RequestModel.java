package com.zenjob.bookrecommendation.api.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestModel implements Serializable {

    private static final long serialVersionUID = 7812340466925084123L;
}
