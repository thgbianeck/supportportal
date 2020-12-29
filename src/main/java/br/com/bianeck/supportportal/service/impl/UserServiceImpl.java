package br.com.bianeck.supportportal.service.impl;

import br.com.bianeck.supportportal.constant.FileConstant;
import br.com.bianeck.supportportal.domain.User;
import br.com.bianeck.supportportal.domain.UserPrincipal;
import br.com.bianeck.supportportal.enumeration.Role;
import br.com.bianeck.supportportal.exception.domain.EmailExistException;
import br.com.bianeck.supportportal.exception.domain.UserNotFoundException;
import br.com.bianeck.supportportal.exception.domain.UsernameExistException;
import br.com.bianeck.supportportal.repository.UserRepository;
import br.com.bianeck.supportportal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static br.com.bianeck.supportportal.constant.FileConstant.*;
import static br.com.bianeck.supportportal.constant.UserImplConstant.*;
import static br.com.bianeck.supportportal.enumeration.Role.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
@Qualifier("UserDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user == null) {
            final String message = "User not found by username: " + username;
            log.error(message);
            throw new UsernameNotFoundException(message);
        } else {
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            log.info("Returning found user by username: " + username);
            return userPrincipal;
        }
    }

    @Override
    public User register(String firstName, String lastName, String username, String email)
            throws UserNotFoundException, UsernameExistException, EmailExistException {
        validateNewUsernameAndEmail(EMPTY, username, email);

        String password = generatePassword();
        User user = User.builder()
                .userId(generateUserId())
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .email(email)
                .joinDate(new Date())
                .password(encodePassword(password))
                .isActive(true)
                .isNotLocked(true)
                .role(ROLE_USER.name())
                .authorities(ROLE_USER.getAuthorities())
                .profileImageUrl(getTemporaryProfileImageUrl(username))
                .build();

        userRepository.save(user);
        log.info("New user password: " + password);
        return user;
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String setProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_IMAGE_PATH + username + FORWARD_SLASH
                + username + DOT + JPG_EXTENSION).toUriString();
    }

    private String getTemporaryProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(DEFAULT_USER_IMAGE_PATH + username).toUriString();
    }

    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UserNotFoundException, UsernameExistException, EmailExistException {
        User userByNewUsername = userRepository.findUserByUsername(newUsername);
        User userByNewEmail = findUserByEmail(newEmail);
        if(StringUtils.isNotBlank(currentUsername)) {
            User currentUser = findUserByUsername(currentUsername);
            if(currentUser == null) {
                throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
            }
            if(userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if(userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return currentUser;
        } else {
            if(userByNewUsername != null) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if(userByNewEmail != null) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return null;
        }
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
