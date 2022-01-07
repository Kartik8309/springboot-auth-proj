package com.example.cryptousersjwt.Repository;

import com.example.cryptousersjwt.Entity.Cryptos;
import com.example.cryptousersjwt.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    /*@Modifying
    @Transactional
    @Query(
            value = "UPDATE user_table SET  ",
            nativeQuery = true
    )
    Collection<Cryptos> updateCryptosListByUserName(Collection<Cryptos> cryptosCollection, String username);*/
}