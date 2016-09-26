/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Sajee
 */
public class TicTacToe2 extends javax.swing.JFrame {

Connection con=null;
ResultSet rs=null;
PreparedStatement pst=null;
Statement st=null;
   
  private final static int ROWS = 3;
    private final static int COLUMNS = 3;
   private final JButton button[] =new JButton[9];
    private char sign[][];
    private JPanel buttonsPanel;
   
    
    
    
    
    public TicTacToe2() {
        
     con= Connect.ConnectDB();
        initComponents();
        sign = new char[ ROWS ][ COLUMNS ];
         button[0]=b1;
         button[1]=b2;
         button[2]=b3;
         button[3]=b4;
         button[4]=b5;
         button[5]=b6;
         button[6]=b7;
         button[7]=b8;
         button[8]=b9;
        this.buttonsPanel = jPanel1;
          
            
             for ( int i = 0 ; i < ROWS * COLUMNS ; i++ ) {
               try{
               button[ i ].setFocusPainted( false);
               button[ i ].setActionCommand( Integer.toString( i ) );
               button[ i ].setFont( new Font( "Tahoma" , Font.BOLD , 15 ) );
               button[ i ].setPreferredSize( new Dimension(70, 70 ) );
               button[ i ].setToolTipText( "Click to make your move" );
               button[ i ].addActionListener(new TicTacToe2.ButtonClickHandler() );
               buttonsPanel.add( button[ i ] );
               }
               catch(ArrayIndexOutOfBoundsException x){}
        }
      resetGame();         
    }

     private int getPlayer(){
    int a = 0;
        try {
        
        
        
        String sq1;
        sq1 = "Select Player from single_player where 1";
        pst=con.prepareStatement(sq1);
        rs= pst.executeQuery();
        while (rs.next()) {
            a = rs.getInt("Player");
            
        }
        a +=1;
        
        
       
    } catch (SQLException ex) {
        Logger.getLogger(TicTacToe2.class.getName()).log(Level.SEVERE, null, ex);
    }
    return a;
    }
    
    private int getComputer(){
        int a = 0;
        try{
        
        String sq1;
        sq1 = "Select Computer from single_player where 1";
        pst=con.prepareStatement(sq1);
        rs= pst.executeQuery();
        while (rs.next()) {
            a = rs.getInt("Computer");
            
        }
        a +=1;
                    
          }catch(HeadlessException | SQLException ex){
            
            JOptionPane.showMessageDialog(this,ex);
        }
       return a; 
    }
    
    
   private class ButtonClickHandler implements ActionListener
    {
        private int i,j;
        
