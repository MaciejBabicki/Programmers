package pl.programmers.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmptyRepositoryException extends RuntimeException{
    public EmptyRepositoryException(){
       log.info("Repository is empty");
    }
}
