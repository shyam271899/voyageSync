package com.example.blueeagle.Repository;

import com.example.blueeagle.Entity.SignUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignUpRepository extends JpaRepository<SignUp, Long> {
    boolean existsByEmail(String email);

    SignUp findByEmailIgnoreCase(String email);

    Optional<SignUp> findByEmail(String email);

    SignUp findByResetToken(String resetToken);
}
