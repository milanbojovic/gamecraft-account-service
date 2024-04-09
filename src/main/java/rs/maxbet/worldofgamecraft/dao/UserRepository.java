package rs.maxbet.worldofgamecraft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.maxbet.worldofgamecraft.data.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByName(String name);

}
