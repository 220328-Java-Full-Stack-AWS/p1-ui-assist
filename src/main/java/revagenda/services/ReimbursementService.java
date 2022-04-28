package revagenda.services;

import revagenda.exceptions.Myexceptions;
import revagenda.models.AbstractReimbursement;
import revagenda.models.Employee;
import revagenda.persistence.ReimbursementDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {

    private ReimbursementDAO dao;

    public ReimbursementService(ReimbursementDAO dao){

        this.dao = dao;
    }

    public ReimbursementService() {

        this.dao = new ReimbursementDAO();
    }

    public boolean checkAllTheReimburement(String description)throws SQLException {

        List<String> list = dao.readAllByDescription(description);

        return  list.contains(description);

    }





    //public AbstractReimbursement create(AbstractReimbursement model) throws Myexceptions {
    public AbstractReimbursement create(AbstractReimbursement model) throws Myexceptions {
        return dao.create(model);
    }



//public AbstractReimbursement createbyemployee(AbstractReimbursement model) throws Myexceptions{
public AbstractReimbursement createbyemployee(AbstractReimbursement model) throws Myexceptions{

    List list = new ArrayList<>();
    list = dao.getAll();
    int lastId = list.size();
    int id = lastId + 1;
    model.setId(id);

        java.util.Date utilDate = new java.util.Date();
    Date submitted = new Date(utilDate.getTime());
    model.setSubmitted(submitted);
        model.setResolved(null);
        model.setStatus_id(1);

        return dao.createbyemployee(model);
}


//public AbstractReimbursement read(int id) {
public AbstractReimbursement read(int id) {
        return dao.read(id);
}


//public List<AbstractReimbursement> readAllByOneUser(int author) {
public List<AbstractReimbursement> readAllByOneUser(int author) {
        return dao.readAllByOneUser(author);
}


//public List<AbstractReimbursement> readAllPendingByOneUser(int author) {
public List<AbstractReimbursement> readAllPendingByOneUser(int author) {
        return dao.readAllPendingByOneUser(author);
}


//public List<AbstractReimbursement> readAllApproveddByOneUser(int author) {
public List<AbstractReimbursement> readAllApproveddByOneUser(int author) {
        return dao.readAllApproveddByOneUser(author);
}


//public List<AbstractReimbursement> readAllDeniedByOneUser(int author) {
public List<AbstractReimbursement> readAllDeniedByOneUser(int author) {
        return  dao.readAllDeniedByOneUser(author);
}



//public void update(AbstractReimbursement model) {
public void update(AbstractReimbursement model) {
        dao.update(model);
}

//public void cancelByEmployee(int id) {
public void cancelByEmployee(int id) {
        dao.cancelByEmployee(id);
}


//public void updateByEmployee(AbstractReimbursement model) {
public void updateByEmployee(AbstractReimbursement model) {

        dao.updateByEmployee(model);
}

//public void updateByAdmin(AbstractReimbursement model) {
public void updateByAdmin(AbstractReimbursement model) {
        dao.updateByAdmin(model);
}

//public void delete(int id) {
public void delete(int id) {
    dao.delete(id);
}

//public void delete(AbstractReimbursement model) {
public void delete(AbstractReimbursement model) {
        dao.delete(model);
}

//public List<AbstractReimbursement> getAll() {
public List<AbstractReimbursement> getAll() {
        return dao.getAll();
}

//public List readAllPendingsbyAdmin(){
public List readAllPendingsbyAdmin(){
        return dao.readAllPendingsbyAdmin();
}

//public List readAllApprovedbyAdmin(){
public List readAllApprovedbyAdmin(){
        return dao.readAllApprovedbyAdmin();
}


//public List readAllDeniedbyAdmin(){
public List readAllDeniedbyAdmin(){
        return dao.readAllDeniedbyAdmin();
}

//public List readAllCancelledbyAdmin(){
public List readAllCancelledbyAdmin(){
        return dao.readAllCancelledbyAdmin();
}

}
