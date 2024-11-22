package com.unimayor.calendar_uni_service.modules.autentication.api;

import com.unimayor.calendar_uni_service.core.constant.MessageConstant;
import com.unimayor.calendar_uni_service.core.domain.AuthenticationDomain;
import com.unimayor.calendar_uni_service.core.exeption.BusinessException;
import com.unimayor.calendar_uni_service.modules.autentication.controller.AuthenticationController;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
@CrossOrigin("*")
@RequestMapping(value = "/authentication", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AuthenticationWebApi {
    @Autowired
    AuthenticationController authenticationController;

    @PostMapping
    public ResponseEntity<Map<String, Object>> authentication(@RequestBody final AuthenticationDomain authenticationDomain) {
        log.info("Init authentication for user: {} ", authenticationDomain.getUsername());

        Map<String, Object> response = new HashMap<>();

        try {
            String user = authenticationController.processAuthentication(authenticationDomain);
            response.put(MessageConstant.RESPONSE_MESSAGE, user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("Error in authentication: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error no controller in authentication: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, "error no controlado" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }
}
