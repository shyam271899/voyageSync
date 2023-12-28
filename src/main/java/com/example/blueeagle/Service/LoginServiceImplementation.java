package com.example.blueeagle.Service;

import com.example.blueeagle.Entity.SignUp;
import com.example.blueeagle.Repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImplementation implements LoginService {
   @Autowired
    private SignUpRepository signUpRepository;

    @Autowired
    public LoginServiceImplementation(SignUpRepository signUpRepository) {
        this.signUpRepository = signUpRepository;
    }

    @Override
    public String login(String email, String password) {
        SignUp signUp = signUpRepository.findByEmailIgnoreCase(email);

        if (signUp == null || !signUp.getPassword().equals(password) || !signUp.isEnabled()) {
            return "Invalid email, password or User is not verified";
        } else {
            return "Login successful";
        }
    }
}
