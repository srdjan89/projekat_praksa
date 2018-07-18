/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.praksa.controllers;

import com.praksa.beans.Korisnik;
import com.praksa.util.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Срђан
 */
@Named
@RequestScoped
public class Login {

    private String korisnicko_ime;
    private String lozinka;
    private Korisnik prijavljeniKorisnik;

    public String getKorisnicko_ime() {
        return korisnicko_ime;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime = korisnicko_ime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Korisnik getPrijavljeniKorisnik() {
        return prijavljeniKorisnik;
    }

    public void setPrijavljeniKorisnik(Korisnik prijavljeniKorisnik) {
        this.prijavljeniKorisnik = prijavljeniKorisnik;
    }

    public String login() {

        try (
                Connection con = ConnectionManager.getConnection();
                Statement stm = con.createStatement();) {

            ResultSet rs = stm.executeQuery("select * from korisnik where korisnicko_ime ='" + korisnicko_ime + "'");
            while (rs.next()) {
                if (rs.getString("korisnicko_ime").equals(korisnicko_ime) && rs.getString("lozinka").equals(lozinka) && rs.getInt("status_id") != 1) {
                    HttpSession sesija = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    sesija.setAttribute("korisnicko_ime", korisnicko_ime);
                    prijavljeniKorisnik = new Korisnik();
                    prijavljeniKorisnik.setId(rs.getInt("id"));
                    prijavljeniKorisnik.setKorisnicko_ime(rs.getString("korisnicko_ime"));
                    prijavljeniKorisnik.setLozinka(rs.getString("lozinka"));
                    prijavljeniKorisnik.setJe_admin(rs.getInt("je_admin"));
                    prijavljeniKorisnik.setStatus_id(rs.getInt("status_id"));
                    if (rs.getInt("je_admin") == 1) {
                        return "admin?faces-redirect=true";
                    } else {
                        return "korisnik?faces-redirect=true";
                    }
                } else {
                    return "error?faces-redirect=true";
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
