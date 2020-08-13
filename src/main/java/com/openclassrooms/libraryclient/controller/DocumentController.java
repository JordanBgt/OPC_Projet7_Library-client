package com.openclassrooms.libraryclient.controller;

import com.openclassrooms.libraryclient.model.Document;
import com.openclassrooms.libraryclient.model.DocumentForm;
import com.openclassrooms.libraryclient.model.DocumentLight;
import com.openclassrooms.libraryclient.model.RestPageImpl;
import com.openclassrooms.libraryclient.proxy.DocumentProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/documents")
public class DocumentController {

    Logger log = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentProxy documentProxy;

    @GetMapping
    public String getAllDocuments(Model model) {
        RestPageImpl<DocumentLight> pageDocuments;
        if (model.getAttribute("pageDocuments") == null) {
            pageDocuments = documentProxy.getAllDocuments(null, null, null, null,
                    null, null, null, null, null, null, false);
        } else {
            pageDocuments = (RestPageImpl<DocumentLight>) model.getAttribute("pageDocuments");
        }
        model.addAttribute("pageDocuments", pageDocuments);
        DocumentForm documentForm = new DocumentForm();
        model.addAttribute("documentForm", documentForm);
        return "document";
    }

    @GetMapping("/{id}")
    public String getDocument(Model model, @PathVariable Long id) {
        Document document = documentProxy.getDocument(id);
        model.addAttribute("document", document);
        return "documentDetail";
    }

    @PostMapping("/search")
    public ModelAndView searchDocuments(@ModelAttribute DocumentForm documentForm, RedirectAttributes redirectAttributes) {
        RestPageImpl<DocumentLight> pageDocuments = documentProxy.getAllDocuments(documentForm.getTitle(),
                documentForm.getIsbn(), documentForm.getAuthorName(), documentForm.getPublisherName(),
                documentForm.getType(), documentForm.getCategory(), null, null, null, null,
                false);
        redirectAttributes.addFlashAttribute("pageDocuments", pageDocuments);
        return new ModelAndView("redirect:/documents");
    }

    @GetMapping("/page/{page}")
    public ModelAndView getPage(@PathVariable int page, RedirectAttributes redirectAttributes) {
        RestPageImpl<DocumentLight> pageDocuments = documentProxy.getAllDocuments(null, null, null,
                null, null, null, page, null, null, null, false);
        redirectAttributes.addFlashAttribute("pageDocuments", pageDocuments);
        return new ModelAndView("redirect:/documents");
    }

}
