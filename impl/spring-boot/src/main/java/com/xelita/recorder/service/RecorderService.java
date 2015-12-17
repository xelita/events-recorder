package com.xelita.recorder.service;

import com.xelita.recorder.dto.EventDTO;
import com.xelita.recorder.dto.EventRecordDTO;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;

import static org.jongo.Oid.withOid;

/**
 * RecorderService.
 *
 * @author xelita
 */
@Slf4j
@Service
public class RecorderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MongoDbFactory mongoDbFactory;

    @Value("${mongo.collection.events}")
    private String eventsCollection;

    // *************************************************************************
    //
    // Service methods
    //
    // *************************************************************************

    /**
     * Check the state of the application.
     *
     * @return an EventDTO instance
     */
    public EventRecordDTO check() {
        this.mailSender.send(mimeMessage -> {
            mimeMessage.setFrom("events-recorder@xelita.com");
            mimeMessage.setSubject("test email");
            mimeMessage.setText("this is a test email sent during API checking process.");
            mimeMessage.setRecipients(Message.RecipientType.TO, "events-recorder@xelita.com");
        });
        log.debug("Test email has been sent.");

        return this.getEventsCollection().findOne().as(EventRecordDTO.class);
    }

    /**
     * Get an existing event by its identifier.
     *
     * @param id the id of the event to retrieve
     * @return the retrieved event or null if the event could not be retrieved
     */
    public EventRecordDTO getEventById(String id) {
        return this.getEventsCollection().findOne(withOid(id)).as(EventRecordDTO.class);
    }

    /**
     * Save a new event in the system.
     *
     * @param event the event to save
     * @return the saved event
     */
    public EventRecordDTO saveEvent(EventRecordDTO event) {
        event.setUid(null);
        ObjectId uid = (ObjectId) this.getEventsCollection().save(event).getUpsertedId();
        log.info("events uid is [{}].", uid);

        return this.getEventsCollection().findOne(uid).as(EventRecordDTO.class);
    }

    // *************************************************************************
    //
    // Convenience methods
    //
    // *************************************************************************

    /**
     * Get the exceptions collection.
     *
     * @return MongoCollection
     */
    protected MongoCollection getEventsCollection() {
        return this.getCollection(this.eventsCollection);
    }

    /**
     * Get the mongo collection with the given name.
     *
     * @param name the name of the mongo collection to retrieve
     * @return MongoCollection
     */
    protected MongoCollection getCollection(String name) {
        Jongo jongo = new Jongo(this.mongoDbFactory.getDb());
        return jongo.getCollection(name);
    }
}
