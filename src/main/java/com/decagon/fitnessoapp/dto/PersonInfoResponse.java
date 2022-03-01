package com.decagon.fitnessoapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonInfoResponse {
    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private String image;
    private String phoneNumber;

}
