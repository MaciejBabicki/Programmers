package MaciejBabicki.Programmers.programmer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class QuotationExceptionHandler {

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity <String> handleIndexOutOfBoundException(IndexOutOfBoundsException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no Quotation with this index");
    }
}
