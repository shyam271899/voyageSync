package com.example.blueeagle.Controller;

import com.example.blueeagle.Entity.SignUp;
import com.example.blueeagle.Service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SignUpController {

    @Autowired
    private SignUpService signUpService;
    @PostMapping("/signUp")
    public ResponseEntity<?> registerSignUp(@RequestBody SignUp signUp) {
        return signUpService.saveSignUpUser(signUp);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmSignUpAccount(@RequestParam("token")String confirmationToken) {
        return signUpService.confirmEmail(confirmationToken);
    }

    @GetMapping("/my-profile")
    public ResponseEntity<?> getMyProfile(@RequestParam String email) {
        return signUpService.getMyProfile(email);
    }

}
