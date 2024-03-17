package MaciejBabicki.Programmers.programmer.security;


import MaciejBabicki.Programmers.programmer.exception.QuotationExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
public class QuotationApi {


    QuotationExceptionHandler handler = new QuotationExceptionHandler();
    private List <Quotation> quotations;

    public QuotationApi() {
        this.quotations = new ArrayList<>();
        quotations.add(new Quotation(1,"Hello", "Maciej"));
        quotations.add(new Quotation(2,"Good Day", "Jonasz"));
    }


    @PostMapping("/api")
    public List<Quotation> createQuotation() {
        return quotations;
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<Quotation> getQuotationsById(@PathVariable Long id) {
        try {

            if (id >= 0 && id < quotations.size()) {
                Quotation quotation = quotations.get(Math.toIntExact(id));
                return ResponseEntity.ok(quotation);
            }
        } catch (IndexOutOfBoundsException exception) {
            handler.handleIndexOutOfBoundException(exception);
        }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    @ResponseBody
    @GetMapping("/api")
    public List<Quotation> getQuotations(){return quotations; };

    @DeleteMapping("/api/{id}")
    public void deleteQuotations(@PathVariable Long id){
        quotations.remove(id);

    }



}
