package br.edu.unipe.api.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ErrorResponseDTO {

    private final String error;
    private final String message;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String details;

    public ErrorResponseDTO(String error, String message, String details){
        this.error = error;
        this.message = message;
        this.details = details;
    }
}
