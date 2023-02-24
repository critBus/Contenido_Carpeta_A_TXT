/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.BasesDeDatos;

import Utiles.ClasesUtiles.Interfases.Funcionales.Realizar;
import Utiles.ClasesUtiles.Interfases.Funcionales.RealizarConException;
import Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2;
import Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar2Exception;
import Utiles.ClasesUtiles.Interfases.Funcionales.Utilizar3;
import Utiles.MetodosUtiles.Arreglos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import static Utiles.MetodosUtiles.BD.*;
import Utiles.MetodosUtiles.MetodosUtiles;
import java.io.File;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;
import java.util.function.Consumer;
import static Utiles.MetodosUtiles.MetodosUtiles.or;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Version 0.2
 *
 * @author Rene
 */
public class BDConexionController {

    private static final String ID = "id int primary key", IF_EXISTS = "IF EXISTS", IF_NOT_EXISTS = "IF NOT EXISTS";

    BDConexion bDConexion;

    private ResultSetMetaData metaDatos;
    private String controlador, url_basesDeDatos, usuario, contraseña, servidor, nombreBD, consultaAcual, puerto, ultimaEjecucion;
    private BDTipoDeConexion tipoDeConxion;
    private File url;

    private char cc = '\'';//para la separacion de los valores de atributos

    private BDConexionController(BDTipoDeConexion tipo, File url) {
        this("", "", "", "", "", tipo, url);

    }

    private BDConexionController(BDTipoDeConexion tipo, String usuario, String contraseña, String nombreBD, String servidor, String puerto) {
        this(usuario, contraseña, servidor, nombreBD, puerto, tipo, null);
    }

    private BDConexionController(String usuario, String contraseña, String servidor, String nombreBD, String puerto, BDTipoDeConexion tipoDeConxion, File url) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.servidor = servidor;
        this.nombreBD = nombreBD;
        this.puerto = puerto;
        this.tipoDeConxion = tipoDeConxion;
        ultimaEjecucion = "";
//        System.out.println("url="+url);

        this.url = url == null ? null : url.getAbsoluteFile();
//System.out.println("this.url="+this.url);
        controlador = tipoDeConxion.getDriver();
        switch (tipoDeConxion) {
            case SQL_LITE:
                url_basesDeDatos = getConexionSQL_LITE(url);
//                System.out.println("2 url_basesDeDatos="+url_basesDeDatos);
                break;
            case POSTGRES:
                url_basesDeDatos = getConexionPOSTGRES(servidor, puerto, nombreBD);
        }
        bDConexion = getNuevaConexion();
    }

    private BDConexion getNuevaConexion() {
        return new BDConexion(controlador, url_basesDeDatos, usuario, contraseña, tipoDeConxion, url);
    }