        public void actionPerformed( ActionEvent event )
        {

            JButton button = (JButton) event.getSource();
            int row = Integer.parseInt( button.getActionCommand() ) / ROWS;
            int col = Integer.parseInt( button.getActionCommand() ) % COLUMNS;

            // is the square that user has clicked is occupied?
            if ( isOccupied( row , col ) == true  )
            {
                JOptionPane.showMessageDialog( null , "This cell is already occupied." ,
                                               "Tic Tac Toe : Error", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                // the square is not occupied so put
                // an X on it.
                button.setText( "X" );

                // mark that square as occupied
                sign[ row ][ col ] = 'X';

                // check whether user has won the game
                if ( isWinner( 'X' ) == true )
                {
                    try {
                        JOptionPane.showMessageDialog( null , "You won the game." ,"Tic Tac Toe:Winner",
                                JOptionPane.INFORMATION_MESSAGE );
                        String sq1="update  single_player set Player ="+getPlayer()+" where 1";
                        st=con.createStatement();
                        st.executeUpdate(sq1);
                        resetGame();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(TicTacToe2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return;
                }
                else
                {
                    // user has not won the game so it's computer
                    // turn to make a move.
                    computerMove();

                    // has compuer won the game?
                    if ( isWinner( 'O' ) == true )
                    {
                        try {
                            JOptionPane.showMessageDialog( null , "Computer won the game." ,"Tic Tac Toe:Winner",
                                    JOptionPane.INFORMATION_MESSAGE );
                            String sq1="update  single_player set Computer ="+getComputer()+" where 1";
                            st=con.createStatement();
                            st.executeUpdate(sq1);
                            resetGame();
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(TicTacToe2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        return;
                    }                      
                }
                
            }

            /*
             * check whether the game is a tie or not.
             * if it's a tie then reset the game
             */
            if ( isTie() == true )
            {
                JOptionPane.showMessageDialog( null , "It's a tie" ,"Tic Tac Toe:Tie",
                                               JOptionPane.INFORMATION_MESSAGE );
                resetGame();
            }
        }

        private boolean isTie()
        {
            boolean tie = true;

            /* loop through all array elements and check
             * whether there is an empty element or not.
             * if there is at least one empty element then
             * the game is not a tie.
             */
            for ( int r = 0 ; r < ROWS ; r++)
            {
                for ( int c = 0 ; c < COLUMNS ; c++)
                {
                    if ( sign[r][c] != 'O' && sign[r][c]!= 'X' )
                    {
                        tie = false;
                    }
                }
            }
            
            return tie;
        }
        
        private void computerMove()
        {
            // is there a possibility for user to win the game.
            if ( canWin('O','X', ROWS-1 ) == true )
            {
                // there is a possibility for user to win.
                // make a suitable move to prevent user
                // from winning the game.
                button[ this.i * ROWS + this.j ].setText("O");
                sign[this.i][this.j] = 'O';

            }
            else if ( canWin('X','O', ROWS-1 ) == true )
                {
                    // there is a possibility for computer to win.
                    // so make a suitable move to win the game.
                    button[this.i * ROWS + this.j ].setText("O");
                    sign[this.i][this.j] = 'O';
                }
            else
            {
                // neither user nor computer can win the game.
                // make a sutable move.
                for ( int r = 0 ; r < (ROWS*COLUMNS) ; r++ )
                        if ( !isOccupied( r / ROWS, r % COLUMNS ) == true )
                        {
                            button[ r ].setText("O");
                            sign[r/ROWS][r%COLUMNS]='O';
                            break;
                        }

            }

        } // end method computerMove
        
        private boolean canWin( char playerSign , char opponentSign , int count )
        {
            byte r,c;
            byte c1,c2,c3,c4;
            byte i1,i2,i3,i4;
            byte j1,j2,j3,j4;

            i1 = i2 = i3 = i4 = -1;
            j1 = j2 = j3 = j4 = -1;
            c1 = c2 = c3 = c4 = 0;
            
            for ( r = 0 ; r < ROWS ; r++ )
            {
                c1 = c2 = 0;
                i1 = i2 = i3 = i4 = -1;
                j1 = j2 = j3 = j4 = -1;
                for ( c = 0 ; c < COLUMNS ; c++ )
                {
                    // check horizontal possibility
                    if ( sign[r][c] == playerSign )
                        c1++;
                    else
                    {
                        if ( sign[r][c] != opponentSign )
                        {
                            i1 = r;
                            j1 = c;
                        }
                    }

                    // check vertical possibility
                    if ( sign[c][r] == playerSign )
                        c2++;
                    else
                    {
                        if ( sign[c][r] != opponentSign )
                        {
                            i2 = c;
                            j2 = r;
                        }
                    }

                    // check diagonal possibilities
                    if ( r == c )
                    {
                        if ( sign[r][c] == playerSign )
                            c3++;
                        else
                        {
                            if ( sign[r][c] != opponentSign )
                            {
                                i3 = r;
                                j3 = c;
                            }
                        }
                    }
                    
                    if ( (r+c) == ROWS - 1 )
                    {
                        if ( sign[r][c] == playerSign )
                            c4++;
                        else
                        {
                            if ( sign[r][c] != opponentSign )
                            {
                                i4 = r;
                                j4 = c;
                            }
                        }
                    }

                    // check whether there is a possibility to win or not
                    if ( c1 == count && i1 != -1 && j1 != -1 )
                    {
                        this.i = i1;
                        this.j = j1;
                        return true;
                    }
                    else if ( c2 == count && i2 != -1 && j2 != -1 )
                    {
                        this.i = i2;
                        this.j = j2;
                        return true;
                    }
                    else if ( c3 == count && i3 != -1 && j3 != -1 )
                    {
                        this.i = i3;
                        this.j = j3;
                        return true;
                    }
                    else if ( c4 == count && i4 != -1 && j4 != -1 )
                    {
                        this.i = i4;
                        this.j = j4;
                        return true;
                    }
                }
            }

            // there is no possibility to win
            return false;

        } // end method canWin
        
        private boolean isOccupied( int row , int col )
        {
            if ( sign[row][col] == 'X' || sign[row][col] == 'O' )
                return true;
            else
                return false;
        }

        private boolean isWinner( char playerSign )
        {
            byte r,c;
            byte c1,c2,c3,c4;

            c1 = c2 = c3 =  c4 = 0;

            for ( r = 0 ; r < ROWS ; r++ )
            {
                c1 = c2 = 0;                
                for ( c = 0 ; c < COLUMNS ; c++ )
                {

                    // check horizontally
                    if ( sign[r][c] == playerSign )
                        c1++;

                    // check vertically
                    if ( sign[c][r] == playerSign )
                        c2++;

                    // check diagonally
                    if ( (r + c) == ROWS - 1 )
                    {
                       if ( sign[r][c] == playerSign )
                           c3++;
                    }

                    if ( r == c )
                    {
                       if ( sign[r][r] == playerSign )
                           c4++;
                    }
                }

                // check whether there is a winner or not
                if ( c1 == ROWS || c2 == COLUMNS || c3 == ROWS || c4 == ROWS )
                    return true;
            }

            // there is no winner so return false
            return false;

        } // end method isWinner

    } // end inner class ButtonClickHandler
   
   private void resetGame()
    {
        sign = new char[ROWS][COLUMNS];
        
        
            b1.setText("");
            b2.setText("");
            b3.setText(""); 
            b4.setText("");
            b5.setText("");
            b6.setText("");
            b7.setText("");
            b8.setText("");  
            b9.setText("");

        int answer = JOptionPane.showConfirmDialog( null , "Do you want to start with computer?",
                                                    "Tic Tac Toe:New Game",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if ( answer == JOptionPane.YES_OPTION )
        {
            Random rnd = new Random();
            int rndNo;
            rndNo =  rnd.nextInt(ROWS*COLUMNS);
            button[rndNo].setText( "O" );
            sign[ rndNo / ROWS ][ rndNo % COLUMNS ] = 'O';
        }
        
    } // end method resetGame
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        b11 = new javax.swing.JButton();
        b12 = new javax.swing.JButton();
        b13 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        b9 = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(490, 330));
        setResizable(false);
        getContentPane().setLayout(null);

        b11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        b11.setText("Reset");
        b11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b11ActionPerformed(evt);
            }
        });
        getContentPane().add(b11);
        b11.setBounds(330, 60, 130, 40);

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
        b13.setText("Main Menu");
        b13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b13ActionPerformed(evt);
            }
        });
        getContentPane().add(b13);
        b13.setBounds(330, 160, 130, 40);

        jPanel1.setBackground(new java.awt.Color(102, 204, 0));
        jPanel1.setLayout(null);

        b9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b9ActionPerformed(evt);
            }
        });
        jPanel1.add(b9);
        b9.setBounds(140, 140, 66, 66);

        b1.setBorder(null);
        b1.setBorderPainted(false);
        b1.setDefaultCapable(false);
        b1.setFocusPainted(false);
        b1.setFocusable(false);
        b1.setOpaque(false);
        b1.setRequestFocusEnabled(false);
        b1.setRolloverEnabled(false);
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        jPanel1.add(b1);
        b1.setBounds(0, 0, 66, 66);

        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });
        jPanel1.add(b4);
        b4.setBounds(0, 70, 66, 66);

        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        jPanel1.add(b5);
        b5.setBounds(70, 70, 66, 66);

        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });
        jPanel1.add(b7);
        b7.setBounds(0, 140, 66, 66);

        b2.setOpaque(false);
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        jPanel1.add(b2);
        b2.setBounds(70, 0, 66, 66);

        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });
        jPanel1.add(b3);
        b3.setBounds(140, 0, 66, 66);

        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });
        jPanel1.add(b6);
        b6.setBounds(140, 70, 66, 66);

        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b8ActionPerformed(evt);
            }
        });
        jPanel1.add(b8);
        b8.setBounds(70, 140, 66, 66);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(70, 10, 210, 210);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/wall.jpg"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(-130, 0, 630, 330);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        
    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
       	
       
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        
    }//GEN-LAST:event_b4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
      
    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
      
    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
       
    }//GEN-LAST:event_b7ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
     
    }//GEN-LAST:event_b8ActionPerformed

    private void b9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b9ActionPerformed
       
    }//GEN-LAST:event_b9ActionPerformed

    private void b11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b11ActionPerformed
      resetGame();
       
    }//GEN-LAST:event_b11ActionPerformed

    private void b12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b12ActionPerformed
       Score s=new Score();
       s.setVisible(true);
       s.getSingleplayer();
    }//GEN-LAST:event_b12ActionPerformed

    private void b13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b13ActionPerformed
       this.dispose();
        new TicTacToeGame().setVisible(true);
    }//GEN-LAST:event_b13ActionPerformed

    /**
     * @param args the command line arguments
     */


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
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
