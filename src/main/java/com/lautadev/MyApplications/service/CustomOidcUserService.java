package com.lautadev.MyApplications.service;

import com.lautadev.MyApplications.model.Account;
import com.lautadev.MyApplications.model.GoogleUserInfo;
import com.lautadev.MyApplications.model.UserSec;
import com.lautadev.MyApplications.repository.IUserSecRepository;
import com.lautadev.MyApplications.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOidcUserService extends OidcUserService {
    @Autowired
    private IUserSecRepository userSecRepository;

    @Autowired
    private IUserSecService userSecService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    @Lazy // <-- Anotación indica que este bean no debe inicializar inmediatamente, para evitar problemas de ciclo de dependencias
    private IUserDetailsService userDetailsService;

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        try {
            return processOidcUser(userRequest, oidcUser);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {
        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());

        Optional<UserSec> accountOptional = userSecRepository.findByEmail(googleUserInfo.getEmail());
        Account account;

        if (accountOptional.isEmpty()) {
            account = accountService.saveUserOAuth(googleUserInfo);

            if (account.getId() != null) {
                UserSec userSec = new UserSec();
                userSec.setEmail(googleUserInfo.getEmail());
                userSec.setName(googleUserInfo.getName());
                userSec.setLastname(googleUserInfo.getLastname());
                userSec.setAccount(account);

                userSecService.saveUser(userSec);
            }
        } else {
            account = accountOptional.get().getAccount();
        }

        // UserDetailsService para obtener UserDetails y authorities
        UserDetails userDetails = userDetailsService.loadUserByUsername(account.getUsername());

        // Crear el token JWT
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        String jwtToken = jwtUtils.createToken(authentication);

        /* agregar el token al OidcUser en los atributos (Si  se quiere usar para el fronted)
        Map<String, Object> attributes = new HashMap<>(oidcUser.getAttributes());
        attributes.put("jwtToken", jwtToken);*/

        // Token impreso
        System.out.println("Generated JWT Token: " + jwtToken);

        return new DefaultOidcUser(userDetails.getAuthorities(), oidcUser.getIdToken(), oidcUser.getUserInfo(), "sub");
    }

}