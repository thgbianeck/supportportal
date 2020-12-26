package br.com.bianeck.supportportal.resource;

import br.com.bianeck.supportportal.exception.domain.ExceptionHandling;
import br.com.bianeck.supportportal.exception.domain.UserNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/","/user"})
public class UserResource extends ExceptionHandling {

    @GetMapping("/home")
    public String showUser() throws Exception {
//        return "application works";
        throw new UserNotFoundException("The user was not found");
    }

}
