package com.decagon.fitnessoapp.service.serviceImplementation;

import com.decagon.fitnessoapp.dto.ContactResponse;
import com.decagon.fitnessoapp.dto.transactionDto.ContactRequest;
import com.decagon.fitnessoapp.model.user.Contact;
import com.decagon.fitnessoapp.repository.ContactRepository;
import com.decagon.fitnessoapp.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private ModelMapper modelMapper;

    @Override
    public ContactRequest save(ContactRequest contactRequest) {
        contactRepository.save(modelMapper.map(contactRequest, Contact.class));
        return contactRequest;
    }

    @Override
    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }
}
