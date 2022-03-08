package com.decagon.fitnessoapp.service;

import com.decagon.fitnessoapp.dto.*;
import com.decagon.fitnessoapp.model.user.Person;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.List;

public interface PersonService {

    ResponseEntity<AuthResponse> loginUser(AuthRequest req) throws Exception;

    PersonInfoResponse getInfo(Authentication authentication) throws Exception;

    UpdatePersonResponse updateUserDetails(UpdatePersonRequest updatePersonRequest);

    PersonResponse register(PersonRequest personRequest) throws MailjetSocketTimeoutException, MailjetException, IOException;

    PersonResponse addTrainer(PersonRequest personRequest);

    ResponseEntity<String> removeTrainer(Long id);

    PersonResponse sendingEmail(String email) throws MailjetSocketTimeoutException, MailjetException;

    ChangePasswordResponse updateCurrentPassword(ChangePasswordRequest changePasswordRequest);

    PersonResponse resetPasswordToken(String email) throws MailjetSocketTimeoutException, MailjetException;

    PersonResponse updateResetPassword(ResetPasswordRequest passwordRequest, String token);

    void resetPasswordMailSender(String email, String token) throws MailjetSocketTimeoutException,
            MailjetException;

    String buildEmail(String name, String link);

    Page<Person> getAllUsers(int pageNumber);
}
