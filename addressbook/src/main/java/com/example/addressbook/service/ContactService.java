package com.example.addressbook.service;

import com.example.addressbook.dto.ContactDTO;
import com.example.addressbook.model.Contact;
import com.example.addressbook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Contact addContact(ContactDTO contactDTO) {
        Contact contact = new Contact(contactDTO);
        return contactRepository.save(contact);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public Contact updateContact(Long id, ContactDTO contactDTO) {
        return contactRepository.findById(id)
                .map(contact -> {
                    contact.setName(contactDTO.getName());
                    contact.setPhone(contactDTO.getPhone());
                    contact.setEmail(contactDTO.getEmail());
                    contact.setAddress(contactDTO.getAddress());
                    return contactRepository.save(contact);
                }).orElse(null);
    }

    public boolean deleteContact(Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
