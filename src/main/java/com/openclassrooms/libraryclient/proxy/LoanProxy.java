package com.openclassrooms.libraryclient.proxy;

import com.openclassrooms.libraryclient.model.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://localhost:8080/api/loans", name = "loan-api")
public interface LoanProxy {

    @GetMapping("/users/{userId}")
    List<Loan> getAllByUser(@PathVariable Long userId);

    @PostMapping
    Loan createLoan(@RequestBody Loan loan);

    @GetMapping("/{id}/renewal")
    ResponseEntity<Void> renewLoan(@PathVariable Long id);

    @PutMapping("/{id}")
    Loan updateLoan(@RequestBody Loan loan);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteLoan(@PathVariable Long id);
}
