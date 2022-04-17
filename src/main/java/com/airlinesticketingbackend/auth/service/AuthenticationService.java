package com.airlinesticketingbackend.auth.service;

import com.airlinesticketingbackend.dto.auth.AuthenticationRequest;
import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.user.User;

public interface AuthenticationService {
    GenericResult<User> authenticate(AuthenticationRequest authRequest);
}
