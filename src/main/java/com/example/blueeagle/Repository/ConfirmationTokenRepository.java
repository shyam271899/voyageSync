package com.example.blueeagle.Repository;

import com.example.blueeagle.Entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("confirmationTokenRepository")
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);


    default ConfirmationToken findBySignUpEntitySignUpId(long signUpId) {
        return null;
    }
}
