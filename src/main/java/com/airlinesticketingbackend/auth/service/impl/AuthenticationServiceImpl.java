package com.airlinesticketingbackend.auth.service.impl;

import com.airlinesticketingbackend.auth.security.JwtTokenUtil;
import com.airlinesticketingbackend.auth.security.TokenizedUser;
import com.airlinesticketingbackend.base.mapper.DozerMapper;
import com.airlinesticketingbackend.dto.auth.AuthenticationRequest;
import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.common.ProcessResult;
import com.airlinesticketingbackend.dto.user.User;
import com.airlinesticketingbackend.repository.user.UserEntityRepository;
import com.airlinesticketingbackend.service.base.AbstractService;
import com.airlinesticketingbackend.service.user.UserDetailsServiceImpl;
import com.airlinesticketingbackend.auth.service.AuthenticationService;
import com.airlinesticketingbackend.entity.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class AuthenticationServiceImpl extends AbstractService implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private HttpServletResponse httpServletResponse;
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private UserDetailsServiceImpl userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private DozerMapper dozerMapper;

    @Value("${jwt.header}")
    private String signToken;

    @Override
    public GenericResult<User> authenticate(AuthenticationRequest authRequest) {
        GenericResult<User> genericResponse = new GenericResult<>();
        try {
            final UserEntity user = this.userEntityRepository.findByUsername(authRequest.getUsername());

            if (user == null) {
                genericResponse.setProcessResult(ProcessResult.noContent("USER_NOT_FOUND : " + user));
                return genericResponse;
            }

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            final TokenizedUser userDetails = (TokenizedUser) this.userService.loadUserByUsername(authRequest.getUsername());

            final String token = jwtTokenUtil.generateToken(userDetails);
            this.httpServletResponse.addHeader(signToken, token);

            genericResponse.setResult(this.dozerMapper.map(user, User.class, "UserEntity_User"));
            genericResponse.setProcessResult(ProcessResult.success("Authenticate API"));
        } catch (Exception exc) {
            genericResponse.setProcessResult(ProcessResult.unauthorized("Authentication Error. " + exc.getMessage()));
            return genericResponse;
        }

        return genericResponse;
    }
}
