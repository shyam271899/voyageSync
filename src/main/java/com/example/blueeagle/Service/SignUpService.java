package com.example.blueeagle.Service;

import com.example.blueeagle.Entity.SignUp;
import org.springframework.http.ResponseEntity;


public interface SignUpService {
    ResponseEntity<?> confirmEmail(String confirmationToken);


    ResponseEntity<?> saveSignUpUser(SignUp signUp);

     ResponseEntity<?> getMyProfile(String email);
}
