package com.openclassrooms.libraryclient.proxy;

import com.openclassrooms.libraryclient.model.Exemplar;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://localhost:8080/api/exemplars", name = "exemplar-api")
public interface ExemplarProxy {

    @GetMapping
    List<Exemplar> getAllExemplarsByDocumentId(@PathVariable Long documentId);

    @GetMapping("/{id}")
    Exemplar getExemplar(@PathVariable Long id);

    @PostMapping
    Exemplar createExemplar(@RequestBody Exemplar exemplar);

    @PutMapping("/{id}")
    Exemplar updateExemplar(@RequestBody Exemplar exemplar);

    @DeleteMapping("/(id}")
    ResponseEntity<Void> deleteExemplar(@PathVariable Long id);
}
