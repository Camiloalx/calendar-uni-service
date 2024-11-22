package com.unimayor.calendar_uni_service.modules.autentication.controller;

import com.unimayor.calendar_uni_service.core.domain.AuthenticationDomain;
import com.unimayor.calendar_uni_service.core.domain.UserDomain;
import com.unimayor.calendar_uni_service.core.exeption.BusinessException;
import com.unimayor.calendar_uni_service.core.util.StringUtils;
import com.unimayor.calendar_uni_service.modules.management.user.dataprovider.UserDataProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
@Log4j2
public class AuthenticationController {
    private final UserDataProvider userDataProvider;
    private final StringUtils stringUtils = new StringUtils();

    public AuthenticationController(UserDataProvider userDataProvider) {
        this.userDataProvider = userDataProvider;
    }

    public String processAuthentication(AuthenticationDomain authenticationDomain) {
        validateParameters(authenticationDomain);
        isExist(authenticationDomain);
        isEnabled(authenticationDomain);
        log.info("finish validate user");
        return isLogin(authenticationDomain);

    }

    private void validateParameters(AuthenticationDomain authenticationDomain) {
        log.info("validate parameters authentication");
        try {
            stringUtils.isAllBlank(authenticationDomain.getUsername());
            stringUtils.isAllBlank(authenticationDomain.getPassword());
        } catch (BusinessException e) {
            log.error("el usuario o contraseña no puede ser nulo o vacio");
            throw new BusinessException("el usuario o contraseña no puede ser nulo o vacio");
        }

    }

    private void isExist(AuthenticationDomain authenticationDomain) {
        log.info("Validate use is exist");
        UserDomain userDomain = userDataProvider.findUser(authenticationDomain.getUsername());
        if (Objects.isNull(userDomain)) {
            throw new BusinessException("El usaurio no existe en la BD: " + authenticationDomain.getUsername());
        }
        log.info("is exist");
    }

    private void isEnabled(AuthenticationDomain authenticationDomain) {
        log.info("validate user is enabled {}", authenticationDomain.getUsername());
        UserDomain userDomain = userDataProvider.findUser(authenticationDomain.getUsername());

        if (userDomain.active) {
            log.info("user is active");
        } else {
            log.error("user not active");
            throw new BusinessException("el usuario: " + userDomain.getUsername() + "no esta activo");
        }
    }


    private String isLogin(AuthenticationDomain authenticationDomain) {
        UserDomain userDomain = userDataProvider.findUser(authenticationDomain.getUsername());
        if (authenticationDomain.getPassword().equals(userDomain.getPassword())) {
            log.info("Login exitoso");
            return authenticationDomain.getUsername();
        } else {
            throw new BusinessException("La contraseña no coinciden");
        }

    }


}
