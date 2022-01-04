package fr.sportingo.api.difficulte.advice;

import fr.sportingo.api.difficulte.exception.DifficulteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DifficulteNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(DifficulteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String difficulteNotFoundHandler(DifficulteNotFoundException ex) {
        return ex.getMessage();
    }
}
