package com.openclassrooms.libraryclient.model;

public class Exemplar {

    private Long id;
    private String reference;
    private Document document;
    private Library library;

    public Exemplar() {
    }

    public Exemplar(Long id, String reference, Document document, Library library) {
        this.id = id;
        this.reference = reference;
        this.document = document;
        this.library = library;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
