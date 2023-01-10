package com.shop.model.repository;

import com.shop.model.Zadania;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZadaniaDAO extends ListCrudRepository<Zadania,Long> {
    @Modifying
    @Query("UPDATE Zadania z SET z.wynikUzytkownika = :wynikuzytkownika WHERE z.id IN (SELECT zu.id FROM ZadaniaUser zu WHERE zu.user.id = :userId) AND z.id = :zadanieId")
    int updateWynikuzytkownika(Long userId, Long zadanieId, String wynikuzytkownika);



}
