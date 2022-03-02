package com.decagon.fitnessoapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class PersonInfoResponse {
    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private String image;
    private String phoneNumber;
    private AddressRequest address;

}
