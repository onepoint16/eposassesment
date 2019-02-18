/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eposassesment;

import java.util.ArrayList;
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
public class products {
    
    //Fields
    private int stockID;
    private String productName;
    private double productCost;
    private int quantity;
    public double subTotal;
    public double VAT;
    public double total;
    ArrayList<Double> shoppingList = new ArrayList<>();
    private String home = System.getProperty("user.dir");
    private String dbpath = "jdbc:ucanaccess:////"+ home + "/database/sameposdb.accdb";
    
    
    //Constructor
    
    public products(){}
    
    public products (int stockID, String productName, double productCost, int quantity){
        
        this.stockID = stockID;
        this.productName = productName;
        this.productCost = productCost;
        this.quantity = quantity;
    }

    
    //Getters
    public int getStockID() {
        return stockID;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductCost() {
        return productCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public ArrayList<Double> getshoppingList() {
        return shoppingList;
    }

    //Setters
    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setshoppingList(ArrayList<Double> shoppingList) {
        this.shoppingList = shoppingList;
    }
    
    //Methods
    
    public void addToShoppingList(Double itemCost){
        Double i = itemCost;
        shoppingList.add(i);
    }
    
    public double subTotal(){
        
        
        int countDown = shoppingList.size();

        this.subTotal = 0.00;
       
        
        for (int i = 0; i < countDown; i++){
            double itemCost = shoppingList.get(i);

            this.subTotal = this.subTotal + itemCost;

        }
        return this.subTotal;
    }
    
    public double VAT(){
        VAT = this.subTotal * 0.2;
        return VAT;
    }
    
    public double total(){
        this.total = this.subTotal + this.VAT;
        return this.total;
    }
    
    public void clearShoppingList(){
        shoppingList.removeAll(shoppingList);
    }

    //Sales Access Tables methods
    
    public double productPriceSearch () {
        
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet productPriceRS = stmt.executeQuery ("SELECT productCost FROM stock WHERE productName='" + this.productName + "'");
            String price = "productCost";
            while (productPriceRS.next()){
                
                System.out.println(productPriceRS.getString(price));
            this.productCost = Double.parseDouble(productPriceRS.getString(price));
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return this.productCost;
    }
    
    public int stockIDSearch () {
        
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet stockIDRS = stmt.executeQuery ("SELECT stockID FROM stock WHERE productName='" + this.productName + "'");
            String ID = "stockID";
            while (stockIDRS.next()){
            this.stockID = Integer.parseInt(stockIDRS.getString(ID));
            }            
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        
        return this.stockID;
    }
    
    public String productNameSearch () {
        
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet productNameRS = stmt.executeQuery ("SELECT productName FROM stock WHERE stockID='" + this.stockID + "'");
            String itemName = "productName";
            while (productNameRS.next()){
            this.productName = productNameRS.getString(itemName);
            }            
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        
        return this.productName;        
    }
    
    public void updateProduct(){
      
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            String productUpdateRS = "UPDATE stock SET productName='" + this.productName + "', productCost='" + this.productCost +"' WHERE stockID='" + this.stockID + "'";
            stmt.executeUpdate(productUpdateRS);
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void addProduct() {
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement(); 
            String productAddRS = "INSERT INTO stock(productName, productCost) VALUES ('" + this.productName + "','" + this.productCost + "')";
            stmt.executeUpdate(productAddRS);
        }
        catch (SQLException e) {
            System.out.println(e);
        }        
    }
    
    public void deleteProduct() {
        try {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement(); 
            String productDeleteRS = "DELETE FROM stock WHERE (productName='" + this.productName + "' AND productCost='"+ this.productCost +"')";
            stmt.executeUpdate(productDeleteRS);
        }
        catch (SQLException e) {
            System.out.println(e);
        }        
    }

    public ResultSet RefreshProductsTable()
    {
        try 
        {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery ("SELECT * FROM stock");
            return rs;
        }
        catch (SQLException e) 
        {
        System.out.println(e);
        }
        return null;
    } 
    
    public ResultSet comboProductLoad(){
        try
        {
            Connection con = DriverManager.getConnection(dbpath);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery ("SELECT productName FROM stock");
            ResultSetMetaData rsmd = rs.getMetaData();
            int userCount = rsmd.getColumnCount();
            return rs; 
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
}
