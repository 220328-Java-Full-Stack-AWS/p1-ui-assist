package revagenda.models;

import revagenda.exceptions.Myexceptions;
import revagenda.persistence.ReimbursementDAO;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class Employee {

static  AbstractReimbursement abstractReimbursement;

public static void submit(int id, double amount, Date submitted, String description, int author
     , int status_id, int type_id) throws Myexceptions {

    AbstractReimbursement abstractReimbursement= new AbstractReimbursement(id, amount, submitted,  description, author
            ,status_id, type_id);
    ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
    try {
        reimbursementDAO.createbyemployee(abstractReimbursement);
    }catch (Myexceptions e){
        System.out.println("This ID is taken for another reimbursement please choose another ID ");
    }
}

public static void edit(int id, double amount, String description) {

    ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
    AbstractReimbursement abstractReimbursement = reimbursementDAO.read(id);

    abstractReimbursement.setAmount(amount);
    abstractReimbursement.setDescription(description);

    reimbursementDAO.updateByEmployee(abstractReimbursement);
}

public static void cancel(int id){
    ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
    reimbursementDAO.cancelByEmployee(id);
}

public static List viewAll(int author){

     List<AbstractReimbursement> list = new LinkedList<>();
    ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
    list = reimbursementDAO.readAllByOneUser(author);
    return list;
 }

    public static List viewAllPending(int author){

        List<AbstractReimbursement> list = new LinkedList<>();
        ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
        list = reimbursementDAO.readAllPendingByOneUser(author);
        return list;
    }

    public static List viewAllApproved(int author){

        List<AbstractReimbursement> list = new LinkedList<>();
        ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
        list = reimbursementDAO.readAllApproveddByOneUser(author);
        return list;
    }

    public static List viewAllDenied(int author){

        List<AbstractReimbursement> list = new LinkedList<>();
        ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
        list = reimbursementDAO.readAllDeniedByOneUser(author);
        return list;
    }



}
