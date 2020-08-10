package com.openclassrooms.libraryclient.proxy;

import com.openclassrooms.libraryclient.model.Document;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@FeignClient(url = "http://localhost:8080/api/documents", name = "document-api")
public interface DocumentProxy {

    @GetMapping
    Page<Document> getAllDocuments(@RequestParam(required = false) String title,
                                          @RequestParam(required = false) String isbn,
                                          @RequestParam(required = false) String authorName,
                                          @RequestParam(required = false) String publisherName,
                                          @RequestParam(required = false) String type,
                                          @RequestParam(required = false) String category,
                                          @RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "20") Integer size,
                                          @RequestParam(defaultValue = "title") String sortBy,
                                          @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                          @RequestParam(defaultValue = "false") boolean unpaged);

    @GetMapping("/{id}")
    Document getDocument(@PathVariable Long id);

    @PostMapping
    Document createDocument(@RequestBody Document document);

    @PutMapping("/{id}")
    Document updateDocument(@RequestBody Document document);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteDocument(@PathVariable Long id);
}
