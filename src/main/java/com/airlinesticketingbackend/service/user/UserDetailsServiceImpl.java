package com.airlinesticketingbackend.service.user;

import com.airlinesticketingbackend.auth.security.TokenizedUser;
import com.airlinesticketingbackend.base.BusinessEnumerationTypes;
import com.airlinesticketingbackend.repository.user.UserEntityRepository;
import com.airlinesticketingbackend.entity.user.UserEntity;
import com.airlinesticketingbackend.service.base.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;
    private final UtilityService utilityService;

    @Autowired
    public UserDetailsServiceImpl(UserEntityRepository userEntityRepository, UtilityService utilityService) {
        this.userEntityRepository = userEntityRepository;
        this.utilityService = utilityService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity user = userEntityRepository.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException(BusinessEnumerationTypes.AuthenticationOperation.USER_NOT_FOUND.name() + username);

        return createTokenizedUser(user);
    }

    private TokenizedUser createTokenizedUser(UserEntity user) {
        return new TokenizedUser(
                user.getUserId(), user.getUsername(),
                user.getPassword(),
                this.userGeneralStatusShortCode(user).equals(BusinessEnumerationTypes.UserEnabledStatus.ACTV.getShrtCode())
                        || this.userGeneralStatusShortCode(user).equals(BusinessEnumerationTypes.UserEnabledStatus.CONFIRM.getShrtCode()),
                mapToGrantedAuthorities());
    }

    private String userGeneralStatusShortCode(UserEntity user) {
        return this.utilityService.findStatusShortCode(user.getStId());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities() {
        ArrayList<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("USER"));
        return grantedAuthorityList;
    }
}
