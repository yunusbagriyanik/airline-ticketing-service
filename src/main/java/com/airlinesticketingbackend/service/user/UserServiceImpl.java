package com.airlinesticketingbackend.service.user;

import com.airlinesticketingbackend.base.BusinessEnumerationTypes;
import com.airlinesticketingbackend.base.Constants;
import com.airlinesticketingbackend.base.exception.ProvideExceptionHandler;
import com.airlinesticketingbackend.base.mapper.DozerMapper;
import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.common.ProcessResult;
import com.airlinesticketingbackend.dto.user.CreateUserRequest;
import com.airlinesticketingbackend.dto.user.User;
import com.airlinesticketingbackend.repository.user.UserEntityRepository;
import com.airlinesticketingbackend.service.base.AbstractService;
import com.airlinesticketingbackend.entity.user.UserEntity;
import com.airlinesticketingbackend.service.base.UtilityService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractService implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserEntityRepository userEntityRepository;
    private final UtilityService utilityService;
    private final DozerMapper dozerMapper;

    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
                           UserEntityRepository userEntityRepository,
                           UtilityService utilityService, DozerMapper dozerMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userEntityRepository = userEntityRepository;
        this.utilityService = utilityService;
        this.dozerMapper = dozerMapper;
    }

    @Override
    public GenericResult<User> userRegister(CreateUserRequest request) {
        this.verifyExistingUser(request);
        GenericResult<User> response = new GenericResult<>();

        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstName(request.getFirstName());
            userEntity.setLastName(request.getLastName());
            userEntity.setEmail(request.getEmail());
            userEntity.setUsername(request.getUsername());
            userEntity.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
            userEntity.setIsActv(Constants.TRUE_FLAG);
            userEntity.setStId(enabledUserStId());

            this.userEntityRepository.save(userEntity);

            response.setProcessResult(ProcessResult.success("Create User API"));
            response.setResult(this.dozerMapper.map(userEntity, User.class, "UserEntity_User"));

            return response;
        } catch (Exception exc) {
            this.logger.error("Error occurred while registering user: {}", exc.getMessage());
            ProcessResult errorResponse = ProcessResult.internalServerError("Error received during create user. " + exc.getMessage());
            response.setProcessResult(errorResponse);

            return response;
        }
    }

    private void verifyExistingUser(CreateUserRequest request) {
        final UserEntity user = this.userEntityRepository.findByUsername(request.getUsername());

        if (user != null)
            throw new ProvideExceptionHandler("This username already exists: " + user.getUsername());
        if (userEntityRepository.findByEmail(request.getEmail()) != null)
            throw new ProvideExceptionHandler("This user email already exists: " + request.getEmail());

    }

    private Long enabledUserStId() {
        return utilityService.findGeneralStatusId(BusinessEnumerationTypes.UserEnabledStatus.CONFIRM.getShrtCode(), BusinessEnumerationTypes.EntityCodeName.USER.name(), Constants.TRUE_FLAG);
    }
}
