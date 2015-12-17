package com.xelita.recorder.controller;

import com.xelita.recorder.dto.EventDTO;
import com.xelita.recorder.dto.EventRecordDTO;
import com.xelita.recorder.dto.ResponseDTO;
import com.xelita.recorder.service.RecorderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

/**
 * RecorderController.
 *
 * @author xelita
 */
@Slf4j
@RequestMapping("/api")
@RestController
public class RecorderController {

    @Value("${info.app.id}")
    private String appId;

    @Autowired
    private RecorderService recorderService;

    // *************************************************************************
    //
    // Rest methods
    //
    // *************************************************************************

    /**
     * @see RecorderService#check()
     */
    @ApiOperation(value = "Check the status of the application")
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public ResponseDTO<EventRecordDTO> check() {
        return new ResponseDTO<>(this.appId, this.recorderService.check());
    }

    /**
     * @see RecorderService#getEventById(String)
     */
    @ApiOperation(value = "Get an existing event by its identifier")
    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    public ResponseDTO<EventRecordDTO> getEventById(@PathVariable String id) {
        return new ResponseDTO<>(this.appId, this.recorderService.getEventById(id));
    }

    /**
     * @see RecorderService#saveEvent(EventRecordDTO)
     */
    @ApiOperation(
            value = "Save a new event in the system",
            notes = "If a uid is provided in the request payload, it will be omitted")
    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public ResponseDTO<EventRecordDTO> saveEvent(@RequestBody EventRecordDTO event) {
        return new ResponseDTO<>(this.appId, this.recorderService.saveEvent(event));
    }
}
