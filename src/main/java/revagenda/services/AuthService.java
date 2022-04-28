package revagenda.services;

import revagenda.exceptions.Myexceptions;
import revagenda.models.AbstractUser;
import revagenda.models.Guest;
import revagenda.persistence.UserDAO;
import revagenda.validation.Validate;

import java.util.ArrayList;
import java.util.List;


public class AuthService {

    private final UserDAO userDAO;

    public AuthService(){
        this.userDAO = new UserDAO();
    }

   public AbstractUser login(AbstractUser model){

        return userDAO.read(model);
   }



    public AbstractUser loginbyup(String username, String password) {

        UserDAO userDAO = new UserDAO();
        //List<AbstractUser> abstractUserList = userDAO.getAll();
        AbstractUser u =userDAO.readbyuP(username, password);
        return u;
    }


    public AbstractUser register(AbstractUser model){
        UserDAO userDAO = new UserDAO();
        List list = new ArrayList<>();
        list = userDAO.getAll();
        boolean b = true;
        for (int i = 1; i <= list.size(); i++){
            AbstractUser u = userDAO.read(i);


            int lastId = list.size();
            int id = lastId + 1;
            model.setUserId(id);

            if(model.getUsername().equals(u.getUsername()) ){
                System.out.println("username has been taken. Please use another user name.");
                b = false;
            }
        }
        for (int i = 1; i <= list.size(); i++){
            AbstractUser u = userDAO.read(i);
            if(model.getEmail().equals(u.getEmail()) ){
                System.out.println("This email has been taken. Please use another email.");
                b = false;
            }
        }
        if(!Validate.validateEmailAddress(model.getEmail())){
            System.out.println("Email Address is not valid, Please enter a valid email address");
            b = false;
        }

        if(!Validate.validateFirstName(model.getFirstName())){
            System.out.println("Please enter a valid first name");
            b = false;
        }

        if(!Validate.validateLastName(model.getLastName())){
            System.out.println("Please enter a valid last name");
            b = false;
        }

        if(b == true){AbstractUser new_user = new AbstractUser(model.getUserId(), model.getUsername(),
                model.getPassword(), model.getFirstName(), model.getLastName(),
                model.getEmail(), 1);
            try {
                userDAO.create(new_user);
            }
            catch (Myexceptions e){
                System.out.println("This id was taken by another employee, Please choose another ID ");
            }


            return new_user;
        }
        return null;
    }


    public AbstractUser register(Guest guest) throws Myexceptions {
        UserDAO userDAO = new UserDAO();
        List list = new ArrayList<>();
        list = userDAO.getAll();
        boolean b = true;
        for (int i = 1; i <= list.size(); i++){
            AbstractUser u = userDAO.read(i);

            if(guest.getGuest_username().equals(u.getUsername()) ){
                System.out.println("username has been taken. Please use another user name.");
               b = false;
            }
            }
        for (int i = 1; i <= list.size(); i++){
            AbstractUser u = userDAO.read(i);
            if(guest.getGuest_email().equals(u.getEmail()) ){
                System.out.println("This email has been taken. Please use another email.");
                b = false;
            }
        }
        if(!Validate.validateEmailAddress(guest.getGuest_email())){
            System.out.println("Email Address is not valid, Please enter a valid email address");
            b = false;
        }

        if(!Validate.validateFirstName(guest.getGuset_first_name())){
            System.out.println("Please enter a valid first name");
            b = false;
        }

        if(!Validate.validateLastName(guest.getGuest_last_name())){
            System.out.println("Please enter a valid last name");
            b = false;
        }

        if(b == true){AbstractUser new_user = new AbstractUser(guest.getGuest_id(), guest.getGuest_username(),
                guest.getGuest_password(), guest.getGuset_first_name(), guest.getGuest_last_name(),
                guest.getGuest_email(), 1);
            try {
            userDAO.create(new_user);
                }
            catch (Myexceptions e){
            System.out.println("This id was taken by another employee, Please choose another ID ");
                }


       return new_user;
        }
    return null;
    }


}
