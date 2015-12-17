package com.xelita.recorder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * EventDTO.
 *
 * @author xelita
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EventDTO implements Serializable {

    private static final long serialVersionUID = 5798904584155568644L;

    @JsonProperty("type")
    private String type;

    @JsonProperty("context")
    private ContextDTO context;

    @JsonProperty("data")
    private Map data;
}
