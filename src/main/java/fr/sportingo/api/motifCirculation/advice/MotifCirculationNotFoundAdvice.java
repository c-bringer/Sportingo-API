package fr.sportingo.api.motifCirculation.advice;

import fr.sportingo.api.motifCirculation.exception.MotifCirculationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MotifCirculationNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(MotifCirculationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String motifCirculationNotFoundHandler(MotifCirculationNotFoundException ex) {
        return ex.getMessage();
    }
}
