package com.decagon.fitnessoapp.controller;

import com.decagon.fitnessoapp.dto.AddressRequest;
import com.decagon.fitnessoapp.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {

    public final AddressService addressService;

    @PostMapping("/addAddress")
    public ResponseEntity<?> addAddress(@RequestBody AddressRequest addressRequest){
        return addressService.createAddress(addressRequest);
    }

    @PutMapping("/update_address/")
    public ResponseEntity<AddressRequest> updateAddress(@RequestBody AddressRequest addressRequest) {
        return ResponseEntity.ok().body(addressService.updateAddress(addressRequest));
    }

    @DeleteMapping("/delete_address/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable("addressId") Long addressId) {
        return ResponseEntity.ok().body(addressService.deleteAddress(addressId));
    }
}
