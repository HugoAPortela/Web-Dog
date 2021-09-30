package br.com.webdog.service;

import br.com.webdog.endpoint.controller.dto.AccountRegisterDto;
import br.com.webdog.persistence.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);
    User save(AccountRegisterDto registration);
    void updatePassword(String password, Long userId);
}
