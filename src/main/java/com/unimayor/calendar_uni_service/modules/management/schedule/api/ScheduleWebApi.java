package com.unimayor.calendar_uni_service.modules.management.schedule.api;

import com.unimayor.calendar_uni_service.core.constant.MessageConstant;
import com.unimayor.calendar_uni_service.core.domain.ScheduleDomain;
import com.unimayor.calendar_uni_service.core.exeption.BusinessException;
import com.unimayor.calendar_uni_service.modules.management.schedule.controller.ControllerSchedule;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/schedule", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ScheduleWebApi {
    @Autowired
    private ControllerSchedule controllerSchedule;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createSchedule(@Valid @RequestBody final ScheduleDomain scheduleDomain) {
        log.info("Initializing createSchedule method with scheduleDomain: {}", scheduleDomain);
        Map<String, Object> response = new HashMap<>();
        try {
            controllerSchedule.createSchedule();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (BusinessException e) {
            log.error("Error creating schedule: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, "error al momento de crear el horario" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error creating schedule: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, "error no controlado" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }
}
