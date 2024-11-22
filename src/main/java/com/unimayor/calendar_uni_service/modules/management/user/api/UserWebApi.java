package com.unimayor.calendar_uni_service.modules.management.user.api;

import com.unimayor.calendar_uni_service.core.constant.MessageConstant;
import com.unimayor.calendar_uni_service.core.domain.UserDomain;
import com.unimayor.calendar_uni_service.core.exeption.BusinessException;
import com.unimayor.calendar_uni_service.modules.management.user.controller.UserController;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/user", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserWebApi {
    @Autowired
    private UserController userController;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody final UserDomain userDomain) {
        log.info("init save user");

        Map<String, Object> response = new HashMap<>();

        try {
            userController.createUser(userDomain);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("Error al momento de crear el usuario");
            response.put(MessageConstant.RESPONSE_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error no controlado en usuario: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, "error no controlado" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }
}
