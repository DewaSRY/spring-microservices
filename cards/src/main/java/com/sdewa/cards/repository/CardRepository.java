package com.sdewa.cards.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sdewa.cards.entity.Cards;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Cards, Long>  {

    Optional<Cards> findByMobileNumber(String mobileNumber);

    Optional<Cards> findByCardNumber(String cardNumber);
}
