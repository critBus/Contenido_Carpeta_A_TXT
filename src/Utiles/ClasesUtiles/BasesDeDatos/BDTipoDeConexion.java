/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.BasesDeDatos;

/**
 *
 * @author Rene
 */
public enum BDTipoDeConexion {
     SQL_LITE("sqlite", "org.sqlite.JDBC",".db",".s3db"), MY_SQL("mysql", "com.mysql.jdbc.Driver"),POSTGRES("postgresql","org.postgresql.Driver");
        private final String url, driver,extencionDeArchivo[];

    private BDTipoDeConexion(String url, String driver, String ... extencionDeArchivo) {
        this.url = "jdbc:" + url + ":";
        this.driver = driver;
        this.extencionDeArchivo = extencionDeArchivo;
    }

    public String[] getExtencionDeArchivo() {
        return extencionDeArchivo;
    }

        

        public String getUrl() {
            return url;
        }

        public String getDriver() {
            return driver;
        }
}
