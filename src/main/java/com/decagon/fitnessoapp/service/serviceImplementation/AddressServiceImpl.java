package com.decagon.fitnessoapp.service.serviceImplementation;

import com.decagon.fitnessoapp.dto.AddressRequest;
import com.decagon.fitnessoapp.dto.AddressResponse;
import com.decagon.fitnessoapp.model.user.Address;
import com.decagon.fitnessoapp.model.user.Person;
import com.decagon.fitnessoapp.repository.AddressRepository;
import com.decagon.fitnessoapp.repository.PersonRepository;
import com.decagon.fitnessoapp.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    public final AddressRepository addressRepository;
    public final PersonRepository personRepository;
    public final ModelMapper modelMapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, PersonRepository personRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressResponse createAddress(AddressRequest addressRequest) {
        AddressResponse addressResponse = new AddressResponse();
        Optional<Person> person = personRepository.findByUserName(addressRequest.getUserName());
        if(person.isEmpty()){
            addressResponse.setMessage("incorrect username");
            return addressResponse;
        }
        Address address = new Address();
        modelMapper.map(addressRequest, address);
        address.setPerson(person.get());
        addressRepository.save(address);
        addressResponse.setMessage("Address added successfully");
        return addressResponse;
    }


}
