package com.xelita.recorder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jongo.marshall.jackson.oid.MongoId;

import java.io.Serializable;

/**
 * Exception stored in database.
 *
 * @author xelita
 */
public class Exception implements Serializable {

    private static final long serialVersionUID = 5798904584155568644L;

    @MongoId
    private String uid;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("details")
    private String details;

    @JsonProperty("context")
    private ExceptionContext context;
}
