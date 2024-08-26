package com.eazybytes.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.model.Contact;
import com.eazybytes.repository.ContactRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactRepository contactRepository;

    /*
    @PostMapping("/contact")
    public Contact saveContactInquiryDetails(@RequestBody Contact contact) {
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return contactRepository.save(contact);
    }
    */
    
    /*
    @PostMapping("/contact")
    @PreFilter("filterObject.contactName != 'Test' ")
    public Contact saveContactInquiryDetails(@RequestBody List<Contact> contacts) {
    	
    	
    	if(!contacts.isEmpty()) {
        	Contact contact = contacts.get(0);
        	System.out.println(contacts.size());
    		contact.setContactId(getServiceReqNumber());
            contact.setCreateDt(new Date(System.currentTimeMillis()));
            return contactRepository.save(contact);
    	}else {
    		return null;
    	}
        
    }
    */
    
    @PostMapping("/contact")
    @PostFilter("filterObject.contactName != 'Test' ")
    public List<Contact> saveContactInquiryDetails(@RequestBody List<Contact> contacts) {
    	
    	List<Contact>returnContacts = new ArrayList<>();
    	
    	if(!contacts.isEmpty()) {
        	Contact contact = contacts.get(0);
        	System.out.println(contacts.size());
    		contact.setContactId(getServiceReqNumber());
            contact.setCreateDt(new Date(System.currentTimeMillis()));
            Contact savedContact = contactRepository.save(contact);
            returnContacts.add(savedContact);
    	}
    	
    	return returnContacts;
        
    }


    public String getServiceReqNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + ranNum;
    }
}
