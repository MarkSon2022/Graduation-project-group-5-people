package sse.edu.SPR2024.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class CustomException extends  RuntimeException{
    private HttpStatus status;
    private String messageReceive;

}
