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
import java.util.List;
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

    @GetMapping
    public ResponseEntity<Map<String, Object>> getFindAll() {
        log.info("init get all user");

        Map<String, Object> response = new HashMap<>();

        try {
            List<UserDomain> userDomains = userController.findAll();
            response.put(MessageConstant.RESPONSE_MESSAGE, userDomains);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("Error al momento de retonar todos los usuario");
            response.put(MessageConstant.RESPONSE_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error no controlado en usuario: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, "error no controlado" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/get-find-by-username")
    public ResponseEntity<Map<String, Object>> getFindByUsername(@RequestParam final String username) {
        log.info("init get user by username");

        Map<String, Object> response = new HashMap<>();

        try {
            UserDomain userDomain = userController.findByUsername(username);
            response.put(MessageConstant.RESPONSE_MESSAGE, userDomain);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("Error al momento de retonar todos los usuario");
            response.put(MessageConstant.RESPONSE_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error no controlado en usuario: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, "error no controlado" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> delete(@RequestParam final String username) {
        log.info("init delete user");

        Map<String, Object> response = new HashMap<>();

        try {
            userController.delete(username);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException e) {
            log.error("Error al momento de eliminar el usuario");
            response.put(MessageConstant.RESPONSE_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Error no controlado en usuario: ", e);
            response.put(MessageConstant.RESPONSE_MESSAGE, "error no controlado" + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> update(@RequestBody final UserDomain userDomain) {
        log.info("init update user");

        Map<String, Object> response = new HashMap<>();

        try {
            userController.update(userDomain);
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
