package com.decagon.fitnessoapp.service.serviceImplementation;

import com.decagon.fitnessoapp.dto.AddressRequest;
import com.decagon.fitnessoapp.exception.AddressNotFoundException;
import com.decagon.fitnessoapp.exception.PersonNotFoundException;
import com.decagon.fitnessoapp.model.user.Address;
import com.decagon.fitnessoapp.model.user.Person;
import com.decagon.fitnessoapp.repository.AddressRepository;
import com.decagon.fitnessoapp.repository.PersonRepository;
import com.decagon.fitnessoapp.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public ResponseEntity<?> createAddress(AddressRequest addressRequest) {
        Person person = personRepository.findByUserName(addressRequest.getUserName())
                .orElseThrow(() -> new PersonNotFoundException("You have to register first"));
        Address address = new Address();
        modelMapper.map(addressRequest, address);
        address.setPerson(person);
        addressRepository.save(address);
        return ResponseEntity.ok().body("Address added successfully");
    }

    @Override
    public AddressRequest updateAddress(AddressRequest request) {
        final Address previousAdd = addressRepository.findById(request.getId())
                .orElseThrow(() ->
                        new AddressNotFoundException("Address not found"));

        previousAdd.setCity(request.getCity());
        previousAdd.setState(request.getState());
        previousAdd.setCountry(request.getCountry());
        previousAdd.setStreetDetail(request.getStreetDetail());
        previousAdd.setZipCode(request.getZipCode());
        Address savedAdd = addressRepository.save(previousAdd);
        return modelMapper.map(savedAdd, AddressRequest.class);
    }

    @Override
    public String deleteAddress(Long id) {
        final Address address = addressRepository.findById(id).orElseThrow(
                () -> new AddressNotFoundException("Address not found"));
        addressRepository.delete(address);
        return "Address deleted successfully!";
    }


}
