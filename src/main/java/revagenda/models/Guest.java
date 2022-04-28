package revagenda.models;

import revagenda.exceptions.Myexceptions;
import revagenda.services.AuthService;

public class Guest {


    AuthService a = new AuthService();
    static AuthService b = new AuthService();
    private int guest_id;
    private String guest_username;
    private String guest_password;
    private String guset_first_name;
    private String guest_last_name;
    private String guest_email;



    public Guest(int guest_id, String guest_username, String guest_password, String guset_first_name,
                 String guest_last_name, String guest_email) {
        this.guest_id = guest_id;
        this.guest_username = guest_username;
        this.guest_password = guest_password;
        this.guset_first_name = guset_first_name;
        this.guest_last_name = guest_last_name;
        this.guest_email = guest_email;
    }

    public int getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(int guest_id) {
        this.guest_id = guest_id;
    }

    public String getGuest_username() {
        return guest_username;
    }

    public void setGuest_username(String guest_username) {
        this.guest_username = guest_username;
    }

    public String getGuest_password() {
        return guest_password;
    }

    public void setGuest_password(String guest_password) {
        this.guest_password = guest_password;
    }

    public String getGuset_first_name() {
        return guset_first_name;
    }

    public void setGuset_first_name(String guset_first_name) {
        this.guset_first_name = guset_first_name;
    }

    public String getGuest_last_name() {
        return guest_last_name;
    }

    public void setGuest_last_name(String guest_last_name) {
        this.guest_last_name = guest_last_name;
    }

    public String getGuest_email() {
        return guest_email;
    }

    public void setGuest_email(String guest_email) {
        this.guest_email = guest_email;
    }

    public void gusetRegister(Guest guest) throws Myexceptions {

        a.register(guest);
        throw new Myexceptions();
}

public static AbstractUser guestLogin(String user_name, String password){
          AbstractUser a = b.loginbyup(user_name, password);
          return a;
    }

}
