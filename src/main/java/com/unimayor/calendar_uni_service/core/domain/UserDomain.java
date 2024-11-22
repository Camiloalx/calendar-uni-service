package com.unimayor.calendar_uni_service.core.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDomain {
    public String username;
    public String password;
    public boolean active;
}
