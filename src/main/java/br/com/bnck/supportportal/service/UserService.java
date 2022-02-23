package br.com.bnck.supportportal.service;

import br.com.bnck.supportportal.domain.User;
import br.com.bnck.supportportal.exception.domain.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Criado utilizando IntelliJ IDEA.
 * Projeto: supportportal
 * Usuário: Thiago Bianeck (Bianeck)
 * Data: 22/02/2022
 * Hora: 23:35
 */
public interface UserService {
    User register(
            String firstName,
            String lastName,
            String username,
            String email) throws UserNotFoundException,
                UsernameExistException,
                EmailExistException,
                MessagingException;

    List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User addNewUser(
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
                                                NotAnImageFileException;

    User updateUser(
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
                                                NotAnImageFileException;

    void deleteUser(String username) throws IOException;

    void resetPassword(String email) throws MessagingException, EmailNotFoundException;

    User updateProfileImage(String username, MultipartFile profileImage) throws UserNotFoundException,
                                                                                    UsernameExistException,
                                                                                    EmailExistException,
                                                                                    IOException,
                                                                                    NotAnImageFileException;



}
