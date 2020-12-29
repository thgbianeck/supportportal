package br.com.bianeck.supportportal.resource;

import br.com.bianeck.supportportal.domain.User;
import br.com.bianeck.supportportal.exception.domain.ExceptionHandling;
import br.com.bianeck.supportportal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = {"/","/user"})
public class UserResource extends ExceptionHandling {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws Exception {
        User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
        return new ResponseEntity<User>(newUser, HttpStatus.OK);
    }

}
