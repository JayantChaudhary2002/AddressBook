package com.example.addressbook.controller;

import com.example.addressbook.dto.ContactDTO;
import com.example.addressbook.model.Contact;
import com.example.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody ContactDTO contactDTO) {
        Contact newContact = contactService.addContact(contactDTO);
        return ResponseEntity.ok(newContact);
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable Long id) {
        Optional<Contact> contact = contactService.getContactById(id);
        return contact.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO) {
        Contact updatedContact = contactService.updateContact(id, contactDTO);
        return (updatedContact != null) ? ResponseEntity.ok(updatedContact) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        return contactService.deleteContact(id)
                ? ResponseEntity.ok("Deleted Successfully")
                : ResponseEntity.notFound().build();
    }
}
