package com.example.addressbook.service;

import com.example.addressbook.dto.ContactDTO;
import com.example.addressbook.model.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ContactService {

    private final List<Contact> contactList = new ArrayList<>(); // In-memory storage
    private final AtomicLong idCounter = new AtomicLong(1); // Generate unique IDs

    // Add a new Contact
    public Contact addContact(ContactDTO contactDTO) {
        Contact contact = new Contact(idCounter.getAndIncrement(), contactDTO.getName(), contactDTO.getPhone(), contactDTO.getEmail(), contactDTO.getAddress());
        contactList.add(contact);
        return contact;
    }

    // Get all Contacts
    public List<Contact> getAllContacts() {
        return contactList;
    }

    // Get Contact by ID
    public Optional<Contact> getContactById(Long id) {
        return contactList.stream().filter(contact -> contact.getId().equals(id)).findFirst();
    }

    // Update Contact by ID
    public Contact updateContact(Long id, ContactDTO contactDTO) {
        Optional<Contact> contactOptional = getContactById(id);
        if (contactOptional.isPresent()) {
            Contact contact = contactOptional.get();
            contact.setName(contactDTO.getName());
            contact.setPhone(contactDTO.getPhone());
            contact.setEmail(contactDTO.getEmail());
            contact.setAddress(contactDTO.getAddress());
            return contact;
        }
        return null;
    }

    // Delete Contact by ID
    public boolean deleteContact(Long id) {
        return contactList.removeIf(contact -> contact.getId().equals(id));
    }
}
