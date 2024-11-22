package com.unimayor.calendar_uni_service.core.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Class for domain authetication
 *
 * @author Brayan Alexis Alvarado
 * @version 1.0
 * @since 2024-11-21
 */
@Builder
@Data
@Getter
@Setter
public class AuthenticationDomain {
    public String username;
    public String password;
}
