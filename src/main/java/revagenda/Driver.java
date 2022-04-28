package revagenda;

import revagenda.services.ReimbursementService;

import java.sql.SQLException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("whst is the description?");
        String description = scanner.nextLine();
        ReimbursementService reimbursementService = new ReimbursementService();
        boolean b = reimbursementService.checkAllTheReimburement(description);
        if(b == false){
            System.out.println("There is no Reimbursement with this description");
        }
        else if(b == true){
            System.out.println("Yes, there is Reimbuserment(s) with this description");
        }

    }





}
