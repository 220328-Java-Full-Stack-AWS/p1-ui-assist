package revagenda.models;

import revagenda.persistence.ReimbursementDAO;
import revagenda.persistence.UserDAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Admin {
    static  AbstractReimbursement abstractReimbursement;
    static ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
    static AbstractUser abstractUser;
    static UserDAO userDAO= new UserDAO();

    public static void update(int id, Date resolved, int resolver, int status_id){
     abstractReimbursement = reimbursementDAO.read(id);
     abstractReimbursement.setResolved(resolved);
     abstractReimbursement.setResolver(resolver);
     abstractReimbursement.setStatus_id(status_id);
     reimbursementDAO.updateByAdmin(abstractReimbursement);
}
//should return List
public static List filter(int status_id){

        List list = new ArrayList<>();
        if(status_id == 1){
            list = reimbursementDAO.readAllPendingsbyAdmin();

        }
        if(status_id == 2){
            list = reimbursementDAO.readAllApprovedbyAdmin();


        }
        if(status_id == 3){
            list = reimbursementDAO.readAllDeniedbyAdmin();

        }

        if(status_id == 4){
            list = reimbursementDAO.readAllCancelledbyAdmin();

        }
      return list;
    }

    public static void changeRole(int users_id, int role_id){
        abstractUser = userDAO.read(users_id);
        if(role_id == 1){
            userDAO.changeRolebyAdminToUser(abstractUser);
        }
        if(role_id == 2){
            userDAO.changeRolebyAdminToAdmin(abstractUser);
        }

    }

}
