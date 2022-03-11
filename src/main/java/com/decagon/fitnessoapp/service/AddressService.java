package com.decagon.fitnessoapp.service;

import com.decagon.fitnessoapp.dto.AddressRequest;
import com.decagon.fitnessoapp.dto.AddressResponse;
import org.springframework.http.ResponseEntity;

public interface AddressService {
    AddressResponse createAddress(AddressRequest addressRequest);
}
