/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Pablo Herrero
 */
public class Opcion {
    public static void recomendarAlbum (){
        Database db = new Database();
        Connection conexion = db.crearConexion();
        Statement s;
        
        try {
            s = conexion.createStatement();
            int n = (int) (Math.random() * 500);
            ResultSet listado = s.executeQuery("SELECT * FROM ÁLBUMES");
            while (listado.next()) {
                if (listado.getInt("POSICIÓN") == n) {
                    String format = ("\t%-30s | %-30s | %-4s \n");
                    System.out.format(format, "TITLE", "ARTIST", "POSITION");
                    System.out.format(format, listado.getString("TÍTULO"), listado.getString("NOMBREARTISTA"), listado.getString("POSICIÓN"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Opcion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void mostrar500() {
        Database db = new Database();
        Connection conexion = db.crearConexion();
        Statement s;

        try {
            s = conexion.createStatement();
            ResultSet listado = s.executeQuery("SELECT * FROM ÁLBUMES");
            String format = ("\t%-9s | %-30s | %-30s\n");
            System.out.format(format, "POSICIÓN", "TITLE", "ARTIST");
            while (listado.next()) {
                    System.out.format(format, listado.getString("POSICIÓN"), listado.getString("TÍTULO"), listado.getString("NOMBREARTISTA"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Opcion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void filtrarDecada() {
        Database db = new Database();
        Connection conexion = db.crearConexion();
        Statement s;

        try {
            System.out.println("Enter the decade you want to see (example: 1960):");
            Scanner sc = new Scanner(System.in);
            int decada = sc.nextInt();
            int decada9 = decada + 9;
            s = conexion.createStatement();
            PreparedStatement stmt = conexion.prepareStatement("SELECT AÑO, POSICIÓN, TÍTULO, NOMBREARTISTA FROM ÁLBUMES WHERE AÑO BETWEEN ? AND ? ORDER BY POSICIÓN");
            stmt.setInt(1, decada);
            stmt.setInt(2, decada9);
            ResultSet listado = stmt.executeQuery();
            
            String format = ("\t%-9s | %-9s| %-30s | %-30s\n");
            System.out.format(format, "YEAR", "POSICIÓN", "TITLE", "ARTIST");
            while (listado.next()) {
                System.out.format(format, listado.getString("AÑO"), listado.getString("POSICIÓN"), listado.getString("TÍTULO"), listado.getString("NOMBREARTISTA"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Opcion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void filtrarArtista() {
        Database db = new Database();
        Connection conexion = db.crearConexion();
        Statement s;

        try {
            System.out.println("Enter the artist you want to see (example: The Beatles):");
            Scanner sc = new Scanner(System.in);
            String artista = sc.nextLine();
            s = conexion.createStatement();
            PreparedStatement stmt = conexion.prepareStatement("SELECT AÑO, POSICIÓN, TÍTULO, NOMBREARTISTA FROM ÁLBUMES WHERE UPPER(NOMBREARTISTA) LIKE CONCAT('%', ?, '%') ORDER BY POSICIÓN");
            stmt.setString(1, artista);
            ResultSet listado = stmt.executeQuery();

            String format = ("\t%-30s | %-40s | %-9s | %-9s|\n");
            System.out.format(format, "ARTIST", "TITLE", "YEAR", "POSICIÓN");
            while (listado.next()) {
                System.out.format(format, listado.getString("NOMBREARTISTA"), listado.getString("TÍTULO"), listado.getString("AÑO"), listado.getString("POSICIÓN"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Opcion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
