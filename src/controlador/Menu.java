/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.*;

/**
 *
 * @author Pablo Herrero
 */
public class Menu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Opcion opcion = new Opcion();
        int eleccion;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("*****************************************");
        System.out.println("*****500 GREATEST ALBUMS OF ALL TIME*****");
        System.out.println("************by Rolling Stone*************");
        System.out.println("*****************************************");
        System.out.println("");
        System.out.println("Select an option:");
        System.out.println("1. 500 Greatest Albums list.");
        System.out.println("2. Album of the day.");
        System.out.println("3. Filter by decade.");
        System.out.println("4. Filter by artist.");
        System.out.println("0. Exit");

        eleccion = sc.nextInt();

        while (eleccion != 0){
            switch(eleccion){
                case 1:
                    opcion.mostrar500();
                    break;
                case 2:
                    opcion.recomendarAlbum();
                    break;
                case 3:
                    opcion.filtrarDecada();
                    break;
                case 4:
                    opcion.filtrarArtista();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
            System.out.println("");
            System.out.println("Select an option:");
            System.out.println("1. 500 Greatest Albums list.");
            System.out.println("2. Album of the day.");
            System.out.println("3. Filter by decade.");
            System.out.println("4. Filter by artist.");
            System.out.println("0. Exit.");
            eleccion = sc.nextInt();
        }

    }
} 

