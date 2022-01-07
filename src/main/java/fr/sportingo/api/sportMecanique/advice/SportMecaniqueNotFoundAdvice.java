package fr.sportingo.api.sportMecanique.advice;

import fr.sportingo.api.sportMecanique.exception.SportMecaniqueNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SportMecaniqueNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SportMecaniqueNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String sportMecaniqueNotFoundHandler(SportMecaniqueNotFoundException ex) {
        return ex.getMessage();
    }
}