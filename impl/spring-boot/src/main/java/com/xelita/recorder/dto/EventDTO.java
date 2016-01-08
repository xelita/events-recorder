package com.xelita.recorder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xelita.commons.dto.ContextDTO;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * EventDTO.
 *
 * @author xelita
 */
@Builder
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
