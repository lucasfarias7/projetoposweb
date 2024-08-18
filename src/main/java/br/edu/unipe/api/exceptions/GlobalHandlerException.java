package br.edu.unipe.api.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalHandlerException {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerResourceNotFoundException(ResourceNotFoundException ex){
        ErrorResponseDTO errorResponseDTO =
                new ErrorResponseDTO("Resource Not Found", ex.getMessage(), null);
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handleException(MethodArgumentNotValidException e) {


        List<String> constraintsViolated = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " - " + error.getDefaultMessage())
                .collect(Collectors.toList());

        constraintsViolated.addAll(
                e.getBindingResult().getGlobalErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList())
        );

        ErrorResponseDTO erroCapturado = ErrorResponseDTO.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .details(constraintsViolated.toString())
                .build();


        log.info("Erros {}", constraintsViolated);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroCapturado);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handlerException(Exception ex){
        ErrorResponseDTO errorResponseDTO =
                new ErrorResponseDTO("Internal error Server", ex.getMessage(), null);
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
