package revagenda.models;

import java.sql.Date;


public class Reimbursement extends AbstractReimbursement {

    public Reimbursement() {
        super();
    }


    public Reimbursement(int id, double amount, Date submitted, Date resolved, String description,
                         int author, int resolver, int status_id, int type_id) {
        super(id, amount, submitted, resolved, description, author, resolver, status_id, type_id);
    }
}
