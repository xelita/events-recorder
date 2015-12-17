package com.xelita.recorder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.io.Serializable;

/**
 * EventRecordDTO.
 *
 * @author xelita
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EventRecordDTO implements Serializable {

    private static final long serialVersionUID = 5798904584155568644L;

    @MongoId
    @MongoObjectId
    private String uid;

    @JsonProperty("event")
    private EventDTO event;
}
