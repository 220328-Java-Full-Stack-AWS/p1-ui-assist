package revagenda;

import revagenda.exceptions.Myexceptions;
import revagenda.models.*;
import revagenda.persistence.ReimbursementDAO;
import revagenda.persistence.UserDAO;
import revagenda.validation.Validate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Employee Reimbursment System (ERS)");
        System.out.println();
        System.out.println("Are you a Guest who wants to register? ");
        String a1 = scanner.next();
        boolean b1 = true;
        boolean done = false;

        if (a1.equals("yes")) {
            UserDAO userDAO = new UserDAO();
            List list = new ArrayList<>();
            list = userDAO.getAll();
            int i = list.size();
            int id = i + 1;
            System.out.println("you user id is: " + id);

            System.out.println("Please enter your user name: ");
            String user_name = scanner.next();
            System.out.println("Please enter your password: ");
            String password = scanner.next();

            System.out.println("Please enter your first name: ");
            Scanner sc3 = new Scanner(System.in);
            String first_name = sc3.nextLine();
            System.out.println("Please enter your last name: ");
            Scanner sc4 = new Scanner(System.in);
            String last_name = sc4.nextLine();
            System.out.println("Please enter your email: ");
            String email = scanner.next();

            Guest guest = new Guest(id, user_name, password, first_name, last_name, email);
            try {
                guest.gusetRegister(guest);
            } catch (Myexceptions e) {
            }
        } else if (a1.equals("no")) {
            System.out.println("Are you a User or an Admin who wants to log in? ");
            String a2 = scanner.next();
            boolean b2 = true;
            if (a2.equals("yes")) {
                System.out.println("Please enter your user_name: ");
                String user_username = scanner.next();
                System.out.println("Please enter your password: ");
                String user_password = scanner.next();

                boolean vup = Validate.valiadeUserNameandPassWord(user_username, user_password);

                    if (vup) {
                        AbstractUser abstractUser = Guest.guestLogin(user_username, user_password);
                        System.out.println("Hello Dear " + abstractUser.getFirstName() + " " + abstractUser.getLastName());
                        System.out.println("Your id number is: " + abstractUser.getUserId());
                        do {
                            if (abstractUser.getRoleId() == 1) {

                                System.out.println("You can submit, cancel, view and edit reimbursements");
                                System.out.println("what do you want to do? ");
                                String a3 = scanner.next();
                                if (a3.equals("submit")) {

                                    ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
                                    List list = new ArrayList<>();
                                    list = reimbursementDAO.getAll();
                                    int i = list.size();
                                    int id = i + 1;
                                    System.out.println("The ID for your reimbursement is : " + id);

                                    System.out.println("How much do you need for this reimbursement? ");
                                    double r_amount = scanner.nextDouble();
                                    //Date submitted = Date.valueOf(LocalDate.now());
                                    //long millis=System.currentTimeMillis();
                                    //Date submitted =new java.sql.Date(millis);
                                    java.util.Date utilDate = new java.util.Date();
                                    Date submitted = new Date(utilDate.getTime());

                                    System.out.println("Please select the type of your reimbursement");
                                    System.out.println("1- LODGING, 2- FOOD, 3- TRAVEL, 4- SOMETHING ELSE");
                                    int type_id = scanner.nextInt();
                                    System.out.println("what is your description? ");
                                    Scanner sc2 = new Scanner(System.in);
                                    String r_description = sc2.nextLine();

                                    try {
                                        Employee.submit(id, r_amount, submitted, r_description, abstractUser.getUserId(),
                                                1, type_id);
                                    } catch (Myexceptions e) {
                                    }
                                } else if (a3.equals("cancel")) {
                                    System.out.println("what is your reimbursement id which you want to cancel? ");
                                    int id_to_cancel = scanner.nextInt();
                                    AbstractReimbursement abstractReimbursement = new AbstractReimbursement();
                                    ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
                                    abstractReimbursement = reimbursementDAO.read(id_to_cancel);
                                    if (abstractReimbursement.getStatus_id() != 1) {
                                        System.out.println("your request is not in pending status and " +
                                                "therefore can not be cancelled");
                                    } else {
                                        Employee.cancel(id_to_cancel);
                                        System.out.println("Your request has been canceled from our system.");
                                    }
                                } else if (a3.equals("view")) {
                                    System.out.println("view all?, view pendings?, view approved?, view denied?");
                                    String view = scanner.next();

                                    List<AbstractReimbursement> list = new LinkedList<>();
                                    if (view.equals("all")) {
                                        list = Employee.viewAll(abstractUser.getUserId());
                                        for(int i=0; i<list.size(); i++){
                                            System.out.println(list.get(i));
                                        }
                                        //System.out.println(list);
                                    } else if (view.equals("pendings")) {
                                        list = Employee.viewAllPending(abstractUser.getUserId());
                                        for(int i=0; i<list.size(); i++){
                                            System.out.println(list.get(i));
                                        }
                                        //System.out.println(list);
                                    } else if (view.equals("approved")) {
                                        list = Employee.viewAllApproved(abstractUser.getUserId());
                                        for(int i=0; i<list.size(); i++){
                                            System.out.println(list.get(i));
                                        }
                                        //System.out.println(list);
                                    } else if (view.equals("denied")) {
                                        list = Employee.viewAllDenied(abstractUser.getUserId());
                                        for(int i=0; i<list.size(); i++){
                                            System.out.println(list.get(i));
                                        }
                                        //System.out.println(list);
                                    }

                                } else if (a3.equals("edit")) {
                                    System.out.println("what is your reimbursement id? ");
                                    int reid = scanner.nextInt();


                                    AbstractReimbursement abstractReimbursement = new AbstractReimbursement();
                                    ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
                                    abstractReimbursement = reimbursementDAO.read(reid);
                                    if (abstractReimbursement.getStatus_id() != 1) {
                                        System.out.println("your request is not in pending status and" +
                                                "therefore can not be edited");
                                    } else {
                                        System.out.println("what is your new amount? ");
                                        int ramount = scanner.nextInt();
                                        System.out.println("Whst is the new description? ");
                                        String rdescription = scanner.next();

                                        Employee.edit(reid, ramount, rdescription);
                                    }
                                }
                            } else if (abstractUser.getRoleId() == 2) {
                                System.out.println("You can approve, deny, filter Reimbursements and also" +
                                        " change the roles ");
                                System.out.println("Now, what do You want to do? ");
                                String aad = scanner.next();
                                if (aad.equals("approve")) {

                                    System.out.println("what is the ID of the reimbursement? ");
                                    int idr = scanner.nextInt();
                                    Date date = Date.valueOf(LocalDate.now());
                                    //int id, Date resolved, int resolver, int status_id
                                    Admin.update(idr, date, abstractUser.getUserId(), 2);
                                }
                                if (aad.equals("deny")) {
                                    System.out.println("what is the ID of the reimbursement? ");
                                    int idr = scanner.nextInt();
                                    Date date = Date.valueOf(LocalDate.now());
                                    //int id, Date resolved, int resolver, int status_id
                                    Admin.update(idr, date, abstractUser.getUserId(), 3);
                                }
                                if (aad.equals("filter")) {
                                    System.out.println("you can see all pendings, all approved, all denied and all canceled" +
                                            " reimbursements");
                                    System.out.println("Which reibmursements do you want to see?");
                                    List list = new ArrayList<>();
                                    String filter = scanner.next();
                                    if (filter.equals("pendings")) {
                                        list = Admin.filter(1);
                                        for(int i=0; i<list.size(); i++){
                                            System.out.println(list.get(i));
                                        }
                                        //System.out.println(list);
                                    }
                                    if (filter.equals("approved")) {
                                        list = Admin.filter(2);
                                        for(int i=0; i<list.size(); i++){
                                            System.out.println(list.get(i));
                                        }
                                        //System.out.println(list);
                                    }
                                    if (filter.equals("denied")) {
                                        list = Admin.filter(3);
                                        for(int i=0; i<list.size(); i++){
                                            System.out.println(list.get(i));
                                        }
                                        //System.out.println(list);
                                    }
                                    if (filter.equals("canceled")) {
                                        list = Admin.filter(4);
                                        for(int i=0; i<list.size(); i++){
                                            System.out.println(list.get(i));
                                        }
                                        //System.out.println(list);
                                    }
                                }
                                if (aad.equals("roles")) {
                                    System.out.println("what is the users_id that you want to change its role?");
                                    int usres_id = scanner.nextInt();
                                    System.out.println("what new Role do you want to give?");
                                    System.out.println("1- EMPLOYEE, 2- ADMIN");
                                    int role_id = scanner.nextInt();
                                    if (role_id == 1) {
                                        Admin.changeRole(usres_id, 1);
                                    }
                                    if (role_id == 2) {
                                        Admin.changeRole(usres_id, 2);
                                    }
                                }
                            } else {
                                System.out.println("The combination of username and password is not correct!");
                            }


                            System.out.println("Do you need anything else?");
                            String sdone = scanner.next();
                            if (sdone.equals("no")) {
                                done = true;
                            }


                        } while (done == false);
                    }
            }
        }
    }
}



