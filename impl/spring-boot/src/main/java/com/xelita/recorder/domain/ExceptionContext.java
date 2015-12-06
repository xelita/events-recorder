package com.xelita.recorder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Exception context.
 *
 * @author xelita
 */
public class ExceptionContext implements Serializable {

    private static final long serialVersionUID = 5798904584155568644L;

    @JsonProperty("app_id")
    private String applicationId;
}
