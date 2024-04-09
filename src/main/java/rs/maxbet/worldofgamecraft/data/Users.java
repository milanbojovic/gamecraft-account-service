package rs.maxbet.worldofgamecraft.data;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Users {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String role;

}
