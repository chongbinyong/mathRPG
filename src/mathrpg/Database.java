/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathrpg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
/**
 *
 * @author Chong Bin Yong
 */
public class Database {
    
    GamePanel gp;
    
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    
    public Database(GamePanel gp)
    {
        this.gp = gp;
    }
    
    public void updateDatabase()
    {          
        try{
            String datetime = dtf.format(now);
            String gametime = dFormat.format(gp.ui.playTime);
            String clearance = gp.player.clear;
            String difficultyLvl = String.valueOf(gp.ui.difficultyLvl);
            String correctMulDec = String.valueOf(gp.player.correctMulDec);
            String totalMulDec = String.valueOf(gp.player.totalMulDec);
            String correctDivDec = String.valueOf(gp.player.correctDivDec);
            String totalDivDec = String.valueOf(gp.player.totalDivDec);
            String correctMulFrac = String.valueOf(gp.player.correctMulFrac);
            String totalMulFrac = String.valueOf(gp.player.totalMulFrac);
            String correctDivFrac = String.valueOf(gp.player.correctDivFrac);
            String totalDivFrac = String.valueOf(gp.player.totalDivFrac);
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mathrpg?zeroDateTimeBehavior=convertToNull","root","");
            PreparedStatement ps = con.prepareStatement("insert into results(datetime, gametime, clearance, difficultyLvl, correctMulDec, totalMulDec, "
                    + "correctDivDec, totalDivDec, correctMulFrac, totalMulFrac, correctDivFrac, totalDivFrac)values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, datetime);
            ps.setString(2, gametime);
            ps.setString(3, clearance);
            ps.setString(4, difficultyLvl);
            ps.setString(5, correctMulDec);
            ps.setString(6, totalMulDec);
            ps.setString(7, correctDivDec);
            ps.setString(8, totalDivDec);
            ps.setString(9, correctMulFrac);
            ps.setString(10, totalMulFrac);
            ps.setString(11, correctDivFrac);
            ps.setString(12, totalDivFrac);
            ps.executeUpdate();
                    
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