//private Statement getNuevaInstruccion() throws SQLException{
//return DriverManager.getConnection(url_basesDeDatos, usuario, contraseña).createStatement();
//}
    public BDConexionController executeQuery(String query) throws SQLException, ClassNotFoundException, Exception {
        bDConexion.openConexion();
// realizarAccionBD(()->{
//        System.out.println("query=" + query);
//        bDConexion.executeQuery(query);
//        conjuntoResultados = instruccion.executeQuery(
//                query);
        metaDatos = bDConexion.executeQuery(query).getMetaData();

        consultaAcual = query;
// });

        return this;
    }

    public BDConexionController executeSELECT(String nombreTabla, String... atributos) throws Exception {
        String a = "";
        for (int i = 0; i < atributos.length; i++) {
            a += (i != 0 ? " , " : "") + atributos[i];
        }
        return executeQuery("SELECT " + a + " FROM " + nombreTabla);
    }

    public BDConexionController executeSELECT_Todos(String nombreTabla) throws Exception {
        return executeSELECT(nombreTabla, "*");
    }

    public BDConexionController executeSELECTyWHERE(String nombreTabla, String where, String... atributos) throws Exception {
        String a = "";
        for (int i = 0; i < atributos.length; i++) {
            a += (i != 0 ? " , " : "") + atributos[i];
        }
        return executeQuery("SELECT " + a + " FROM " + nombreTabla + " WHERE " + where);
    }

    public BDConexionController executeSELECTyWHERE_Igual(String nombreTabla, String whereA, Object igualA, String... atributos) throws SQLException, ClassNotFoundException, Exception {
        return executeSELECTyWHERE(nombreTabla, (whereA + "='" + getFormaString(igualA) + "'"), atributos.length == 0 ? new String[]{"*"} : atributos);
    }

    /**
     * el 2r paresAigualB son pares de valores para <br>
     * ejemplo<br><strong>SELECT algo FROM autores WHERE apellidoPaterno =
     * 'Garcia' AND nombrePila = 'Alejandra'</strong>
     *
     * @param nombreTabla
     * @param paresAigualB
     * @param atributos
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public BDConexionController executeSELECTyWHERE_Igual(String nombreTabla, Object[] paresAigualB, String... atributos) throws SQLException, ClassNotFoundException, Exception {
        String where = "";
        for (int i = 0; i < paresAigualB.length; i += 2) {
            where += (i != 0 ? "AND" : "") + (" " + paresAigualB[i] + "='" + getFormaString(paresAigualB[i + 1]) + "'");
        }
        return executeSELECTyWHERE(nombreTabla, where, atributos.length == 0 ? new String[]{"*"} : atributos);
    }

    /**
     * el 3r paresAigualB son pares de valores para <br>
     * ejemplo<br><strong>DELETE FROM autores WHERE apellidoPaterno = 'Garcia'
     * AND nombrePila = 'Alejandra'</strong>
     *
     * @param nombreTabla
     * @param paresAigualB
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public BDConexionController executeDELETEyWHERE_Igual(String nombreTabla, Object... paresAigualB) throws SQLException, ClassNotFoundException, Exception {
        String where = "";
        for (int i = 0; i < paresAigualB.length; i += 2) {
            where += (i != 0 ? "AND" : "") + (" " + paresAigualB[i] + "='" + getFormaString(paresAigualB[i + 1]) + "'");
        }
        return executeDELETEyWHERE(nombreTabla, where);
    }

    public BDConexionController executeDELETE_forID(String nombreTabla, int... ids) throws SQLException, ClassNotFoundException, Exception {
        for (int id : ids) {
            executeDELETEyWHERE_Igual(nombreTabla, "id", id + "");
        }
        return this;
    }

    public BDConexionController executeDELETEyWHERE(String nombreTabla, String where) throws SQLException, ClassNotFoundException, Exception {
        return execute("DELETE  FROM " + nombreTabla + " WHERE " + where);
    }

    /**
     *   * el 3r paresColumnaValor son pares de valores para <br>
     * ejemplo<br><strong>UPDATE nombreDeTabla SET nombreDeColumna1 = valor1,
     * nombreDeColumna2 = valor2, …, nombreDeColumnaN = valorN WHERE
     * criterios</strong>
     *
     * @param nombreTabla
     * @param where
     * @param paresColumnaValor
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public BDConexionController executeUPDATEyWHERE(String nombreTabla, String where, Object... paresColumnaValor) throws SQLException, ClassNotFoundException, Exception {
        String valores = "";
        for (int i = 0; i < paresColumnaValor.length; i += 2) {
            valores += (i != 0 ? " , " : "") + (" " + paresColumnaValor[i] + "='" + getFormaString(paresColumnaValor[i + 1]) + "'");
        }

        return execute("UPDATE " + nombreTabla + " SET " + valores + " WHERE " + where);
    }

    /**
     *   * el 4r paresColumnaValor son pares de valores para <br>
     * ejemplo<br><strong>UPDATE nombreDeTabla SET nombreDeColumna1 = valor1,
     * nombreDeColumna2 = valor2, …, nombreDeColumnaN = valorN WHERE
     * criterios</strong><br>
     * 2do y 3ro: WHERE (" "+whereA + "='" +igualB + "'")
     *
     * @param nombreTabla
     * @param whereA
     * @param igualB
     * @param paresColumnaValor
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public BDConexionController executeUPDATEyWHERE_Igual(String nombreTabla, String whereA, String igualB, Object... paresColumnaValor) throws SQLException, ClassNotFoundException, Exception {
        //(" "+paresAigualB[i] + "='" + paresAigualB[i+1] + "'")

        return executeUPDATEyWHERE(nombreTabla, (" " + whereA + "='" + igualB + "'"), paresColumnaValor);
    }

    /**
     * el 3r paresColumnaValor son pares de valores para <br>
     * ejemplo<br><strong>UPDATE nombreDeTabla SET nombreDeColumna1 = valor1,
     * nombreDeColumna2 = valor2, …, nombreDeColumnaN = valorN WHERE (" id='" +#
     * + "'")</strong><br>
     *
     * @param nombreTabla
     * @param id
     * @param paresColumnaValor
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public BDConexionController executeUPDATE_forID(String nombreTabla, int id, String... paresColumnaValor) throws SQLException, ClassNotFoundException, Exception {
        //(" "+paresAigualB[i] + "='" + paresAigualB[i+1] + "'")
        return executeUPDATEyWHERE_Igual(nombreTabla, "id", (id + ""), paresColumnaValor);
        // return executeUPDATEyWHERE(nombreTabla, (" id='" + id + "'"), paresColumnaValor);
    }

    /**
     *  * el 3r paresColumnaValor son pares de valores y <br>
     * el 2r paresAigualB son pares de valores para <br>
     * ejemplo<br><strong>UPDATE nombreDeTabla SET nombreDeColumna1 = valor1,
     * nombreDeColumna2 = valor2, …, nombreDeColumnaN = valorN WHERE
     * criterios</strong><br>
     *
     * <code>where+=(i!=0?"AND":"")+(" "+paresAigualB[i] + "='" + paresAigualB[i+1] + "'");</code>
     *
     * @param nombreTabla
     * @param paresAigualB
     * @param paresColumnaValor
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public BDConexionController executeUPDATEyWHERE_Igual(String nombreTabla, String[] paresAigualB, String... paresColumnaValor) throws SQLException, ClassNotFoundException, Exception {
        String where = "";
        for (int i = 0; i < paresAigualB.length; i += 2) {
            where += (i != 0 ? "AND" : "") + (" " + paresAigualB[i] + "='" + paresAigualB[i + 1] + "'");
        }
        return executeUPDATEyWHERE(nombreTabla, where, paresColumnaValor);
    }
// public BDConexion executeUPDATEyWHERE(String nombreTabla,String  set,String where) throws SQLException, ClassNotFoundException, Exception {
//         return executeQuery("UPDATE "+nombreTabla+" SET "+set+" WHERE "+where);
//    }

    /**
     * del 2do argumento la 1ra fila son los nombres de columnas<br>
     * y el resto los valores en cada una<br>
     * ejemplo<br><strong>INSERT INTO nombreDeTabla ( nombreDeColumna1,
     * nombreDeColumna2, …, nombreDeColumnaN ) VALUES ( valor1, valor2, …,
     * valorN )</strong><br>
     *
     * @param nombreTabla
     * @param paresColumnaValor
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public BDConexionController executeINSERT(String nombreTabla, String columnasYvalores[][]) throws SQLException, ClassNotFoundException, Exception {
//    System.out.println(Arrays.toString(paresColumnaValor));
        String columnasSQL = "";//"( ";
        for (int i = 0; i < columnasYvalores[0].length; i++) {
            columnasSQL += (i != 0 ? " , " : "") + columnasYvalores[0][i];
        }
        //columnasSQL+=" )";
        for (int i = 1; i < columnasYvalores.length; i++) {
            String valoresSQL = "";//"( ";
            for (int j = 0; j < columnasYvalores[i].length; j++) {
                valoresSQL += (j != 0 ? " , " : "") + cc + getFormaString(columnasYvalores[i][j]) + cc;
            }
            executeINSERTmasID(nombreTabla, columnasSQL, valoresSQL);
//         valoresSQL+=" )";
//          execute("INSERT INTO "+nombreTabla+" "+columnasSQL+" VALUES "+valoresSQL);
        }
        return this;

//        for (int i = 0; i < paresColumnaValor.length; i+=2) {
////            System.out.println("i="+i);
//            valores+=(i!=0?" , ":"")+paresColumnaValor[i+1];
////            System.out.println("valores="+valores);
//            columnas+=(i!=0?" , ":"")+paresColumnaValor[i];
////            System.out.println("columnas="+columnas);
//        }
//         System.out.println("valores="+valores);
//            System.out.println("columnas="+columnas);
//         return execute("INSERT INTO "+nombreTabla+" "+columnasSQL+" VALUES "+valoresSQL);
    }

    /**
     * <br>
     * ejemplo<br><strong>INSERT INTO nombreDeTabla ( nombreDeColumna1,
     * nombreDeColumna2, …, nombreDeColumnaN ) VALUES ( valor1, valor2, …,
     * valorN )</strong><br>
     *
     * @param nombreTabla
     * @param paresColumnaValor
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public BDConexionController executeINSERT(String nombreTabla, String columnas[], Object valores[][]) throws SQLException, ClassNotFoundException, Exception {
//    System.out.println(Arrays.toString(paresColumnaValor));
        String columnasSQL = "";//"( ";
        for (int i = 0; i < columnas.length; i++) {
            columnasSQL += (i != 0 ? " , " : "") + columnas[i];
        }
        //  columnasSQL+=" )";
        for (int i = 0; i < valores.length; i++) {
//         String valoresSQL="( ";
            String valoresSQL = "";
            for (int j = 0; j < valores[i].length; j++) {
                valoresSQL += (j != 0 ? " , " : "") + cc + getFormaString(valores[i][j]) + cc;
            }
            //valoresSQL+=" )";
            // execute("INSERT INTO "+nombreTabla+" "+columnasSQL+" VALUES "+valoresSQL);
            executeINSERTmasID(nombreTabla, columnasSQL, valoresSQL);
        }
        return this;

//        for (int i = 0; i < paresColumnaValor.length; i+=2) {
////            System.out.println("i="+i);
//            valores+=(i!=0?" , ":"")+paresColumnaValor[i+1];
////            System.out.println("valores="+valores);
//            columnas+=(i!=0?" , ":"")+paresColumnaValor[i];
////            System.out.println("columnas="+columnas);
//        }
//         System.out.println("valores="+valores);
//            System.out.println("columnas="+columnas);
//         return execute("INSERT INTO "+nombreTabla+" "+columnasSQL+" VALUES "+valoresSQL);
    }

    private void executeINSERTsql(String nombreTabla, String columnasSQL, String valoresSQL) throws Exception {

        execute("INSERT INTO  " + nombreTabla + " (" + columnasSQL + " ) VALUES ( " + valoresSQL + " ) ");
    }

    private void executeINSERTsql(String nombreTabla, int id, String columnasSQL, String valoresSQL) throws Exception {
        executeINSERTsql(nombreTabla, " id , " + columnasSQL, (id >= 0 ? id : idCorresPondiente(nombreTabla)) + " , " + valoresSQL);
        //execute("INSERT INTO  " + nombreTabla + " ( id ," + columnasSQL + " ) VALUES ( " + (id>=0?id:idCorresPondiente(nombreTabla)) + " , " + valoresSQL+" ) ");
    }

    private void executeINSERTmasID(String nombreTabla, String columnasSQL, String valoresSQL) throws Exception {
        executeINSERTsql(nombreTabla, idCorresPondiente(nombreTabla), columnasSQL, valoresSQL);
        //execute("INSERT INTO  " + nombreTabla + " ( id ," + columnasSQL + " ) VALUES ( " + idCorresPondiente(nombreTabla) + " , " + valoresSQL+" ) ");
//aaaaaaaaa
    }

    public BDConexionController executeINSERT(String nombreTabla, String columnas[], Object valores[]) throws SQLException, ClassNotFoundException, Exception {
        return executeINSERT(nombreTabla, -1, columnas, valores);

    }

    public BDConexionController executeINSERTsinID(String nombreTabla, String columnas[], Object valores[]) throws SQLException, ClassNotFoundException, Exception {
        crearColumnasYValores(columnas, valores, (columnasSQL, valoresSQL) -> executeINSERTsql(nombreTabla, columnasSQL, valoresSQL));
        return this;
    }

    private void crearColumnasYValores(String columnas[], Object valores[], Utilizar2Exception<String, String> u) throws Exception {
        String columnasSQL = "";//"( ";
        for (int i = 0; i < columnas.length; i++) {
            columnasSQL += (i != 0 ? " , " : "") + columnas[i];
        }
        //columnasSQL+=" )";
        if (columnas.length == 1) {
            for (int i = 0; i < valores.length; i++) {
                u.utilizar(columnasSQL, cc + getFormaString(valores[i]) + cc);
                // executeINSERTsql(nombreTabla, id, columnasSQL, cc + getFormaString(valores[i]) + cc);
            }
        } else {
            String valoresSQL = "";//"( ";
            for (int j = 0; j < valores.length; j++) {
                valoresSQL += (j != 0 ? " , " : "") + cc + getFormaString(valores[j]) + cc;
            }
            u.utilizar(columnasSQL, valoresSQL);
            //executeINSERTsql(nombreTabla, id, columnasSQL, valoresSQL);
        }
    }

    public BDConexionController executeINSERT(String nombreTabla, int id, String columnas[], Object valores[]) throws SQLException, ClassNotFoundException, Exception {
//    System.out.println(Arrays.toString(paresColumnaValor));
//        String columnasSQL = "";//"( ";
//        for (int i = 0; i < columnas.length; i++) {
//            columnasSQL += (i != 0 ? " , " : "") + columnas[i];
//        }
//        if (columnas.length == 1) {
//            for (int i = 0; i < valores.length; i++) {
//
//                executeINSERTsql(nombreTabla, id, columnasSQL, cc + getFormaString(valores[i]) + cc);
//            }
//        } else {
//            String valoresSQL = "";
//            for (int j = 0; j < valores.length; j++) {
//                valoresSQL += (j != 0 ? " , " : "") + cc + getFormaString(valores[j]) + cc;
//            }
//            executeINSERTsql(nombreTabla, id, columnasSQL, valoresSQL);
//        }
        crearColumnasYValores(columnas, valores, (columnasSQL, valoresSQL) -> executeINSERTsql(nombreTabla, id, columnasSQL, valoresSQL));
        return this;

    }

    public BDConexionController executeINSERT(String nombreTabla, String columna, Object valor) throws SQLException, ClassNotFoundException, Exception {
        executeINSERTmasID(nombreTabla, columna, cc + getFormaString(valor) + cc);
        return this;
    }

    public BDConexionController execute(String ejecucion) throws ClassNotFoundException, SQLException, Exception {
//        crearConexion();
//        instruccion.execute(ejecucion);
        realizarAccionBD(() -> {
            System.out.println("ejecucion=" + ejecucion);
            ultimaEjecucion = ejecucion;
            bDConexion.getInstruccion().execute(ejecucion);
        });
        return this;
    }

    public BDConexionController insertar(String nombreTabla, Map<String, List> atributoValores) throws SQLException, ClassNotFoundException, Exception {
        realizarAccionBD(() -> {
            Iterator<String> I = atributoValores.keySet().iterator();
            String nombres[] = new String[atributoValores.keySet().size()];
            List valores[] = new List[nombres.length];
            int i = 0;
            while (I.hasNext()) {
                String n = I.next();
                nombres[i] = n;
                valores[i] = atributoValores.get(n);

            }
            int size = -1;
            if (valores.length > 0) {
                size = valores[0].size();
            }
            for (int j = 0; j < size; j++) {
                List l = valores[j];

                String atributos = "id", valoresdeAtributos = idCorresPondiente(nombreTabla) + "";
                for (int k = 0; k < nombres.length; k++) {
                    atributos += " ," + nombres[k];
                }
                for (int k = 0; k < valores.length; k++) {
//                System.out.println("valores[k].get(i)=" + valores[k].get(i));
//                System.out.println("valores[k].get(i).getClass().getSimpleName()=" + valores[k].get(i).getClass().getSimpleName());

                    valoresdeAtributos += " ," + (valores[k].get(i) instanceof String ? "" + cc + valores[k].get(i) + cc : valores[k].get(i));
                }
//            if(!atributos.isEmpty()){
//             valoresdeAtributos=valoresdeAtributos.substring(0,atributos.length()-1);
//            }
//            System.out.println("INSERT INTO " + nombreTabla + " (" + atributos + ") values ("
//                    + valoresdeAtributos
//                    + ");");
                ultimaEjecucion = "INSERT INTO " + nombreTabla + " (" + atributos + ") values ("
                        + valoresdeAtributos
                        + ");";
                bDConexion.getInstruccion().execute(ultimaEjecucion);

            }
        });

//        crearConexion();
        //String atributos="(",;
        return this;

    }

    private int idCorresPondiente(String nombreTabla) throws SQLException, ClassNotFoundException, Exception {

        bDConexion.resetear();
//Connection conexion = DriverManager.getConnection(url_basesDeDatos, usuario, contraseña);
//   Statement    instruccion = conexion.createStatement();

        bDConexion.executeQuery("SELECT MAX(" + nombreTabla + ".\"id\") FROM " + nombreTabla);
        // ResultSet r = instruccion.executeQuery("SELECT MAX(" + nombreTabla + ".\"id\") FROM " + nombreTabla);

//        System.out.println("r="+r);
        bDConexion.getConjuntoResultados().next();
        String o = bDConexion.getConjuntoResultados().getObject(1) + "";
        int id = 1;
        if (MetodosUtiles.esNumeroAll(o)) {
            id = Integer.parseInt(o) + 1;
        }
        bDConexion.resetear();

// r.close();
//instruccion.close();
//   conexion.close();
//    crearConexion();
        return id;

    }

    public BDConexionController crearTablaYBorrarSiExiste(String nombreTabla, Map<String, Class> nombreTipo) throws SQLException, ClassNotFoundException, Exception {
//        instruccion.execute("DROP TABLE IF EXISTS " + nombreTabla + ";");
//        crearTabla(nombreTabla, nombreTipo);
        realizarAccionBD(() -> {
            executeDROP_TABLE_IF_EXISTSsinAccion(nombreTabla);
            crearTabla(nombreTabla, nombreTipo);
        });
        return this;
    }

    private void executeDROP_TABLE_IF_EXISTSsinAccion(String nombreTabla) throws SQLException {
        ultimaEjecucion = "DROP TABLE IF EXISTS " + nombreTabla + ";";
        bDConexion.getInstruccion().execute(ultimaEjecucion);

    }

    public BDConexionController executeDROP_TABLE_IF_EXISTS(String nombreTabla) throws Exception {
        realizarAccionBD(() -> {
            executeDROP_TABLE_IF_EXISTSsinAccion(nombreTabla);

        });
        return this;
    }

    public BDConexionController crearTablaYBorrarSiExiste(String nombreTabla, String... columnasString) throws Exception {
//        realizarAccionBD(()->{
//        instruccion.execute("DROP TABLE IF EXISTS " + nombreTabla + ";");
//        crearTablaSiNoExiste(nombreTabla, columnasString);
//        });
//        crearConexion();
//        close();
        return crearTablaYBorrarSiExiste(nombreTabla, new String[0], columnasString);
    }

    public BDConexionController crearTablaYBorrarSiExiste(String nombreTabla, String columnasAnteriores[], String... columnasString) throws Exception {
        realizarAccionBD(() -> {
            executeDROP_TABLE_IF_EXISTSsinAccion(nombreTabla);
            crearTablaSiNoExiste(nombreTabla, columnasAnteriores, columnasString);
        });
//        realizarAccionBD(() -> {
//            bDConexion.getInstruccion().execute("DROP TABLE IF EXISTS " + nombreTabla + ";");
//            crearTablaSiNoExiste(nombreTabla, columnasAnteriores, columnasString);
//
//        });
//        crearConexion();
//        close();
        return this;
    }

    private void realizarAccionBD(RealizarConException r) throws Exception {
        bDConexion.openConexion();
        // crearConexion();
        r.realizar();
        // close();
        bDConexion.close();
    }
//public BDConexionController crearTablaSiNoExisteSinID(String nombreTabla, String columnas[],String tipos[],boolean nuleables[]) throws SQLException, ClassNotFoundException, Exception {
//     return crearTablaSiNoExiste(nombreTabla, new String[0], columnasString);
//    }

    public BDConexionController crearTablaSiNoExiste(String nombreTabla, String... columnasString) throws SQLException, ClassNotFoundException, Exception {
        return crearTablaSiNoExiste(nombreTabla, new String[0], columnasString);
    }

    public BDConexionController crearTablaSiNoExiste(String nombreTabla, String columnasAnteriores[], String... columnasString) throws SQLException, ClassNotFoundException, Exception {
        String argumentos = " (" + ID;
        for (int i = 0; i < columnasAnteriores.length; i++) {
            argumentos += ", " + columnasAnteriores[i] + " " + getTipoSQL(String.class);
        }
        for (int i = 0; i < columnasString.length; i++) {
            argumentos += ", " + columnasString[i] + " " + getTipoSQL(String.class);
        }
        argumentos += ");";
        executeCrearTabla(nombreTabla, IF_NOT_EXISTS, argumentos);
        return this;
    }

    private void crearTabla(String nombreTabla, Map<String, Class> nombreTipo) throws SQLException, ClassNotFoundException, Exception {
        final String argumentos = " (" + ID;
        nombreTipo.forEach((a, b) -> {
            argumentos.concat(", " + a + " " + getTipoSQL(b));
        });
        argumentos.concat(");");
        executeCrearTabla(nombreTabla, "", argumentos);
    }

    private void executeCrearTabla(String nombreTabla, String condicion, String argumentos) throws SQLException, ClassNotFoundException, Exception {
//        crearConexion();
        realizarAccionBD(() -> {
            ultimaEjecucion = "CREATE TABLE " + condicion + " " + nombreTabla + " " + argumentos;
            bDConexion.getInstruccion().execute(ultimaEjecucion);
        });

    }

    private String getTipoSQL(Class c) {
        if (or(c, int.class, Integer.class)) {
            return "int";
        }
        if (or(c, String.class)) {
            return "varchar";
        }
        return null;
    }

    public BDConexionController recorrerColumnas(Utilizar2Exception<ResultSetMetaData, Integer> c) throws Exception {
//        int numeroDeColumnas = metaDatos.getColumnCount();
        for (int i = 1; i <= metaDatos.getColumnCount(); i++) {
            c.utilizar(metaDatos, i - 1);
        }
        return this;
    }

    /**
     * Object[] son los valores dentro de las columnas<br>
     * Integer es el indice de la columna acual<br>
     * hay que cerrarlo al final<br>
     * recomendado  <code> recorrerResultados(...).close(); </code>
     *
     * @param c
     * @return
     * @throws Exception
     */
