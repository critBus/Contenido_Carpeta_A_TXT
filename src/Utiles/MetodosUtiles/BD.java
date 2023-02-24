/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.MetodosUtiles;

import Utiles.ClasesUtiles.BasesDeDatos.BDTipoDeConexion;
import static Utiles.MetodosUtiles.MetodosUtiles.or;
import java.io.File;

/**
 * Metodos que fasilitan las acciones basicas con Bases de Datos Version 0.2
 *
 * @author Rene
 */
public abstract class BD {
    //private static final String JDBC="jdbc:";
public static String getFormaString(Object o) {
        Class c = o.getClass();
        if (or(c, java.util.Date.class)) {
            return new java.sql.Date(((java.util.Date) o).getTime()) + "";
        }

        return o.toString();
    }
 public static String getConexionPOSTGRES(String servidor,String puerto, String nombreBD) {
        return BDTipoDeConexion.POSTGRES.getUrl() + "//" + servidor+":"+puerto + "/" + nombreBD;
    }
    public static String getConexionSQL_LITE(File direccion) {
        return BDTipoDeConexion.SQL_LITE.getUrl() + direccion;
    }

    public static String getConexionMY_SQL(String servidor, String nombreBD) {
        return BDTipoDeConexion.MY_SQL.getUrl() + "/" + servidor + "/" + nombreBD;
    }
    
    public static boolean esSQL_LITE(String direccion){
        
    return MetodosUtiles.or(Archivo.getExtencion(direccion), true, BDTipoDeConexion.SQL_LITE.getExtencionDeArchivo());
    }
   
    
//    public static final String URL_SQL_LITE = "jdbc:sqlite:";
//    public static final String URL_SQL_LITE = "jdbc:sqlite:";
}
