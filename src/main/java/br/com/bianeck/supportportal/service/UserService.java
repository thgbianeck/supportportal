package br.com.bianeck.supportportal.service;

import br.com.bianeck.supportportal.domain.User;
import br.com.bianeck.supportportal.exception.domain.EmailExistException;
import br.com.bianeck.supportportal.exception.domain.UserNotFoundException;
import br.com.bianeck.supportportal.exception.domain.UsernameExistException;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException;

    List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
