package com.xelita.recorder.service;

import lombok.extern.slf4j.Slf4j;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;

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

    public Exception check() {
        this.mailSender.send(mimeMessage -> {
            mimeMessage.setFrom("events-recorder@xelita.com");
            mimeMessage.setSubject("test email");
            mimeMessage.setText("this is a test email sent during API checking process.");
            mimeMessage.setRecipients(Message.RecipientType.TO, "events-recorder@xelita.com");
        });
        log.debug("Test email has been sent.");

        return this.getEventsCollection().findOne().as(Exception.class);
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
