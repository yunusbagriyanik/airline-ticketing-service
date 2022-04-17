package com.airlinesticketingbackend.service.user;

import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.user.CreateUserRequest;
import com.airlinesticketingbackend.dto.user.User;

public interface UserService {
    GenericResult<User> userRegister(CreateUserRequest request);
}
