package com.openclassrooms.libraryclient.proxy;

import com.openclassrooms.libraryclient.model.Author;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://localhost:8080/api/authors", name = "author-api")
public interface AuthorProxy {

    @GetMapping
    List<Author> getAllAuthors();

    @GetMapping("/{id}")
    Author getAuthor(@PathVariable Long id);

    @PostMapping
    Author createAuthor(@RequestBody Author author);

    @PutMapping("/{id}")
    Author updateAuthor(@RequestBody Author author);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAuthor(@PathVariable Long id);
}
