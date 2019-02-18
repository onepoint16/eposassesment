/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eposassesment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


/**
 *
 * @author sam
 */
public class agents {
    
    //Fields
    private int userID;
    private String username;
    private String password;
    private boolean permission;
    private String accountType;
    private String home = System.getProperty("user.dir");
    private String dbpath = "jdbc:ucanaccess:////"+ home + "/database/sameposdb.accdb";
    
    //Constructor

    public agents(){}
    
    public agents(int userID, String username, String password, boolean permission, String accountType) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.permission = permission;
        this.accountType = accountType;
    }
    
    //Getters

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isPermission() {
        return permission;
    }

    public String getAgentType() {
        return accountType;
    }
    
    //Setters

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public void setAgentType(String accountType) {
        this.accountType = accountType;
    }
    
    // Agents Access Table methods

    public String usermameSearch () {
        
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet usernameSearchRS = stmt.executeQuery ("SELECT username FROM login WHERE userID='" + this.userID + "'");
            String usernameRS = "username";
            while (usernameSearchRS.next()){
            this.username = usernameSearchRS.getString(usernameRS);
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return this.username;
    }
    
    public int userIDSearch () {
        
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet userIDRS = stmt.executeQuery ("SELECT userID FROM login WHERE username='" + this.username + "'");
            String ID = "userID";
            while (userIDRS.next()){
            this.userID = Integer.parseInt(userIDRS.getString(ID));
            }            
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        
        return this.userID;
    }
    
    public String passwordSearch () {
        
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet passwordRS = stmt.executeQuery ("SELECT password FROM login WHERE username='" + this.username + "'");
            String pass = "password";
            while (passwordRS.next()){
            this.password = passwordRS.getString(pass);
            }            
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        
        return this.password;        
    }
    
    public boolean permissionSearch(){

        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet permissionRS = stmt.executeQuery ("SELECT permission FROM login WHERE username='" + this.username + "'");
            String auth = "permission";
            while (permissionRS.next()){
            this.permission = Boolean.parseBoolean(permissionRS.getString(auth));
            }            
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        return this.permission;
    }
    
    public String accountTypeSearch(){
        
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet accountTypeRS = stmt.executeQuery ("SELECT accountType FROM login WHERE username='" + this.username + "'");
            String ac = "accountType";
            while (accountTypeRS.next()){
            this.accountType = accountTypeRS.getString(ac);
            }            
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        return this.accountType;
    }
    
    public void updateAgent(){
      
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            String agentUpdateRS = "UPDATE login SET username='" + this.username + "', password='" + this.password + "', permission='" + this.permission + "', accountType='" + this.accountType +"' WHERE userID='" + this.userID + "'";
            stmt.executeUpdate(agentUpdateRS);
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void addAgent() {
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement(); 
            String agentAddRS = "INSERT INTO login(username, password, permission, accountType) VALUES ('" + this.username + "','" + this.password +  "','" + this.permission + "','" + this.accountType + "')";
            stmt.executeUpdate(agentAddRS);
        }
        catch (SQLException e) {
            System.out.println(e);
        }        
    }
    
    public void deleteAgent() {
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement(); 
            String agentDeleteRS = "DELETE FROM login WHERE (username='" + this.username + "' AND userID='"+ this.userID +"')";
            stmt.executeUpdate(agentDeleteRS);
        }
        catch (SQLException e) {
            System.out.println(e);
        }        
    }  
    
    public ResultSet RefreshUserTable()
    {
        try 
        {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery ("SELECT * FROM login");
            return rs;
        }
        catch (SQLException e) 
        {
        System.out.println(e);
        return null;
        }
    }
    
    public ResultSet comboUserLoad(){
        try
        {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery ("SELECT username FROM login");
            ResultSetMetaData rsmd = rs.getMetaData();
            int userCount = rsmd.getColumnCount();
            return rs; 
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public int countUsers(){
        int userCount = 0;
        try{
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery ("SELECT * from login");
            ResultSetMetaData rsmd = rs.getMetaData();
            userCount = rsmd.getColumnCount();
            return userCount;   
            
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return 0;
    }
}
