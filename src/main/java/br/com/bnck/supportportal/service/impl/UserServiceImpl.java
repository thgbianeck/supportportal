package br.com.bnck.supportportal.service.impl;

import br.com.bnck.supportportal.domain.User;
import br.com.bnck.supportportal.domain.UserPrincipal;
import br.com.bnck.supportportal.exception.domain.*;
import br.com.bnck.supportportal.repository.UserRepository;
import br.com.bnck.supportportal.service.EmailService;
import br.com.bnck.supportportal.service.LoginAttemptService;
import br.com.bnck.supportportal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static br.com.bnck.supportportal.constant.UserImplConstant.FOUND_USER_BY_USERNAME;
import static br.com.bnck.supportportal.constant.UserImplConstant.NO_USER_FOUND_BY_USERNAME;

/**
 * Criado utilizando IntelliJ IDEA.
 * Projeto: supportportal
 * Usuário: Thiago Bianeck (Bianeck)
 * Data: 22/02/2022
 * Modificado: 16/03/2022
 * Hora: 23:44
 */
@Service
@Transactional
@Qualifier("userDetailsService")
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final LoginAttemptService loginAttemptService;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           LoginAttemptService loginAttemptService,
                           EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginAttemptService = loginAttemptService;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(Objects.isNull(user)) {
            log.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        } else {
            validateLoginAttempt(user);
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            log.info(FOUND_USER_BY_USERNAME + username);
            return userPrincipal;
        }
    }

    private void validateLoginAttempt(User user) {
        if(user.isNotLocked()){
            if(loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {
                user.setNotLocked(false);
            } else {
                user.setNotLocked(true);
            }
        } else {
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }

    @Override
    public User register(
            String firstName,
            String lastName,
            String username,
            String email) throws UserNotFoundException,
                                    UsernameExistException,
                                    EmailExistException,
                                    MessagingException {
        return null;
    }

    @Override
    public User addNewUser(
            String firstName,
            String lastName,
            String username,
            String email,
            String role,
            boolean isNonLocked,
            boolean isActive,
            MultipartFile profileImage) throws UserNotFoundException,
                                                UsernameExistException,
                                                EmailExistException,
                                                IOException,
                                                NotAnImageFileException {
        return null;
    }

    @Override
    public User updateUser(
            String currentUsername,
            String newFirstName,
            String newLastName,
            String newUsername,
            String newEmail,
            String role,
            boolean isNonLocked,
            boolean isActive,
            MultipartFile profileImage) throws UserNotFoundException,
                                                UsernameExistException,
                                                EmailExistException,
                                                IOException,
                                                NotAnImageFileException {
        return null;
    }

    @Override
    public void resetPassword(String email) throws MessagingException, EmailNotFoundException {

    }

    @Override
    public User updateProfileImage(String username, MultipartFile profileImage) throws UserNotFoundException,
                                                                                        UsernameExistException,
                                                                                        EmailExistException,
                                                                                        IOException,
                                                                                        NotAnImageFileException {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public void deleteUser(String username) throws IOException {

    }

}
