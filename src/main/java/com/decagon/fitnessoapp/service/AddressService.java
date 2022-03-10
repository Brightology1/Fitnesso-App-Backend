package com.decagon.fitnessoapp.service;

import com.decagon.fitnessoapp.dto.AddressRequest;
import org.springframework.http.ResponseEntity;

public interface AddressService {
    ResponseEntity<?> createAddress(AddressRequest addressRequest);

    AddressRequest updateAddress(AddressRequest request);

    String deleteAddress(Long id);
}
