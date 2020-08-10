package com.openclassrooms.libraryclient.proxy;

import com.openclassrooms.libraryclient.model.Library;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://localhost:8080/api/libraries", name = "library-api")
public interface LibraryProxy {

    @GetMapping
    List<Library> getAllLibraries();

    @GetMapping("/{id}")
    Library getLibrary(@PathVariable Long id);

    @PostMapping
    Library createLibrary(@RequestBody Library library);

    @PutMapping("/{id}")
    Library updateLibrary(@RequestBody Library library);

    @DeleteMapping
    ResponseEntity<Void> deleteLibrary(@PathVariable Long id);
}
