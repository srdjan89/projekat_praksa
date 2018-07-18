/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.srdjan.praksa;

import com.praksa.util.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Срђан
 */
public class Test {

    public static void main(String[] args) {

        try (Connection con = ConnectionManager.getConnection();
                Statement stm = con.createStatement();) {

            ResultSet rs = stm.executeQuery("SELECT * FROM mesto;");

            while (rs.next()) {

                String stampaj = rs.getString("naziv");
                System.out.println(stampaj);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
