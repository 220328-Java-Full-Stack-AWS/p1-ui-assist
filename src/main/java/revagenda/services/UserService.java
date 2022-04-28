package revagenda.services;

import revagenda.exceptions.Myexceptions;
import revagenda.models.AbstractUser;
import revagenda.persistence.UserDAO;

import java.util.ArrayList;
import java.util.List;


public class UserService {

	private final UserDAO dao;

	public UserService() {

		this.dao = new UserDAO();
	}


	//public AbstractUser create(AbstractUser model) throws Myexceptions {
	public AbstractUser create(AbstractUser model) throws Myexceptions {
		List list = new ArrayList<>();
		list = dao.getAll();
		int lastId = list.size();
		int id = lastId + 1;
		model.setUserId(id);
		return dao.create(model);
	}

	//public AbstractUser read(int users_id) {
	public AbstractUser read(int users_id){

		return dao.read(users_id);
	}


	//public AbstractUser readbyu(String user_name) {
	public AbstractUser readbyu(String user_name) {

		return dao.readbyu(user_name);
	}


	//public AbstractUser readbyuP(String user_name, String password) {
	public AbstractUser readbyuP(String user_name, String password) {

		return dao.readbyuP(user_name, password);
	}


	//public void update(AbstractUser model) {
	public void update(AbstractUser model) {

		dao.update(model);
	}


	//public void delete(int id) {
	public void delete(int users_id) {

		dao.delete(users_id);
	}



	//public void delete(AbstractUser model) {
    public void delete(AbstractUser model) {

		dao.delete(model);
	}


	//public List<AbstractUser> getAll()
	public List<AbstractUser> getAll(){

		return dao.getAll();
	}


//public void changeRolebyAdminToUser(AbstractUser model){
public void changeRolebyAdminToUser(AbstractUser model){
		dao.changeRolebyAdminToUser(model);
}


//public void changeRolebyAdminToAdmin(AbstractUser model){
public void changeRolebyAdminToAdmin(AbstractUser model){
		dao.changeRolebyAdminToAdmin(model);
}

}

