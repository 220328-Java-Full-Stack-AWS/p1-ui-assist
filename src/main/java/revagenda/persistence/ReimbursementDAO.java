package revagenda.persistence;

import revagenda.ConnectionManager;
import revagenda.exceptions.Myexceptions;
import revagenda.models.AbstractReimbursement;
import revagenda.models.Reimbursement;
import revagenda.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ReimbursementDAO implements CRUDInterface<AbstractReimbursement> {

    @Override
    public AbstractReimbursement create(AbstractReimbursement model) throws Myexceptions {
        String sql = "INSERT INTO REIMBURSEMENT (id, amount, submitted, resolved, " +
                " description, author, resolver, status_id, type_id) VALUES (?,?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, model.getId());
            pstmt.setDouble(2, model.getAmount());
            pstmt.setDate(3, model.getSubmitted());
            pstmt.setDate(4, model.getResolved());
            pstmt.setString(5, model.getDescription());
            pstmt.setInt(6, model.getAuthor());
            pstmt.setInt(7, model.getResolver());
            pstmt.setInt(8, model.getStatus_id());
            pstmt.setInt(9, model.getType_id());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new Myexceptions();
            //e.printStackTrace();
        }
        return model;
    }

    public AbstractReimbursement createbyemployee(AbstractReimbursement model) throws Myexceptions{

        //id, amount, submitted,  description, author
        //            ,status_id, type_id
        String sql = "INSERT INTO REIMBURSEMENT (id, amount,submitted, description, author, status_id, type_id) VALUES " +
                "(?,?,?,?,?,?,?)";
        try {

            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, model.getId());
            pstmt.setDouble(2, model.getAmount());
            pstmt.setDate(3,model.getSubmitted());
            pstmt.setString(4, model.getDescription());
            pstmt.setInt(5, model.getAuthor());
            pstmt.setInt(6,model.getStatus_id());
            pstmt.setInt(7, model.getType_id());


            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new Myexceptions();
            //e.printStackTrace();
        }
        return model;
    }

    public List<String> readAllByDescription(String description) throws SQLException {
        List<String> list = new ArrayList<>();

        String SQL = "SELECT * FROM REIMBURSEMENT WHERE description = ?";
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(SQL);
        preparedStatement.setString(1, description);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            list.add(resultSet.getString("description"));
        }

        return list;
    }

    @Override
    public AbstractReimbursement read(int id) {
        AbstractReimbursement model = new AbstractReimbursement();

        try {
            String SQL = "SELECT * FROM REIMBURSEMENT WHERE id = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                model.setId(rs.getInt("id"));
                model.setAmount(rs.getDouble("amount"));
                model.setSubmitted(rs.getDate("submitted"));
                model.setResolved(rs.getDate("resolved"));
                model.setDescription(rs.getString("description"));
                model.setAuthor(rs.getInt("author"));
                model.setResolver(rs.getInt("resolver"));
                model.setStatus_id(rs.getInt("status_id"));
                model.setType_id(rs.getInt("type_id"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public List<AbstractReimbursement> readAllByOneUser(int author) {
        List<AbstractReimbursement> list = new LinkedList<>();

        try {
            String SQL = "SELECT * FROM REIMBURSEMENT WHERE author = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, author);

            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                AbstractReimbursement model = new AbstractReimbursement();
                model.setId(rs.getInt("id"));
                model.setAmount(rs.getDouble("amount"));
                model.setSubmitted(rs.getDate("submitted"));
                model.setResolved(rs.getDate("resolved"));
                model.setDescription(rs.getString("description"));
                model.setAuthor(rs.getInt("author"));
                model.setResolver(rs.getInt("resolver"));
                model.setStatus_id(rs.getInt("status_id"));
                model.setType_id(rs.getInt("type_id"));
                list.add(model);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<AbstractReimbursement> readAllPendingByOneUser(int author) {

        List<AbstractReimbursement> list = new LinkedList<>();

        int status_id = 1;
        try {
            String SQL = "SELECT * FROM REIMBURSEMENT WHERE author = ? and status_id = ? ";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, author);
            pstmt.setInt(2, status_id);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                AbstractReimbursement model = new AbstractReimbursement();
                model.setId(rs.getInt("id"));
                model.setAmount(rs.getDouble("amount"));
                model.setSubmitted(rs.getDate("submitted"));
                model.setResolved(rs.getDate("resolved"));
                model.setDescription(rs.getString("description"));
                model.setAuthor(rs.getInt("author"));
                model.setResolver(rs.getInt("resolver"));
                model.setStatus_id(rs.getInt("status_id"));
                model.setType_id(rs.getInt("type_id"));
                list.add(model);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<AbstractReimbursement> readAllApproveddByOneUser(int author) {

        List<AbstractReimbursement> list = new LinkedList<>();

        int status_id = 2;
        try {
            String SQL = "SELECT * FROM REIMBURSEMENT WHERE author = ? and status_id = ? ";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, author);
            pstmt.setInt(2, status_id);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                AbstractReimbursement model = new AbstractReimbursement();
                model.setId(rs.getInt("id"));
                model.setAmount(rs.getDouble("amount"));
                model.setSubmitted(rs.getDate("submitted"));
                model.setResolved(rs.getDate("resolved"));
                model.setDescription(rs.getString("description"));
                model.setAuthor(rs.getInt("author"));
                model.setResolver(rs.getInt("resolver"));
                model.setStatus_id(rs.getInt("status_id"));
                model.setType_id(rs.getInt("type_id"));
                list.add(model);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<AbstractReimbursement> readAllDeniedByOneUser(int author) {

        List<AbstractReimbursement> list = new LinkedList<>();

        int status_id = 3;
        try {
            String SQL = "SELECT * FROM REIMBURSEMENT WHERE author = ? and status_id = ? ";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, author);
            pstmt.setInt(2, status_id);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                AbstractReimbursement model = new AbstractReimbursement();
                model.setId(rs.getInt("id"));
                model.setAmount(rs.getDouble("amount"));
                model.setSubmitted(rs.getDate("submitted"));
                model.setResolved(rs.getDate("resolved"));
                model.setDescription(rs.getString("description"));
                model.setAuthor(rs.getInt("author"));
                model.setResolver(rs.getInt("resolver"));
                model.setStatus_id(rs.getInt("status_id"));
                model.setType_id(rs.getInt("type_id"));
                list.add(model);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public void update(AbstractReimbursement model) {
        String sql = "UPDATE REIMBURSEMENT SET AMOUNT = ?, SUBMITTED = ?, RESOLVED = ?, " +
                "DESCRIPTION = ?, AUTHOR = ?, RESOLVER = ?, STATUS_ID = ?, TYPE_ID = ?  WHERE ID = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);

            pstmt.setDouble(1, model.getAmount());
            pstmt.setDate(2, model.getSubmitted());
            pstmt.setDate(3, model.getResolved());
            pstmt.setString(4, model.getDescription());
            pstmt.setInt(5, model.getAuthor());
            pstmt.setInt(6, model.getResolver());
            pstmt.setInt(7, model.getStatus_id());
            pstmt.setInt(8, model.getType_id());
            pstmt.setInt(9, model.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelByEmployee(int id) {
        String sql = "update reimbursement set status_id = ? where id = ?";

        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, 4);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }



    public void updateByEmployee(AbstractReimbursement model) {
        String sql = "update reimbursement set amount = ?, description = ? where id = ?";
        try {

            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);

            pstmt.setDouble(1, model.getAmount());
            pstmt.setString(2, model.getDescription());
            pstmt.setInt(3, model.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateByAdmin(AbstractReimbursement model) {
        String sql = "update reimbursement set resolved = ?, resolver = ?, status_id = ? where id = ?";
        try {

            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);

            pstmt.setDate(1, model.getResolved());
            pstmt.setInt(2, model.getResolver());
            pstmt.setInt(3, model.getStatus_id());
            pstmt.setInt(4, model.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM reimbursement WHERE id = ?";

        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(AbstractReimbursement model) {
        int id = model.getId();
        String sql = "delete from reimbursement where id = ?";

        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AbstractReimbursement> getAll() {
        List<AbstractReimbursement> list = new LinkedList<>();
        try {
            String SQL = "SELECT * FROM reimbursement";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);

            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                AbstractReimbursement model = new AbstractReimbursement();

                model.setId(rs.getInt("id"));
                model.setAmount(rs.getDouble("amount"));
                model.setSubmitted(rs.getDate("submitted"));
                model.setResolved(rs.getDate("resolved"));
                model.setDescription(rs.getString("description"));
                model.setAuthor(rs.getInt("author"));
                model.setResolver(rs.getInt("resolver"));
                model.setStatus_id(rs.getInt("status_id"));
                model.setType_id(rs.getInt("type_id"));
                list.add(model);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    public List readAllPendingsbyAdmin(){
        List<AbstractReimbursement> list = new LinkedList<>();

        int status_id = 1;
        try {
            String SQL = "SELECT * FROM REIMBURSEMENT WHERE status_id = ? ";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);

            pstmt.setInt(1, status_id);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                AbstractReimbursement model = new AbstractReimbursement();
                model.setId(rs.getInt("id"));
                model.setAmount(rs.getDouble("amount"));
                model.setSubmitted(rs.getDate("submitted"));
                model.setResolved(rs.getDate("resolved"));
                model.setDescription(rs.getString("description"));
                model.setAuthor(rs.getInt("author"));
                model.setResolver(rs.getInt("resolver"));
                model.setStatus_id(rs.getInt("status_id"));
                model.setType_id(rs.getInt("type_id"));
                list.add(model);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }




     public List readAllApprovedbyAdmin(){
         List<AbstractReimbursement> list = new LinkedList<>();

         int status_id = 2;
         try {
             String SQL = "SELECT * FROM REIMBURSEMENT WHERE status_id = ? ";
             Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL);

             pstmt.setInt(1, status_id);

             ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
                 AbstractReimbursement model = new AbstractReimbursement();
                 model.setId(rs.getInt("id"));
                 model.setAmount(rs.getDouble("amount"));
                 model.setSubmitted(rs.getDate("submitted"));
                 model.setResolved(rs.getDate("resolved"));
                 model.setDescription(rs.getString("description"));
                 model.setAuthor(rs.getInt("author"));
                 model.setResolver(rs.getInt("resolver"));
                 model.setStatus_id(rs.getInt("status_id"));
                 model.setType_id(rs.getInt("type_id"));
                 list.add(model);

             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return list;
     }


     public List readAllDeniedbyAdmin(){
         List<AbstractReimbursement> list = new LinkedList<>();

         int status_id = 3;
         try {
             String SQL = "SELECT * FROM REIMBURSEMENT WHERE status_id = ? ";
             Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL);

             pstmt.setInt(1, status_id);

             ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
                 AbstractReimbursement model = new AbstractReimbursement();
                 model.setId(rs.getInt("id"));
                 model.setAmount(rs.getDouble("amount"));
                 model.setSubmitted(rs.getDate("submitted"));
                 model.setResolved(rs.getDate("resolved"));
                 model.setDescription(rs.getString("description"));
                 model.setAuthor(rs.getInt("author"));
                 model.setResolver(rs.getInt("resolver"));
                 model.setStatus_id(rs.getInt("status_id"));
                 model.setType_id(rs.getInt("type_id"));
                 list.add(model);

             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return list;

    }


     public List readAllCancelledbyAdmin(){
         List<AbstractReimbursement> list = new LinkedList<>();

         int status_id = 4;
         try {
             String SQL = "SELECT * FROM REIMBURSEMENT WHERE status_id = ? ";
             Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL);

             pstmt.setInt(1, status_id);

             ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
                 AbstractReimbursement model = new AbstractReimbursement();
                 model.setId(rs.getInt("id"));
                 model.setAmount(rs.getDouble("amount"));
                 model.setSubmitted(rs.getDate("submitted"));
                 model.setResolved(rs.getDate("resolved"));
                 model.setDescription(rs.getString("description"));
                 model.setAuthor(rs.getInt("author"));
                 model.setResolver(rs.getInt("resolver"));
                 model.setStatus_id(rs.getInt("status_id"));
                 model.setType_id(rs.getInt("type_id"));
                 list.add(model);

             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return list;





    }









    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */

    public Optional<Reimbursement> getById(int id) {
        return Optional.empty();
    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(Status status) {
        return Collections.emptyList();
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement) {
    	return null;
    }
}
