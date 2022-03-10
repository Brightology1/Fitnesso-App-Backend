package com.decagon.fitnessoapp.service;

import com.decagon.fitnessoapp.dto.AddressRequest;
import com.decagon.fitnessoapp.dto.AddressResponse;
import org.springframework.http.ResponseEntity;

public interface AddressService {
    ResponseEntity<?> createAddress(AddressRequest addressRequest);

    AddressRequest updateAddress(AddressRequest request);

    String deleteAddress(Long id);

}
