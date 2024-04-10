package rs.maxbet.worldofgamecraft.data.transport;

import lombok.Data;
import rs.maxbet.worldofgamecraft.data.Users;

@Data
public class UserRepresentation {
    private int id;
    private String role;

    public UserRepresentation(Users user) {
        this.id = user.getId();
        this.role = user.getRole();
    }
}
