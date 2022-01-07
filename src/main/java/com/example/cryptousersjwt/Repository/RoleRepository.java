package com.example.cryptousersjwt.Repository;

import com.example.cryptousersjwt.Roles.ERoles;
import com.example.cryptousersjwt.Roles.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Long> {

    Roles findByName(ERoles name);

}
