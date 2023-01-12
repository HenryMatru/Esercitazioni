package it.course.esercitazionespringboot_coursesdatabase.business;

import it.course.esercitazionespringboot_coursesdatabase.models.User;
import it.course.esercitazionespringboot_coursesdatabase.payload.request.SignupRequest;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthBO {

    public User createUser(@Valid @RequestBody SignupRequest signUpRequest);

}
