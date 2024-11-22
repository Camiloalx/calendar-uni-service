package com.unimayor.calendar_uni_service.modules.autentication.dataprovider;

import com.unimayor.calendar_uni_service.core.domain.AuthenticationDomain;
import com.unimayor.calendar_uni_service.core.domain.UserDomain;

public interface AuthenticateDataProvider {
    UserDomain findUser(AuthenticationDomain authenticationDomain);
}
