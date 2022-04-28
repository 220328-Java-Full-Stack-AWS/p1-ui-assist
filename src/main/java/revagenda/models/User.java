package revagenda.models;


public class User extends AbstractUser {

    public User() {
        super();
    }


    public User(int users_id, String username, String password, String first_name,
                String last_name, String email, int role_id) {
        super(users_id, username, password, first_name, last_name, email, role_id);
    }
}
