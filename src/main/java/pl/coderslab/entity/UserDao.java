package pl.coderslab.entity;

import pl.coderslab.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String QUERY_CREATE = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String QUERY_LIST = "SELECT * FROM users";
    private static final String QUERY_LIST_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String QUERY_DELETE_BY_ID = "DELETE FROM users WHERE id=?";
    private static final String QUERY_UPDATE_BY_ID = "UPDATE users SET userName = ?, email = ?, password = ? WHERE id = ?";
    public static User create(User user){
        try(Connection conn = DbUtils.dbConnect()){
            PreparedStatement ps = conn.prepareStatement(QUERY_CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, DbUtils.hashPassword(user.getPassword()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                user.setId(rs.getLong(1));
                System.out.println(user.getId());
            }
            System.out.println("print");
            return user;
        }catch(SQLException e){
            e.getStackTrace();
            return null;
        }
    }
    public static List<User> findAll(){
        List<User> list = new ArrayList<>();
        try(Connection conn = DbUtils.dbConnect()){
            PreparedStatement ps = conn.prepareStatement(QUERY_LIST);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
//                System.out.println("Done");
                list.add(new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            for(User user : list){
                System.out.println(user.toString());
            }
            return list;
        }catch(SQLException e){
            e.getStackTrace();
            return null;
        }
    }
    public static User read(Long id){
        try(Connection conn = DbUtils.dbConnect()){
            PreparedStatement ps = conn.prepareStatement(QUERY_LIST_BY_ID);
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                User user = new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
                System.out.println(user.toString());
                return user;
            }
            return null;
        }catch(SQLException e){
            e.getStackTrace();
            System.out.println(e.getMessage());
            return null;
        }

    }
    public static boolean delete(int id){
        try(Connection conn = DbUtils.dbConnect()){
            PreparedStatement ps = conn.prepareStatement(QUERY_DELETE_BY_ID);
            ps.setString(1, String.valueOf(id));
            ps.executeUpdate();
            System.out.println("Deleted user of id: " + id);
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static void update(User user){
        try(Connection conn = DbUtils.dbConnect()){
            PreparedStatement ps = conn.prepareStatement(QUERY_UPDATE_BY_ID);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, String.valueOf(user.getId()));
            ps.executeUpdate();

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }


}
