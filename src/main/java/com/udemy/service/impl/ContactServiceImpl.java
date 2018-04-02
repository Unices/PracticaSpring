package com.udemy.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.udemy.component.ContactConverter;
import com.udemy.entity.Contact;
import com.udemy.model.ContactModel;
import com.udemy.repository.ContactRepository;
import com.udemy.service.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService{

	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Override
	public ContactModel addContact(ContactModel contactModel) {
		Contact contact = contactRepository.save(ContactConverter.convertContactModel2Contact(contactModel));
		return ContactConverter.convertContact2ContactModel(contact);
	}

	@Override
	public List<ContactModel> listAllContacts() {
		return contactRepository.findAll().stream().map(ContactConverter::convertContact2ContactModel).collect(Collectors.toList());
	}

	@Override
	public Contact findContactById(int id) {
		return contactRepository.findById(id);
	}

	@Override
	public void removeContact(int id) {
		Contact contact = findContactById(id);
		if(null != contact) {
			contactRepository.delete(contact);
		}
	}
	
	@Override
	public ContactModel findContactByIdModel(int id) {
		return ContactConverter.convertContact2ContactModel(findContactById(id));
	}
	
}
