package com.openclassrooms.libraryclient.controller;

import com.openclassrooms.libraryclient.model.*;
import com.openclassrooms.libraryclient.proxy.DocumentProxy;
import com.openclassrooms.libraryclient.proxy.ExemplarProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/documents")
public class DocumentController {

    Logger log = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentProxy documentProxy;

    @Autowired
    private ExemplarProxy exemplarProxy;

    private DocumentForm documentForm = new DocumentForm();

    @GetMapping
    public String getAllDocuments(Model model,
                                  @RequestParam(required = false) Integer page,
                                  @RequestParam(required = false) Integer size,
                                  @RequestParam(required = false) String sortBy,
                                  @RequestParam(required = false) Sort.Direction direction,
                                  @RequestParam(required = false) boolean unpaged) {

        RestPageImpl<DocumentLight> pageDocuments;
        pageDocuments = documentProxy.getAllDocuments(documentForm.getTitle(), documentForm.getIsbn(),
                documentForm.getAuthorName(), documentForm.getPublisherName(), documentForm.getType(),
                documentForm.getCategory(), page, size, sortBy, direction, unpaged);
        model.addAttribute("pageDocuments", pageDocuments);
        model.addAttribute("documentForm", documentForm);
        return "document";
    }

    @GetMapping("/{id}")
    public String getDocument(Model model, @PathVariable Long id) {
        Document document = documentProxy.getDocument(id);
        List<Exemplar> exemplars = exemplarProxy.getAllExemplarsByDocumentId(id);
        model.addAttribute("document", document);
        model.addAttribute("exemplars", exemplars);
        return "document-detail";
    }

    @PostMapping("/search")
    public ModelAndView searchDocuments(@ModelAttribute DocumentForm documentForm, RedirectAttributes redirectAttributes) {
        this.documentForm = documentForm;
        return new ModelAndView("redirect:/documents");
    }

}