//    public void recorrerResultados(Utilizar3<ResultSet, Integer, Object[]> u) throws SQLException {
    public BDConexionController recorrerResultados(Utilizar2Exception< Object[], Integer> c) throws Exception {
        int i = 0;
        while (bDConexion.getConjuntoResultados().next()) {
            c.utilizar(getConjuntoResultadoActual(), i++);
        }
        if (tipoDeConxion == tipoDeConxion.SQL_LITE) {
            executeQuery(consultaAcual);
        } else {
            bDConexion.getConjuntoResultados().beforeFirst();
        }

        return this;
    }

    public BDConexionController recorrerResultadoActual(Utilizar2< Object, Integer> c) throws SQLException {
        for (int i = 1; i <= metaDatos.getColumnCount(); i++) {
            c.utilizar(bDConexion.getConjuntoResultados().getObject(i), i);
        }
        return this;
    }

    public Object[] getConjuntoResultadoActual() throws SQLException {
        Object[] n = new Object[metaDatos.getColumnCount()];
//        System.out.println("n.length="+n.length);
        for (int i = 1; i <= n.length; i++) {
            n[i - 1] = bDConexion.getConjuntoResultados().getObject(i);
        }
        return n;
    }

    public Object[] getConjuntoResultadoActualClose() throws SQLException {
        Object[] n = getConjuntoResultadoActual();
        close();
        return n;
    }

    public BDConexionController mostrarResultadosConsola() throws Exception {
        LinkedList<Object[]> l = new LinkedList<>();
        l.add(getNombreDeColumnas());
        recorrerResultados((O, i) -> {
            l.add(O);
        });
        Object[][] n = new Object[l.size()][];
        for (int i = 0; i < n.length; i++) {
            n[i] = l.get(i);
        }
        Arreglos.MostrarMatrizObjectTabulada(n);
        return this;
    }

    public Object[][] getConjuntoResultadoMatris() throws Exception {
        LinkedList<Object[]> l = new LinkedList<>();
//        Object[][] n = new Object[metaDatos.getColumnCount()][getCantidadDeResultados()];
        recorrerResultados((O, i) -> {
            l.add(O);
//            n[i] = O;
        });
        Object[][] n = new Object[l.size()][];
        for (int i = 0; i < n.length; i++) {
            n[i] = l.get(i);
        }
////        int i = 0;
////        while (conjuntoResultados.next()) {
////            for (int j = 0; j < metaDatos.getColumnCount(); j++) {
////                n[i][j] = conjuntoResultados.getObject(j);
////            }
////            i++;
////        }
////        conjuntoResultados.beforeFirst();
        //  return (Object[][]) l.toArray();
        return n;
    }

