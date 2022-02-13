package com.decagon.fitnessoapp.service;

import com.decagon.fitnessoapp.dto.*;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface PersonService {

    ResponseEntity<AuthResponse> loginUser(AuthRequest req) throws Exception;

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

}
