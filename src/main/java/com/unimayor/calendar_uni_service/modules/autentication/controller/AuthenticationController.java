package com.unimayor.calendar_uni_service.modules.autentication.controller;

import com.unimayor.calendar_uni_service.core.domain.AuthenticationDomain;
import com.unimayor.calendar_uni_service.core.domain.UserDomain;
import com.unimayor.calendar_uni_service.core.exeption.BusinessException;
import com.unimayor.calendar_uni_service.core.util.StringUtils;
import com.unimayor.calendar_uni_service.modules.autentication.dataprovider.AuthenticateDataProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

@Controller
@Log4j2
public class AuthenticationController {
    private final AuthenticateDataProvider authenticateDataProvider;
    private StringUtils stringUtils = new StringUtils();

    public AuthenticationController(AuthenticateDataProvider authenticateDataProvider) {
        this.authenticateDataProvider = authenticateDataProvider;
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
        authenticateDataProvider.findUser(authenticationDomain);
        log.info("is exist");
    }

    private void isEnabled(AuthenticationDomain authenticationDomain) {
        log.info("validate user is enabled {}", authenticationDomain.getUsername());
        UserDomain userDomain = authenticateDataProvider.findUser(authenticationDomain);

        if (userDomain.active) {
            log.info("user is active");
        } else {
            log.error("user not active");
            throw new BusinessException("el usuario: " + userDomain.getUsername() + "no esta activo");
        }
    }


    private String isLogin(AuthenticationDomain authenticationDomain) {
        return authenticationDomain.getUsername();
    }


}
