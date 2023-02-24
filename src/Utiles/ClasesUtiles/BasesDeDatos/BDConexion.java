/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.BasesDeDatos;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rene
 */
class BDConexion {

    private Connection conexion; // maneja la conexión
    private Statement instruccion; // instrucción de consulta
    private ResultSet conjuntoResultados;

    private String controlador, url_basesDeDatos, usuario, contraseña, UltimaConsulta;
    private BDTipoDeConexion tipoDeConxion;
    private File url;

    public BDConexion(String controlador, String url_basesDeDatos, String usuario, String contraseña, BDTipoDeConexion tipoDeConxion, File url) {
        this.controlador = controlador;
        this.url_basesDeDatos = url_basesDeDatos;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.tipoDeConxion = tipoDeConxion;
        this.url = url;
UltimaConsulta="";
    }

    protected ResultSet executeQuery(String query) throws SQLException, ClassNotFoundException, Exception {
//   conjuntoResultados = instruccion.executeQuery(
//                query);
System.out.println("query="+query);
UltimaConsulta=query;
        return (conjuntoResultados = instruccion.executeQuery(
                query));
    }

    protected void resetear() throws SQLException, ClassNotFoundException {
        close();
        openConexion();
    }

    protected void openConexion() throws ClassNotFoundException, SQLException {
        Class.forName(tipoDeConxion.getDriver());
//        System.out.println("1 url_basesDeDatos="+url_basesDeDatos);
        conexion = DriverManager.getConnection(url_basesDeDatos, usuario, contraseña);

// crea objeto Statement para consultar la base de datos
//        if (tipoDeConxion == tipoDeConxion.SQL_LITE) {
//            instruccion = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
//        } else {
//            instruccion = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//        }
        switch (tipoDeConxion) {
            case SQL_LITE:
                instruccion = conexion.createStatement();
                break;
            case POSTGRES:
                instruccion = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                break;
        }

    }

    public void close() throws SQLException {
        if (conjuntoResultados != null) {
            conjuntoResultados.close();
        }
        if (instruccion != null) {
            instruccion.close();
        }
        if (conexion != null) {
            conexion.close();
        }
//        instruccion.close();
//        conexion.close();
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Statement getInstruccion() {
        return instruccion;
    }

    public void setInstruccion(Statement instruccion) {
        this.instruccion = instruccion;
    }

    public ResultSet getConjuntoResultados() {
        return conjuntoResultados;
    }

    public void setConjuntoResultados(ResultSet conjuntoResultados) {
        this.conjuntoResultados = conjuntoResultados;
    }

    public String getUltimaConsulta() {
        return UltimaConsulta;
    }

}
