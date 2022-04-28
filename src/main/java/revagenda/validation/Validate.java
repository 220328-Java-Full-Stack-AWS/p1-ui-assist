package revagenda.validation;


import revagenda.models.AbstractUser;
import revagenda.persistence.UserDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Validate {

    public static boolean validateEmailAddress(String email){

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        //String kyle = "^([0-9a-zA-Z.]+@[0-9a-zA-Z]+[.][a-zA-Z]+){1,40}$";
        Pattern pattern = Pattern.compile(emailRegex);
        /*Matcher matcher = pattern.matcher(email);
        boolean bool = matcher.matches();
        if(bool){
            return true;
        }
        else{
            return false;
        }*/
        if (email == null)
            return false;
        return pattern.matcher(email).matches();
    }

    public static boolean validateFirstName( String firstName )
    {
        return firstName.matches( "[a-zA-Z]*" );
    }

    public static boolean validateLastName( String lastName )
    {

        return lastName.matches( "[a-zA-z]+([ '-][a-zA-Z]+)*" );
    }




    public static boolean valiadeUserNameandPassWord(String username, String password)  {
        List list  = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        list = userDAO.getAll();
        boolean b = false;
        for(int i = 1; i <= list.size() ; i++){
            AbstractUser user = userDAO.read(i);

            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                b = true;
            }
        }

        return b;
    }


}
