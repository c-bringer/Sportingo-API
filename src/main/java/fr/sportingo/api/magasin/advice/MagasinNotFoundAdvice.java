package fr.sportingo.api.magasin.advice;

import fr.sportingo.api.magasin.exception.MagasinNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MagasinNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(MagasinNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String magasinNotFoundHandler(MagasinNotFoundException ex) {
        return ex.getMessage();
    }
}
