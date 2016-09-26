/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Sajee
 */
public class TicTacToe1 extends javax.swing.JFrame {

Connection con=null;
ResultSet rs=null;
PreparedStatement pst=null;
String currentTime;
Statement stmt = null;
   
    String Player1,Player2;
    
    public TicTacToe1() {
    
        initComponents();
        con= Connect.ConnectDB();
     
          
                
    }

   public static int num=0;
   public static boolean playerTurn=true;
   public static boolean computerWon=false;
   public static boolean playerWon=false;
   public static boolean matchResult=false;
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        b11 = new javax.swing.JButton();
        b12 = new javax.swing.JButton();
        b13 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(490, 330));
        setResizable(false);
        getContentPane().setLayout(null);

        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        getContentPane().add(b1);
        b1.setBounds(60, 30, 66, 66);

        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        getContentPane().add(b2);
        b2.setBounds(130, 30, 66, 66);

        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });
        getContentPane().add(b3);
        b3.setBounds(200, 30, 66, 66);

        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });
        getContentPane().add(b4);
        b4.setBounds(60, 100, 66, 66);

        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        getContentPane().add(b5);
        b5.setBounds(130, 100, 66, 66);

        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });
        getContentPane().add(b6);
        b6.setBounds(200, 100, 66, 66);

        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });
        getContentPane().add(b7);
        b7.setBounds(60, 170, 66, 66);

        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b8ActionPerformed(evt);
            }
        });
        getContentPane().add(b8);
        b8.setBounds(130, 170, 66, 66);

        b9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b9ActionPerformed(evt);
            }
        });
        getContentPane().add(b9);
        b9.setBounds(200, 170, 66, 66);

        b11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        b11.setText("Main Menu");
        b11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b11ActionPerformed(evt);
            }
        });
        getContentPane().add(b11);
        b11.setBounds(330, 170, 130, 40);

        b12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        b12.setText("Score");
        b12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b12ActionPerformed(evt);
            }
        });
        getContentPane().add(b12);
        b12.setBounds(330, 110, 130, 40);

        b13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        b13.setText("Reset");
        b13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b13ActionPerformed(evt);
            }
        });
        getContentPane().add(b13);
        b13.setBounds(330, 60, 130, 40);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/wall.jpg"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(-140, 0, 690, 350);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private int getPlayer1(){
    int a = 0;
        try {
        
        
        
        String sq1;
        sq1 = "Select Player1 from multiplayer where 1";
        pst=con.prepareStatement(sq1);
        rs= pst.executeQuery();
        while (rs.next()) {
            a = rs.getInt("Player1");
            
        }
        a +=1;
        
        
       
    } catch (SQLException ex) {
        Logger.getLogger(TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
    }
    return a;
    }
    
    private int getPlayer2(){
        int a = 0;
        try{
        
        String sq1;
        sq1 = "Select Player2 from multiplayer where 1";
        pst=con.prepareStatement(sq1);
        rs= pst.executeQuery();
        while (rs.next()) {
            a = rs.getInt("Player2");
            
        }
        a +=1;
                    
          }catch(HeadlessException | SQLException ex){
            
            JOptionPane.showMessageDialog(this,ex);
        }
       return a; 
    }
    
    public void checkForWin(){
       if(b1.getText().equals("X")){
           if(b4.getText().equals("X")){
               if(b7.getText().equals("X")){
                   
                   try {
                       playerWon=true;
                       computerWon=false;
                       JOptionPane.showMessageDialog( null , "Player1 Won!" ,"Tic Tac Toe",
                               JOptionPane.INFORMATION_MESSAGE );
                       String sq1= "update multiplayer set Player1 ="+ getPlayer1()+" where 1";
                       
                       stmt = con.createStatement();
     
                        stmt.executeUpdate(sq1);
                      
                       matchResult=true;
              
                       
                       update();
                   } catch (SQLException ex) {
                       Logger.getLogger(TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   
               }
           }
       }
        if(b1.getText().equals("X")){
           if(b5.getText().equals("X")){
               if(b9.getText().equals("X")){
                   try {
                       playerWon=true;
                       computerWon=false;
                       JOptionPane.showMessageDialog( null , "Player1 Won!" ,"Tic Tac Toe",
                               JOptionPane.INFORMATION_MESSAGE );
                       String sq1= "update multiplayer set Player1 ="+ getPlayer1()+" where 1";
                       
                       stmt = con.createStatement();
     
                        stmt.executeUpdate(sq1);
                        matchResult=true;
                       update();
                   } catch (SQLException ex) {
                       Logger.getLogger(TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
       }
       if(b1.getText().equals("X")){
           if(b2.getText().equals("X")){
               if(b3.getText().equals("X")){
                  
                   try {
                       playerWon=true;
                       computerWon=false;
                       JOptionPane.showMessageDialog( null , "Player1 Won!" ,"Tic Tac Toe",
                               JOptionPane.INFORMATION_MESSAGE );
                       String sq1= "update multiplayer set Player1 ="+ getPlayer1()+" where 1";
                       
                       stmt = con.createStatement();
     
                        stmt.executeUpdate(sq1);
                       
                       
                       matchResult=true;
                       update();
                   } catch (SQLException ex) {
                       Logger.getLogger(TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   
               }
           }
       }
        if(b3.getText().equals("X")){
           if(b5.getText().equals("X")){
               if(b7.getText().equals("X")){
                   try {
                       playerWon=true;
                       computerWon=false;
                       JOptionPane.showMessageDialog( null , "Player1 Won!" ,"Tic Tac Toe",
                               JOptionPane.INFORMATION_MESSAGE );
                       String sq1= "update multiplayer set Player1 ="+ getPlayer1()+" where 1";
                       
                       stmt = con.createStatement();
     
                        stmt.executeUpdate(sq1);
                       
                       
                       matchResult=true;
                       update();
                   } catch (SQLException ex) {
                       Logger.getLogger(TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
       }
         if(b3.getText().equals("X")){
           if(b6.getText().equals("X")){
               if(b9.getText().equals("X")){
                   try {
                       JOptionPane.showMessageDialog( null , "Player1 Won!" ,"Tic Tac Toe",
                               JOptionPane.INFORMATION_MESSAGE );
                       String sq1= "update multiplayer set Player1 ="+ getPlayer1()+" where 1";
                       
                       stmt = con.createStatement();
     
                        stmt.executeUpdate(sq1);
                       
                       
                       matchResult=true;
                       update();
                   } catch (SQLException ex) {
                       Logger.getLogger(TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
       }
       if(b7.getText().equals("X")){
           if(b8.getText().equals("X")){
               if(b9.getText().equals("X")){
                   try {
                       playerWon=true;
                       computerWon=false;
                       JOptionPane.showMessageDialog( null , "Player1 Won!" ,"Tic Tac Toe",
                               JOptionPane.INFORMATION_MESSAGE );
                       String sq1= "update multiplayer set Player1 ="+ getPlayer1()+" where 1";
                       
                       stmt = con.createStatement();
     
                        stmt.executeUpdate(sq1);
                       
                       
                       matchResult=true;
                       update();
                   } catch (SQLException ex) {
                       Logger.getLogger(TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
       }
       if(b4.getText().equals("X")){
           if(b5.getText().equals("X")){
               if(b6.getText().equals("X")){
                   try {
                       playerWon=true;
                       computerWon=false;
                       JOptionPane.showMessageDialog( null , "Player1 Won!" ,"Tic Tac Toe",
                               JOptionPane.INFORMATION_MESSAGE );
                       String sq1= "update multiplayer set Player1 ="+ getPlayer1()+" where 1";
                       
                       stmt = con.createStatement();
     
                        stmt.executeUpdate(sq1);
                       
                       
                       matchResult=true;
                       update();
                   } catch (SQLException ex) {
                       Logger.getLogger(TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
       }
        if(b2.getText().equals("X")){
           if(b5.getText().equals("X")){
               if(b8.getText().equals("X")){
                   try {
                       playerWon=true;
                       computerWon=false;
                       JOptionPane.showMessageDialog( null , "Player1 Won!" ,"Tic Tac Toe",
                               JOptionPane.INFORMATION_MESSAGE );
                       String sq1= "update multiplayer set Player1 ="+ getPlayer1()+" where 1";
                       
                       stmt = con.createStatement();
     
                        stmt.executeUpdate(sq1);
                       
                       
                       matchResult=true;
                       update();
                   } catch (SQLException ex) {
                       Logger.getLogger(TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
       }
        if(b1.getText().equals("O")){
           if(b4.getText().equals("O")){
               if(b7.getText().equals("O")){
                   try {
                       playerWon=false;
                       computerWon=true;
                       JOptionPane.showMessageDialog( null , "Player2 Won!" ,"Tic Tac Toe",
                               JOptionPane.INFORMATION_MESSAGE );
                       String sq1= "update multiplayer set Player2 ="+ getPlayer2()+" where 1";
                       
                       stmt = con.createStatement();
                       
                       stmt.executeUpdate(sq1);
                       
                       matchResult=true;
                       update();
                   } catch (SQLException ex) {
                       Logger.getLogger(TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
        }
        if(b1.getText().equals("O")){
           if(b5.getText().equals("O")){
               if(b9.getText().equals("O")){
                  try {
                       playerWon=false;
                       computerWon=true;
                       JOptionPane.showMessageDialog( null , "Player2 Won!" ,"Tic Tac Toe",
                               JOptionPane.INFORMATION_MESSAGE );
                       String sq1= "update multiplayer set Player2 ="+ getPlayer2()+" where 1";
                       
                       stmt = con.createStatement();
                       
                       stmt.executeUpdate(sq1);
                       
                       matchResult=true;
                       update();
                   } catch (SQLException ex) {
                       Logger.getLogger(TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
       }
       if(b1.getText().equals("O")){
           if(b2.getText().equals("O")){
               if(b3.getText().equals("O")){
                   try {
                       playerWon=false;
                       computerWon=true;
                       JOptionPane.showMessageDialog( null , "Player2 Won!" ,"Tic Tac Toe",
                               JOptionPane.INFORMATION_MESSAGE );
                       String sq1= "update multiplayer set Player2 ="+ getPlayer2()+" where 1";
                       
                       stmt = con.createStatement();
                       
                       stmt.executeUpdate(sq1);
                       
                       matchResult=true;
                       update();
                   } catch (SQLException ex) {
                       Logger.getLogger(TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
       }
       if(b3.getText().equals("O")){
           if(b5.getText().equals("O")){
               if(b7.getText().equals("O")){
                   try {
                       playerWon=false;
                       computerWon=true;
                       JOptionPane.showMessageDialog( null , "Player2 Won!" ,"Tic Tac Toe",
                               JOptionPane.INFORMATION_MESSAGE );
                       String sq1= "update multiplayer set Player2 ="+ getPlayer2()+" where 1";
                       
                       stmt = con.createStatement();
                       
                       stmt.executeUpdate(sq1);
                       
                       matchResult=true;
                       update();
                   } catch (SQLException ex) {
                       Logger.getLogger(TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
       }
       if(b3.getText().equals("O")){
           if(b6.getText().equals("O")){
               if(b9.getText().equals("O")){
                   try {
                       playerWon=false;
                       computerWon=true;
                       JOptionPane.showMessageDialog( null , "Player2 Won!" ,"Tic Tac Toe",
                               JOptionPane.INFORMATION_MESSAGE );
                       String sq1= "update multiplayer set Player2 ="+ getPlayer2()+" where 1";
                       
                       stmt = con.createStatement();
                       
                       stmt.executeUpdate(sq1);
                       
                       matchResult=true;
                       update();
                   } catch (SQLException ex) {
                       Logger.getLogger(TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
       }
       if(b7.getText().equals("O")){
           if(b8.getText().equals("O")){
               if(b9.getText().equals("O")){
                   try {
                       playerWon=false;
                       computerWon=true;
                       JOptionPane.showMessageDialog( null , "Player2 Won!" ,"Tic Tac Toe",
                               JOptionPane.INFORMATION_MESSAGE );
                       String sq1= "update multiplayer set Player2 ="+ getPlayer2()+" where 1";
                       
                       stmt = con.createStatement();
                       
                       stmt.executeUpdate(sq1);
                       
                       matchResult=true;
                       update();
                   } catch (SQLException ex) {
                       Logger.getLogger(TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
       }
        if(b4.getText().equals("O")){
           if(b5.getText().equals("O")){
               if(b6.getText().equals("O")){
                  try {
                       playerWon=false;
                       computerWon=true;
                       JOptionPane.showMessageDialog( null , "Player2 Won!" ,"Tic Tac Toe",
                               JOptionPane.INFORMATION_MESSAGE );
                       String sq1= "update multiplayer set Player2 ="+ getPlayer2()+" where 1";
                       
                       stmt = con.createStatement();
                       
                       stmt.executeUpdate(sq1);
                       
                       matchResult=true;
                       update();
                   } catch (SQLException ex) {
                       Logger.getLogger(TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
       }
        if(b2.getText().equals("O")){
           if(b5.getText().equals("O")){
               if(b8.getText().equals("O")){
                  try {
                       playerWon=false;
                       computerWon=true;
                       JOptionPane.showMessageDialog( null , "Player2 Won!" ,"Tic Tac Toe",
                               JOptionPane.INFORMATION_MESSAGE );
                       String sq1= "update multiplayer set Player2 ="+ getPlayer2()+" where 1";
                       
                       stmt = con.createStatement();
                       
                       stmt.executeUpdate(sq1);
                       
                       matchResult=true;
                       update();
                   } catch (SQLException ex) {
                       Logger.getLogger(TicTacToe1.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
       }    
     if(num==8){
         JOptionPane.showMessageDialog( null , "It's a tie" ,"Tic Tac Toe:Tie",
                                               JOptionPane.INFORMATION_MESSAGE );
         update();
    	 //display.setText("Match Draw");
     }
     
   }  
    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        if(b1.getText().equals("")){
	    if(playerTurn==true){
	        if(matchResult==true){
		    b1.setText(""); 
		    playerTurn=false;
		}
		else{
			 b1.setText("X");
			checkForWin();
			 num++;
			 playerTurn=false;}
			            
		}
	    else{
			if(matchResult==true){
			    b1.setText(""); 
			    playerTurn=false;
			 }
			 else{ 
			    b1.setText("O"); 
			    checkForWin();
			    playerTurn=true;
			     num++;
			     }
		    }
	 }
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        if(b2.getText().equals("")){
	     if(playerTurn==true){
	 	if(matchResult==true){
			b2.setText(""); 
		        playerTurn=false;
		 }
	    else{
	     b2.setText("X");
	     checkForWin();
	      num++;
	      playerTurn=false;}
		               
	 }
	else{
	        if(matchResult==true){
		    b2.setText(""); 
		     playerTurn=false;
		}
		else{
		    b2.setText("O"); 
		    checkForWin();
		    playerTurn=true;
		    num++;}
                 }
             }
    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
       	if(b3.getText().equals("")){
	     if(playerTurn==true){
		if(matchResult==true){
		    b3.setText(""); 
		    playerTurn=false;
		 }
		else{
		     b3.setText("X");
		     checkForWin();
		       playerTurn=false;
		       num++;}
	        }
	 else{
		if(matchResult==true){
		    	b3.setText(""); 
		       playerTurn=false;
		  }
		else{
		     b3.setText("O"); 
		    checkForWin();
		     playerTurn=true;
		     num++;
		            	}
		            }
		        }
       
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        if(b4.getText().equals("")){
	   if(playerTurn==true){
		if(matchResult==true){
			b4.setText(""); 
		    playerTurn=false;
		  }
		else{
		     b4.setText("X");
		      checkForWin();
		     playerTurn=false;
		      num++;}
		 }
	 else{
		 if(matchResult==true){
		b4.setText(""); 
		   playerTurn=false;
		                }
		            	else{	
		             b4.setText("O"); 
		               checkForWin();
		               playerTurn=true;
		               num++;}
		            }
		        }
    }//GEN-LAST:event_b4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        if(b5.getText().equals("")){
		            if(playerTurn==true){
		            	if(matchResult==true){
		                	b5.setText(""); 
		                	playerTurn=false;
		                }
		            	else{
		                b5.setText("X");
		                checkForWin();
		                playerTurn=false;
		                num++;}
		            }
		            else{
		            	if(matchResult==true){
		                	b5.setText(""); 
		                	playerTurn=false;
		                }
		            	else{
		               b5.setText("O"); 
		               checkForWin();
		               playerTurn=true;
		               num++;}
		            }
		        }
    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
         if(b6.getText().equals("")){
			            if(playerTurn==true){
			            	if(matchResult==true){
			                	b6.setText(""); 
			                	playerTurn=false;
			                }
			            	else{
			                b6.setText("X");
			                checkForWin();
			                playerTurn=false;
			                num++;}
			            }
			            else{
			            	if(matchResult==true){
			                	b6.setText(""); 
			                	playerTurn=false;
			                }
			            	else{
			              b6.setText("O"); 
			               checkForWin();
			               playerTurn=true;
			               num++;}
			            }
			        }
    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        if(b7.getText().equals("")){
			            if(playerTurn==true){
			            	if(matchResult==true){
			                	b7.setText(""); 
			                	playerTurn=false;
			                }
			            	else{
			               b7.setText("X");
			                checkForWin();
			                playerTurn=false;
			                num++;}
			            }
			            else{
			            	if(matchResult==true){
			                	b7.setText(""); 
			                	playerTurn=false;
			                }
			            	else{
			               b7.setText("O"); 
			               checkForWin();
			               playerTurn=true;
			               num++;}
			            }
			        }
    }//GEN-LAST:event_b7ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        if(b8.getText().equals("")){
		            if(playerTurn==true){
		            	if(matchResult==true){
		                	b8.setText(""); 
		                	playerTurn=false;
		                }
		            	else{
		                b8.setText("X");
		                checkForWin();
		                playerTurn=false;
		                num++;}
		            }
		            else{
		            	if(matchResult==true){
		                	b8.setText(""); 
		                	playerTurn=false;
		                }
		            	else{
		               b8.setText("O"); 
		               checkForWin();
		               playerTurn=true;
		               num++;}
		            }
		        }
    }//GEN-LAST:event_b8ActionPerformed

    private void b9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b9ActionPerformed
       if(b9.getText().equals("")){
		            if(playerTurn==true){
		            	if(matchResult==true){
		                	b9.setText(""); 
		                	playerTurn=false;
		                }
		            	else{
		                b9.setText("X");
		                checkForWin();
		                playerTurn=false;
		                num++;}
		            }
		            else{
		            	if(matchResult==true){
		                	b9.setText(""); 
		                	playerTurn=false;
		                }
		            	else{
		               b9.setText("O"); 
		               checkForWin();
		               playerTurn=true;
		               num++;}
		            }
		        }
    }//GEN-LAST:event_b9ActionPerformed
    public void update(){
         b1.setText("");
	 b2.setText("");
	 b3.setText("");
	 b4.setText("");
	 b5.setText("");
	 b6.setText("");
	 b7.setText("");
	 b8.setText("");
	 b9.setText("");
		         
	 playerTurn=true;
	 playerWon=false;
	 computerWon=false; 
         matchResult=false;
         num=-1;
    }
    private void b11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b11ActionPerformed
       //setDefaultCloseOperation(TicTacToe1.DISPOSE_ON_CLOSE);
        this.dispose();
        new TicTacToeGame().setVisible(true);
    }//GEN-LAST:event_b11ActionPerformed

    private void b12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b12ActionPerformed
       Score s=new Score();
       s.setVisible(true);
       s.getMultiplayer();
    }//GEN-LAST:event_b12ActionPerformed

    private void b13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b13ActionPerformed
        update();

    }//GEN-LAST:event_b13ActionPerformed

    /**
     * @param args the command line arguments
     */
  private void setIcon() {
       
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/icon.png")));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JButton b11;
    private javax.swing.JButton b12;
    private javax.swing.JButton b13;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
