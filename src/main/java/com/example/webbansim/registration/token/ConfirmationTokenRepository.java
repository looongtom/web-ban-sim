package com.example.webbansim.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository
        extends JpaRepository<ConfirmationToken,Long> {
    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("update ConfirmationToken c " +
            "set c.confirmedAt = ?1 " +
            "where c.token = ?2")
    int updateConfirmedAt(LocalDateTime confirmedAt, String token);
}
