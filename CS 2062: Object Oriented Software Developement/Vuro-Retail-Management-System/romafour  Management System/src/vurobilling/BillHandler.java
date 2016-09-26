/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vurobilling;

import com.mysql.jdbc.PreparedStatement;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class BillHandler extends SalesHandler{
    
     int c_q = 0;
    public float total= 0;
    public ResultSet resultSet;
    float subTotal = 0;
    float price = 0;
    
    
    
    public String[] SelectItem(int productId, int quantity){
         
        super.connectToDB();
        try{
            
            String query = "SELECT `Product_Id`, `Product_Name`, `Price`,`Reorder_Level` FROM `products` WHERE Product_Id = ?";
            java.sql.PreparedStatement ps = super.conn.prepareStatement(query);
            ps.setString(1, Integer.toString(productId));
            ResultSet rs = ps.executeQuery();
            
           // if(Integer.parseInt(resultSet.getString(5))>0){
            
              
                
                  
                
                   // if((resultSet.getInt(5)-quantity)>=0){
                    //    reduceQuantity(true,quantity);
                   // }
                  //  else
                   //     reduceQuantity(false,0);
                 
                String[] str = new String[5];
                    
                if(rs.next()){        
                
        
        
                try {
                    price = Float.parseFloat(rs.getString(3));
                    
                    subTotal = price*quantity;
                    total +=subTotal;
                    str[0] = rs.getString(1);
             
                    str[1] = rs.getString(2);
                    str[2] = rs.getString(3);
                    str[3] = String.valueOf(quantity);
                    str[4] = String.valueOf(subTotal);
                   // System.out.println(rs.getString(4));
                   //if((Integer.parseInt(rs.getString(4))-quantity)>0){
                     
                } catch (SQLException ex) {
                   Logger.getLogger(CreateBillWindow.class.getName()).log(Level.SEVERE, null, ex);
         
                }
                return str;
            }
                
           // }
            
            
        }catch(Exception e){
            
        }
        return null;
    }

   

    private void reduceQuantity(boolean b, int quantity,int productId,int amount) {
        //super.connectToDB();
        try{
            
            String query = "UPDATE `products` SET `Reorder_Level`=70 WHERE Product_Id = 1";
            java.sql.PreparedStatement ps = super.conn.prepareStatement(query);
            if(b){
             //   int x = (amount - quantity);
           // ps.setString(1,String.valueOf(amount) );
             
           // ps.setString(2,String.valueOf(productId) );
             //System.out.println(String.valueOf(productId));
            ps.executeQuery();
           
            }
            else{
                ps.setString(1,"0" );
            ps.setString(2,String.valueOf(productId) );
            ps.executeQuery();
            }
           
        }catch(Exception e){
            
        }
       
        
    
    }
    
 
    public void insertDataTo_Sales(String cust_id){
        try {
            this.connectToDB();
            String query = "INSERT INTO `sales`(`Bill_No`, `Order_Date`, `Cust_Id`, `Cashier_Id`, `Total_Amount`, `Discount_Used`, `Final_Amount`)"+" VALUES (null,?,?,1,0,null,0)";
            
            // create the mysql insert preparedstatement
            java.sql.PreparedStatement preparedStmt = conn.prepareStatement(query);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
           String da =(String) dateFormat.format(date);
            
            preparedStmt.setString(1,da);
            preparedStmt.setString(2,cust_id);
            preparedStmt.executeUpdate();
            preparedStmt.close();
            ////////////////////////////////////////////////
            
         //  this.getData_Cust_name(str[0]);
            
            ///////////////////////////////////////////////
            
            this.closeDB();
            
        } catch (SQLException ex) {
            Logger.getLogger(SalesHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
  
       ///////////////////////////////////////////////////////////////////////////////////
    
    
     public int getLastBillNo(){
     int billNo =0;
    
      this.connectToDB();
     
        try {
            
           // String query = "SELECT `Cust_Id`, `Cust_Name`, `Cust_Address`, `Cust_Number`, `Cust_email`, `Total_Amount_Purchased`, `Discount_Points` FROM `customers` WHERE Cust_Name = ?";
            String query = "SELECT * FROM `sales` WHERE Bill_No = (SELECT MAX(Bill_No) FROM `sales`)";
            // create the mysql insert preparedstatement
            java.sql.PreparedStatement preparedStmt = conn.prepareStatement(query);
            //preparedStmt.setString(1,name);
            
            // execute the preparedstatement
            ResultSet rs = preparedStmt.executeQuery();
            
           //System.out.print(customer_rs.getString(1));
          
            if(rs.next()){
            billNo= rs.getInt(1);
           
          //  rs.first();
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SalesHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeDB();
        return billNo;
    }

     /////////////////////////////////////////////////////////////////////////////////////
     
     
     
     public void insertIntoSales_Items(String str[]){
        try {
            this.connectToDB();
            String query = "INSERT INTO `sales_items`(`Bill_No`, `Product_Id`, `Price`, `Order_Quantity`, `Total_Amount`)"+" VALUES (?,?,?,?,?)";
            
            // create the mysql insert preparedstatement
            java.sql.PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, str[0]);
            preparedStmt.setString(2, str[1]);
            preparedStmt.setString(3, str[2]);
            preparedStmt.setString(4, str[3]);
            preparedStmt.setString(5, str[4]);
            
            preparedStmt.executeUpdate();
            preparedStmt.close();
            ////////////////////////////////////////////////
            
            
            ///////////////////////////////////////////////
            
            this.closeDB();
            
        } catch (SQLException ex) {
            Logger.getLogger(SalesHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
     //////////////////////////////////////////////////////////////////////
     
     public void update_Sales(int billNo, String total){
        try {
            this.connectToDB();
            String query = "UPDATE `sales` SET `Total_Amount`=?,`Final_Amount`=? WHERE `Bill_No` = ?";
            
            // create the mysql insert preparedstatement
            java.sql.PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            
            preparedStmt.setString(1,total);
            preparedStmt.setString(2,total);
            preparedStmt.setString(3,String.valueOf(billNo));
            preparedStmt.executeUpdate();
            preparedStmt.close();
            ////////////////////////////////////////////////
            
         //  this.getData_Cust_name(str[0]);
            
            ///////////////////////////////////////////////
            
            this.closeDB();
            
        } catch (SQLException ex) {
            Logger.getLogger(SalesHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
     public void update_Customers(String cust_Id,String total){
         float cust_total = 0;
         float real_total = 0;
         
        try {
            cust_total = super.get_Customers(cust_Id);
            real_total = cust_total + Float.parseFloat(total);
             
            String query = "UPDATE `customers` SET `Total_Amount_Purchased`=? WHERE `Cust_Id` = ?";
            
            // create the mysql insert preparedstatement
            java.sql.PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            
            preparedStmt.setString(1,String.valueOf(real_total));
            preparedStmt.setString(2,cust_Id);
            preparedStmt.executeUpdate();
            preparedStmt.close();
            ////////////////////////////////////////////////
            
         //  this.getData_Cust_name(str[0]);
            
            ///////////////////////////////////////////////
            
            this.closeDB();
            
        } catch (SQLException ex) {
            Logger.getLogger(SalesHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     //////////////////////////////////////////////////////////////////////////////////////////////
     
     public int get_Quantity(String product_id){
        int f = 0;
     this.connectToDB();
        try {
            
            String query = "SELECT `Total_Quantity` FROM `products` WHERE Product_Id = ?";
            
            // create the mysql insert preparedstatement
            java.sql.PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1,product_id);
            
            // execute the preparedstatement
            ResultSet rs = preparedStmt.executeQuery();
         
             
            
            
            if(rs.next()){
                f= rs.getInt(1);
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


//////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
     
     public void update_Products(String product_id, String quantity){
        int quantity_total = 0;
        int bill_quantity = 0;
        int current_quantity = 0;
        try {
            quantity_total = get_Quantity(product_id);
            bill_quantity  = Integer.parseInt(quantity);
            current_quantity = quantity_total - bill_quantity;
            c_q = current_quantity;
            if(current_quantity  > 0){
                String query = "UPDATE `products` SET `Total_Quantity`=? WHERE Product_Id = ?";
            
            // create the mysql insert preparedstatement
            java.sql.PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            
            preparedStmt.setString(1,String.valueOf(current_quantity));
            preparedStmt.setString(2,product_id);
            preparedStmt.executeUpdate();
            preparedStmt.close();
            }
            else{
                String query =  "UPDATE `products` SET `Total_Quantity`= 0 WHERE Product_Id = ?";
            
            // create the mysql insert preparedstatement
            java.sql.PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            
            
            preparedStmt.setString(1,product_id);
            preparedStmt.executeUpdate();
            preparedStmt.close();
                
            }
             
            
            ////////////////////////////////////////////////
            
         //  this.getData_Cust_name(str[0]);
            
            ///////////////////////////////////////////////
            
            this.closeDB();
            
        } catch (SQLException ex) {
            Logger.getLogger(SalesHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

     
     ///////////////////////////////////////////////////////////////////////////////////////////////////////
     
      public void insertDataTo_Inventory(String p_Id , String quantity){
        try {
            this.connectToDB();
            String query = "INSERT INTO `inventory`(`Product_Id`, `Order_Type_Code`, `Order_Date`, `Quantity`, `Inventory_Level`)" + "VALUES (?,?,?,?,?)";
            
            // create the mysql insert preparedstatement
            java.sql.PreparedStatement preparedStmt = conn.prepareStatement(query);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
           String da =(String) dateFormat.format(date);
            
            preparedStmt.setString(1,p_Id);
            preparedStmt.setString(2,"sales");
            preparedStmt.setString(3,da);
            preparedStmt.setString(4,quantity);
            if(c_q > 0){
            preparedStmt.setString(5,String.valueOf(c_q));
            }
            else{
                preparedStmt.setString(5,"0");
            }
            preparedStmt.executeUpdate();
            preparedStmt.close();
            ////////////////////////////////////////////////
            
         //  this.getData_Cust_name(str[0]);
            
            ///////////////////////////////////////////////
            
            this.closeDB();
            
        } catch (SQLException ex) {
            Logger.getLogger(SalesHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
  
}
