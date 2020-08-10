package com.openclassrooms.libraryclient.proxy;

import com.openclassrooms.libraryclient.model.Publisher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://localhost:8080/api/publishers", name = "publisher-api")
public interface PublisherProxy {

    @GetMapping
    List<Publisher> getAllPublishers();

    @GetMapping("/{id}")
    Publisher getPublisher(@PathVariable Long id);

    @PostMapping
    Publisher createPublisher(@RequestBody Publisher publisher);

    @PutMapping("/{id}")
    Publisher updatePublisher(@RequestBody Publisher publisher);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePublisher(@PathVariable Long id);
}
