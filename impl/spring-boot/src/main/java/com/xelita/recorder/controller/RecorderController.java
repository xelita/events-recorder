package com.xelita.recorder.controller;

import com.xelita.recorder.service.RecorderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * RecorderController.
 *
 * @author xelita
 */
@Slf4j
@RequestMapping("/api")
@RestController
public class RecorderController {

    @Autowired
    private RecorderService recorderService;

    // *************************************************************************
    //
    // Rest methods
    //
    // *************************************************************************

    @ResponseBody
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Exception check() {
        return this.recorderService.check();
    }
}
