package com.unimayor.calendar_uni_service.modules.autentication.dataprovider.jpa;

import com.unimayor.calendar_uni_service.core.domain.AuthenticationDomain;
import com.unimayor.calendar_uni_service.core.domain.UserDomain;
import com.unimayor.calendar_uni_service.core.exeption.BusinessException;
import com.unimayor.calendar_uni_service.core.persistence.entity.UserEntity;
import com.unimayor.calendar_uni_service.core.persistence.repository.UserRepository;
import com.unimayor.calendar_uni_service.modules.autentication.dataprovider.AuthenticateDataProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class AuthenticationImpl implements AuthenticateDataProvider {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDomain findUser(AuthenticationDomain authenticationDomain) {
            Optional<UserEntity> userEntity
                    = userRepository.findUsername(authenticationDomain.getUsername());

            if (userEntity.isPresent()) {
                return UserDomain.builder()
                        .username(userEntity.get().getUsername())
                        .password(userEntity.get().getPassword())
                        .active(userEntity.get().isActive())
                        .build();
            } else {
                throw new BusinessException(authenticationDomain.getUsername() + " No existe");
            }
    }
}
