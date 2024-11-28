package com.rolco.userBusinesses;

import com.rolco.models.User;
import com.rolco.models.UserMessages;
import com.rolco.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DaoUtils {

    DBConnection dbConnection = new DBConnection();
    Random random = new Random();

    public int saveUser(User user){
        int execution = 0;

        String query = "insert into user (id, name, email, phone, role, username, password) values(?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, user.getEmpID());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getRole());
            stmt.setString(6, user.getUsername());
            stmt.setString(7, user.getPassword());

            execution = stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            //send another random ID in case it already exist
            int empID = Integer.parseInt(String.format("%04d", random.nextInt(10000)));
            user.setEmpID(empID);
            saveUser(user);
            //execution is still 0, so we update it manually once saveUser() is done
            execution = 1;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return execution;
    }

    public User getUserInfo(String username) {
        //change everything labeled employee to manager once model is created
        User emp = null;

        try(Connection conn = dbConnection.getConnection()){
            String query = "select id, name, email, phone, role from user where username=?;";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();


            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String role = rs.getString("role");

                emp = new User(id, name, email, phone, role);
            }
            rs.close();
            stmt.close();

        }catch (SQLException e){
            System.err.println("Error connecting to database: " + e.getMessage());
        }catch (ClassNotFoundException e){
            System.err.println("Database connection error. Classpath not set " + e.getMessage());
        }
        return emp;
    }

    public User Login(String username, String password){
        //change everything labeled employee to manager once model is created
        User emp = null;

        try(Connection conn = dbConnection.getConnection()){
            String query = "select name, role, username from user where username=? and password=?;";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();


            while(rs.next()){
                String name = rs.getString("name");
                String role = rs.getString("role");
                String uName = rs.getString("username");

                emp = new User(name, role, uName);
            }
            rs.close();
            stmt.close();

        }catch (SQLException e){
            System.err.println("Error connecting to database: " + e.getMessage());
        }catch (ClassNotFoundException e){
            System.err.println("Database connection error. Classpath not set " + e.getMessage());
        }
        return emp;
    }


    public int saveMessage(String message, int empId, String status){
        {
            //User emp = checkEmployeeTable(username);
            String query = "insert into employee_message (employeeId, message, status) values(?, ?, ?)";

            int saved = 0;

            try(Connection conn = dbConnection.getConnection()){
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, empId);
                stmt.setString(2, message);
                stmt.setString(3, status);

                saved = stmt.executeUpdate();

            }catch (SQLException e){
                System.err.println("Error connecting to database: " + e.getMessage());
            }catch (ClassNotFoundException e){
                System.err.println("Database connection error. Classpath not set " + e.getMessage());
            }
            return saved;
        }
    }


    public Boolean updateUserInfo(String name, String email, String phone, String username){
        boolean userUpdated = false;

        String query = "update user set name=?, email=?, phone=? where username=?;";

        try (Connection conn = dbConnection.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, username);
            userUpdated = stmt.execute();

            stmt.close();

        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Database connection error. Classpath not set " + e.getMessage());
        }
        return userUpdated;
    }


    public ArrayList<UserMessages> joinUserAndMessageTables(){
        ArrayList<UserMessages> allUsers = new ArrayList<>();
        UserMessages userMessages = null;

        try(Connection conn = dbConnection.getConnection()){
            String query = "select user.name, employee_message.id, employee_message.message, employee_message.status from user inner join employee_message on employee_message.employeeId=user.id;";
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();


            while(rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                String message = rs.getString("message");
                String status = rs.getString("status");

                userMessages = new UserMessages(id, name, message, status);
                allUsers.add(userMessages);

            }
            rs.close();
            stmt.close();

        }catch (SQLException e){
            System.err.println("Error connecting to database: " + e.getMessage());
        }catch (ClassNotFoundException e){
            System.err.println("Database connection error. Classpath not set " + e.getMessage());
        }
        return allUsers;
    }

    public boolean updateMessageStatusById(String messageId, String action) {
        boolean userUpdated = false;

        String query = "update employee_message set status=? where id=?;";

        try (Connection conn = dbConnection.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, action);
            stmt.setString(2, messageId);
            userUpdated = stmt.execute();

            stmt.close();

        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Database connection error. Classpath not set " + e.getMessage());
        }
        return userUpdated;
    }

    public ArrayList<UserMessages> getMessageById(int empId) {
        UserMessages userMessages = null;
        ArrayList<UserMessages> myMessageList = new ArrayList<>();

        try(Connection conn = dbConnection.getConnection()){
            String query = "select id, message, status from employee_message where employeeId=?;";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();


            while(rs.next()){
                int id = rs.getInt("id");
                String message = rs.getString("message");
                String status = rs.getString("status");

                userMessages = new UserMessages(String.valueOf(id), message, status);
                myMessageList.add(userMessages);
            }
            rs.close();
            stmt.close();

        }catch (SQLException e){
            System.err.println("Error connecting to database: " + e.getMessage());
        }catch (ClassNotFoundException e){
            System.err.println("Database connection error. Classpath not set " + e.getMessage());
        }
        return myMessageList;
    }
}
