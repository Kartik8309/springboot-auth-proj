package com.example.cryptousersjwt.Repository;

import com.example.cryptousersjwt.Entity.Cryptos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
public interface CryptoRepository extends JpaRepository<Cryptos,Long> {
   /* @Modifying
    @Transactional
    @Query(
            value = "UPDATE cryptos SET cryptoname=?1",
            nativeQuery = true
    )
    Cryptos updateCryptoWithUserName(Cryptos crypto , String username);*/
}
