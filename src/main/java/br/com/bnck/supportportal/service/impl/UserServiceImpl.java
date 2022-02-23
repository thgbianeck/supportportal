package br.com.bnck.supportportal.service.impl;

import br.com.bnck.supportportal.domain.User;
import br.com.bnck.supportportal.exception.domain.*;
import br.com.bnck.supportportal.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Criado utilizando IntelliJ IDEA.
 * Projeto: supportportal
 * Usuário: Thiago Bianeck (Bianeck)
 * Data: 22/02/2022
 * Hora: 23:44
 */
public class UserServiceImpl implements UserService, UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
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
