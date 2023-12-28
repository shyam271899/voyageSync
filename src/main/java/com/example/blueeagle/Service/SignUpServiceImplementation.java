package com.example.blueeagle.Service;

import com.example.blueeagle.Entity.ConfirmationToken;
import com.example.blueeagle.Entity.SignUp;
import com.example.blueeagle.Repository.ConfirmationTokenRepository;
import com.example.blueeagle.Repository.SignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class SignUpServiceImplementation implements SignUpService {

   @Autowired
   private SignUpRepository signUpRepository;

   @Autowired
   private ConfirmationTokenRepository confirmationTokenRepository;

   @Autowired
   private EmailService emailService;


    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            SignUp signUp = signUpRepository.findByEmailIgnoreCase(token.getSignUpEntity().getEmail());
            signUp.setEnabled(true);
            signUpRepository.save(signUp);
            return ResponseEntity.ok("Email verified successfully! Redirecting...");
        }

        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }

    @Override
    public ResponseEntity<?> saveSignUpUser(SignUp signUp) {
        if (signUpRepository.existsByEmail(signUp.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        // Calculate age based on date of birth
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(signUp.getDateOfBirth(), currentDate);

        // Check if the age is below 13
        if (age.getYears() < 13) {
            return ResponseEntity.badRequest().body("Error: User must be at least 13 years old to register.");
        }

        signUpRepository.save(signUp);

        ConfirmationToken confirmationToken = new ConfirmationToken(signUp);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(signUp.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://127.0.0.1:8080/confirm-account?token="+ confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }

    @Override
    public ResponseEntity<?> getMyProfile(String email) {

            SignUp signUp = signUpRepository.findByEmailIgnoreCase(email);

            if (signUp == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(signUp);
        }
    }




