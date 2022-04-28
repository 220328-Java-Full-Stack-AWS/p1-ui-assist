package revagenda.persistence;

import revagenda.ConnectionManager;
import revagenda.exceptions.Myexceptions;
import revagenda.models.AbstractUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDAO implements CRUDInterface<AbstractUser> {


    @Override
    public AbstractUser create(AbstractUser model) throws Myexceptions {


        String sql = "INSERT INTO users (users_id, username, password, first_name, " +
                "last_name, email, role_id) VALUES (?, ?, ?,?,?,?,?)";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, model.getUserId());
            pstmt.setString(2, model.getUsername());
            pstmt.setString(3, model.getPassword());
            pstmt.setString(4, model.getFirstName());
            pstmt.setString(5, model.getLastName());
            pstmt.setString(6, model.getEmail());
            pstmt.setInt(7, model.getRoleId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new Myexceptions();
            //e.printStackTrace();
        }

        return model;
    }

    public AbstractUser read(AbstractUser model) {
        System.out.println("yes ia ma here");
        System.out.println(model.getPassword());
        AbstractUser outModel = new AbstractUser();
        if(model.getPassword() != null && model.getUsername() != null){
            String SQL = "SELECT * from users WHERE username = ? AND password = ?";
            try{
                PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL);
                pstmt.setString(1, model.getUsername());
                pstmt.setString(2, model.getPassword());
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    outModel.setUserId(rs.getInt("users_id"));
                    outModel.setUsername(rs.getString("username"));
                    outModel.setPassword((rs.getString("password")));
                    outModel.setFirstName(rs.getString("first_name"));
                    outModel.setLastName(rs.getString("last_name"));
                    outModel.setEmail(rs.getString("email"));
                    outModel.setRoleId(rs.getInt("role_id"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    return outModel;
    }

    @Override
    public AbstractUser read(int users_id) {
        AbstractUser abstractUser = new AbstractUser();
        try {
            String SQL = "SELECT * FROM USERS WHERE users_id = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            /*this.id = id;
            this.username = username;
            this.password = password;
            this.first_name = first_name;
            this.last_name = last_name;
            this.email = email;
            this.role_id = role_id;*/
            pstmt.setInt(1, users_id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                abstractUser.setUserId(rs.getInt("users_id"));
                abstractUser.setUsername(rs.getString("username"));
                abstractUser.setPassword((rs.getString("password")));
                abstractUser.setFirstName(rs.getString("first_name"));
                abstractUser.setLastName(rs.getString("last_name"));
                abstractUser.setEmail(rs.getString("email"));
                abstractUser.setRoleId(rs.getInt("role_id"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return abstractUser;

    }

    public AbstractUser readbyu(String user_name) {
        AbstractUser abstractUser = new AbstractUser();

        try {
            String SQL = "SELECT * FROM USERS WHERE username = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            /*this.id = id;
            this.username = username;
            this.password = password;
            this.first_name = first_name;
            this.last_name = last_name;
            this.email = email;
            this.role_id = role_id;*/
            pstmt.setString(1, user_name);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                abstractUser.setUserId(rs.getInt("users_id"));
                abstractUser.setUsername(rs.getString("username"));
                abstractUser.setPassword((rs.getString("password")));
                abstractUser.setFirstName(rs.getString("first_name"));
                abstractUser.setLastName(rs.getString("last_name"));
                abstractUser.setEmail(rs.getString("email"));
                abstractUser.setRoleId(rs.getInt("role_id"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return abstractUser;
    }

    public AbstractUser readbyuP(String user_name, String password) {
        AbstractUser abstractUser = new AbstractUser();

        try {
            String SQL = "SELECT * FROM USERS WHERE username = ? and password = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            /*this.id = id;
            this.username = username;
            this.password = password;
            this.first_name = first_name;
            this.last_name = last_name;
            this.email = email;
            this.role_id = role_id;*/
            pstmt.setString(1, user_name);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                abstractUser.setUserId(rs.getInt("users_id"));
                abstractUser.setUsername(rs.getString("username"));
                abstractUser.setPassword((rs.getString("password")));
                abstractUser.setFirstName(rs.getString("first_name"));
                abstractUser.setLastName(rs.getString("last_name"));
                abstractUser.setEmail(rs.getString("email"));
                abstractUser.setRoleId(rs.getInt("role_id"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return abstractUser;
    }


    @Override
    public void update(AbstractUser model) {

        String sql = "UPDATE users SET username = ?, password = ?, first_name = ? , " +
                " last_name = ?, email = ?, role_id = ? WHERE users_id = ?";
            try {
                PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);

                pstmt.setString(1, model.getUsername());
                pstmt.setString(2, model.getPassword());
                pstmt.setString(3, model.getFirstName());
                pstmt.setString(4, model.getLastName());
                pstmt.setString(5, model.getEmail());
                pstmt.setInt(6, model.getRoleId());
                pstmt.setInt(7, model.getUserId());

                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    @Override
    public void delete(int users_id) {
        String sql = "DELETE FROM USERS WHERE USERS_ID = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, users_id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(AbstractUser model) {
        String sql = "DELETE FROM USERS WHERE USERS_ID = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, model.getUserId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AbstractUser> getAll() {

        List<AbstractUser> list = new LinkedList<>();

        try {
            String SQL = "SELECT * FROM USERS";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);

            //A table of data representing a database result set, which is usually generated
            // by executing a statement that queries the database.
            ResultSet rs = pstmt.executeQuery();


            while(rs.next()) {
                AbstractUser model = new AbstractUser();
                model.setUserId(rs.getInt("users_id"));
                model.setUsername(rs.getString("username"));
                model.setPassword(rs.getString("password"));
                model.setFirstName(rs.getString("first_name"));
                model.setLastName(rs.getString("last_name"));
                model.setEmail(rs.getString("email"));
                model.setRoleId(rs.getInt("role_id"));
                list.add(model);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    //update users set role_id = 2 where users_id = 1;
public void changeRolebyAdminToUser(AbstractUser model){

    String sql = "update users set role_id = ? where users_id = ?";
    try {
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
        pstmt.setInt(1, 1);
        pstmt.setInt(2, model.getUserId());

        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    }

    public void changeRolebyAdminToAdmin(AbstractUser model){

        String sql = "update users set role_id = ? where users_id = ?";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            pstmt.setInt(1, 2);
            pstmt.setInt(2, model.getUserId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
