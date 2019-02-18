
package eposassesment;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author sam
 */
public class sales {
    
    //Fields
    private int salesID;
    private int userID;
    public double salesTotal;
    public int totalSales;
    public double highestSale;
    public double lowestSale;
    public double averageSale;
    ArrayList<Double> userTotalSales = new ArrayList<>();
    private String home = System.getProperty("user.dir");
    private String dbpath = "jdbc:ucanaccess:////"+ home + "/database/sameposdb.accdb";
    
    //Constructor
    
    public sales(){}
    
    public sales(int salesID, int userID, double salesTotal){
        
        this.salesID = salesID;
        this.userID = userID;
        this.salesTotal = salesTotal;
        
    }
    
    //Getters

    public int getSalesID() {
        return salesID;
    }

    public int getUserID() {
        return userID;
    }

    public double getSalesTotal() {
        return salesTotal;
    }

    public ArrayList<Double> userTotalSales() {
        return userTotalSales;
    }
    
    //Setters

    public void setSalesID(int salesID) {
        this.salesID = salesID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setSalesTotal(double salesTotal) {
        this.salesTotal = salesTotal;
    }
    
    //Methods
    
    public void setuserTotalSales() {
        int rows = 0;
        try {
        ResultSet rs = totalNumberSales();
        while(rs.next()){
                this.userTotalSales.add(rs.getDouble("salesTotal"));
                rows++;
        }
        }
        catch (SQLException e){
            System.out.println(e);
        }

    }    
    
    public int totalSales(){
        this.totalSales = userTotalSales.size();
        return this.totalSales;
    }
    
    public double highestSale(){
        double highest = 0;
        int countDown = userTotalSales.size();
        
        for(int i = 0; i < countDown; i++){
            
            if (userTotalSales.get(i) > highest)
            {
                highest = userTotalSales.get(i);
                this.highestSale = highest;
            }
        }
        
        return this.highestSale;
    }
    
    public double lowestSale(){
        double lowest = userTotalSales.get(0);
        int countDown = userTotalSales.size();
        
        for(int i = 0; i < countDown; i++){
            
            if (userTotalSales.get(i) < lowest)
            {
                lowest = userTotalSales.get(i);
                this.lowestSale = lowest;
            }
        }
        
        return this.lowestSale;
    }
    
    public double averageSale(){
        double total = 0;
        int countDown = userTotalSales.size();
        
        for(int i = 0; i < countDown; i++){
            total = total + userTotalSales.get(i);
            this.averageSale = total / this.totalSales;
        }
        return this.averageSale;
    }

    //Sales Access Tables methods

    public ResultSet totalNumberSales(){
        
        try{
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet totalSalesRS = stmt.executeQuery ("SELECT salesTotal FROM sales WHERE userID=" + this.userID );
            return totalSalesRS;
        }
        catch (SQLException e){
            return null;
        }
    }
    
    public double salesTotalSearch () {
        
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet salesTotalRS = stmt.executeQuery ("SELECT salesTotal FROM sales WHERE userID=" + this.userID );
            String salesT = "salesTotal";
            while (salesTotalRS.next()){
            this.salesTotal = Double.parseDouble(salesTotalRS.getString(salesT));
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return this.salesTotal;
    }
    
    public int salesIDSearch () {
        
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet salesIDRS = stmt.executeQuery ("SELECT salesID FROM sales WHERE userID='" + this.userID + "'");
            String ID = "salesID";
            while (salesIDRS.next()){
            this.salesID = Integer.parseInt(salesIDRS.getString(ID));
            }            
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        
        return this.salesID;
    }
    
    public int userIDSearch () {
        
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet userIDRS = stmt.executeQuery ("SELECT userID FROM sales WHERE salesID='" + this.salesID + "'");
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
    
    public void updateSale(){
      
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            String saleUpdateRS = "UPDATE sales SET salesTotal='" + this.salesTotal + "', userID='" + this.userID +"' WHERE salesID='" + this.salesID + "'";
            stmt.executeUpdate(saleUpdateRS);        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void addSale() {
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement(); 
            String saleAddRS = "INSERT INTO sales(userID, salesTotal) VALUES ('" + this.userID + "','" + this.salesTotal + "')";
            stmt.executeUpdate(saleAddRS);
        }
        catch (SQLException e) {
            System.out.println(e);
        }        
    }
    
    public void deleteSale() {
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement(); 
            String saleDeleteRS = "DELETE FROM sales WHERE (salesID='" + this.salesID +"')";
            stmt.executeUpdate(saleDeleteRS);        }
        catch (SQLException e) {
            System.out.println(e);
        }        
    }        

    public ResultSet RefreshSalesTable()
    {
        try 
        {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery ("SELECT * FROM sales");
            return rs;
        }
        catch (SQLException e) 
        {
        System.out.println(e);
        }
        return null;
    }   
}
