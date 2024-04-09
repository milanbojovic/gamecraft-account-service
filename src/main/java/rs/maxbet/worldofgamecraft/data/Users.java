package rs.maxbet.worldofgamecraft.data;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="users_seq")
    @SequenceGenerator(name="users_seq", sequenceName="users_seq", allocationSize=1)
    private int id;
    private String name;
    private String password;
    private String role;

}
