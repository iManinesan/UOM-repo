/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vurobilling;

import java.sql.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author hp
 */
public class SalesHandler {
    
 public Connection conn = null;
   private ResultSet customer_rs = null ;
   ResultSet customer_rs1 = null ;
   String str[]= {"w","q"};
    
    /// connet to database --------------------------------------------------------------------
    
    public Connection connectToDB(){
        
         try{
            // create a mysql database connection
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/romafour";
            Class.forName(myDriver);
            conn = DriverManager.getConnection(myUrl, "root", "");
             
            }
        
         catch (ClassNotFoundException | SQLException e)
            {
                JOptionPane.showMessageDialog(null, e);
                JOptionPane.showInternalMessageDialog(null, e.getMessage());
                System.err.println(e.getMessage());
            }        
        return conn;
        
    }
    
    
    /// close database ----------------------------------------------------------------------------------------
    
    public void closeDB(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SalesHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // set data to customer table-------------------------------------------------------------------------------------------
    public void setCustomers(String str[]){
        try {
            this.connectToDB();
            String query = "INSERT INTO `customers`(`Cust_Id`, `Cust_Name`, `Cust_Address`, `Cust_Number`, `Cust_email`, `Total_Amount_Purchased`, `Discount_Points`)"+" VALUES (null,?,?,?,?,null,null)";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1,str[0]);
            preparedStmt.setString(2, str[1]);
            preparedStmt.setString(3, str[2]);
            preparedStmt.setString(4, str[3]);
            
            preparedStmt.executeUpdate();
            preparedStmt.close();
            ////////////////////////////////////////////////
            
           this.getData_Cust_name(str[0]);
            
            ///////////////////////////////////////////////
            
            this.closeDB();
            
        } catch (SQLException ex) {
            Logger.getLogger(SalesHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // get data from customer table ---------------------------------------------------------------------------------------------------
      
    public boolean getData_Customers(String id){
        str[0]="";
                str[1]= "";
     this.connectToDB();
        try {
            
            String query = "SELECT `Cust_Id`, `Cust_Name`, `Cust_Address`, `Cust_Number`, `Cust_email`, `Total_Amount_Purchased`, `Discount_Points` FROM `customers` WHERE Cust_Id = ?";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1,id);
            
            // execute the preparedstatement
            ResultSet rs = preparedStmt.executeQuery();
            customer_rs = rs;
            customer_rs1 = rs;
             
          //  rs.first();
            
            
            
            if(rs.next()){
                str[0]= customer_rs.getString(1);
                str[1]= customer_rs.getString(2);
                
               return true;
            }
            else return false;
            //return rs;
        } catch (SQLException ex) {
            Logger.getLogger(SalesHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeDB();
     return false;
    }
    
    
    
    
    //--------------------------------------------------------------------------------------------------------------------------------
    
    public void getNewBill(SalesHandler sh){
        // you have to write the algoritham for check the database for customer id then create a new bill window.
        
        CreateBillWindow bw = new CreateBillWindow(sh,str);
        bw.setVisible(true);
    }
    
    
    
    
    
    public void getCustomerSignUpWindow(SalesHandler sh){
        // you have to write the algoritham for add the details in this jfreame in database
        this.connectToDB();
        CustomerSignUpWindow csw =new CustomerSignUpWindow(sh); 
        csw.setVisible(true);
        this.closeDB();
    }
    
     public void getAnonymusBillWindow(SalesHandler sh){
        // you have to write the algoritham for add the details in this jfreame in database
         BillWindow_anonymous bw_a = new BillWindow_anonymous(sh);
        bw_a.setVisible(true);
    }
     
     
     //////////////////////////////////////////
  public void getData_Cust_name(String name){
     str[0]="";
      str[1]= "";
      this.connectToDB();
     
        try {
            
           // String query = "SELECT `Cust_Id`, `Cust_Name`, `Cust_Address`, `Cust_Number`, `Cust_email`, `Total_Amount_Purchased`, `Discount_Points` FROM `customers` WHERE Cust_Name = ?";
            String query = "SELECT * FROM `customers` WHERE Cust_Id = (SELECT MAX(Cust_Id) FROM `customers`)";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            //preparedStmt.setString(1,name);
            
            // execute the preparedstatement
            ResultSet rs = preparedStmt.executeQuery();
            customer_rs = rs;
           //System.out.print(customer_rs.getString(1));
          
            if(rs.next()){
            str[0]= rs.getString("Cust_Id");
            str[1]= rs.getString(2);
          //  rs.first();
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SalesHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeDB();
   
    }
    
    
    //--------------------------------------------------------------------------------------------------------------------------------
    public float get_Customers(String id){
        float f = 0;
     this.connectToDB();
        try {
            
            String query = "SELECT `Cust_Id`, `Cust_Name`, `Cust_Address`, `Cust_Number`, `Cust_email`, `Total_Amount_Purchased`, `Discount_Points` FROM `customers` WHERE Cust_Id = ?";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1,id);
            
            // execute the preparedstatement
            ResultSet rs = preparedStmt.executeQuery();
            customer_rs = rs;
            customer_rs1 = rs;
             
          //  rs.first();
            
            
            
            if(rs.next()){
                f= rs.getFloat(6);
               return f;
            }
            else return f;
            //return rs;
        } catch (SQLException ex) {
            Logger.getLogger(SalesHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeDB();
     return f;
    }
    
    
    
}