//    public int getCantidadDeResultados() throws SQLException {
//        int i = conjuntoResultados.getRow();
//        conjuntoResultados.last();
//        int size = conjuntoResultados.getRow();
//        conjuntoResultados.absolute(i);
//        return size;
//    }
    public String[] getNombreDeColumnas() throws Exception {
        String[] n = new String[metaDatos.getColumnCount()];
        recorrerColumnas((o, i) -> {
            n[i] = o.getColumnName(i + 1);
        });
//        for (int i = 1; i < n.length; i++) {
//            metaDatos.getColumnName(i);
//        }
        return n;
    }

    public BDConexionController close() throws SQLException {
        bDConexion.close();
        return this;
    }

    public String getUrl_basesDeDatos() {
        return url_basesDeDatos;
    }

    public String getUltimaEjecucion() {
        return ultimaEjecucion;
    }

    public String getUltimaConsulta() {
        return bDConexion.getUltimaConsulta();
    }

    public String getUltimaEjecucionClose() throws SQLException {
        String r = ultimaEjecucion;
        close();
        return r;
    }

    public String getUltimaConsultaClose() throws SQLException {
        String r = bDConexion.getUltimaConsulta();
        close();
        return r;
    }

    public static BDConexionController getMY_SQLConexion(File url) {
        return new BDConexionController(BDTipoDeConexion.MY_SQL, url);
    }

    public static BDConexionController getPOSTGRESConexion(String usuario, String contraseña, String nombreBD,String servidor, String puerto) {
        //return new BDConexionController(BDTipoDeConexion.POSTGRES, usuario, contraseña, nombreBD, puerto);
        return new BDConexionController(BDTipoDeConexion.POSTGRES, usuario, contraseña, nombreBD, servidor, puerto);
    }
    public static BDConexionController getPOSTGRESConexionLocal5432(String usuario, String contraseña, String nombreBD) {
        return getPOSTGRESConexion(usuario, contraseña, nombreBD, "localhost", "5432");
    }
}
