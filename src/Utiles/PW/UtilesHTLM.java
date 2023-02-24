/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.PW;

import Utiles.ClasesUtiles.Interfases.Funcionales.Creador2;
import static Utiles.MetodosUtiles.MetodosUtiles.unirString;
import static Utiles.MetodosUtiles.MetodosUtiles.unirStringC;
import java.util.Arrays;
import javafx.util.Callback;

/**
 * Version 0.4
 *
 * @author Rene
 */
public abstract class UtilesHTLM {
     public static String title(String... content) {
        return etiquetaParCierre("title", unirString(content));
    }
    public static String linkCSS(String href){
    return "<link href=\""+href+"\" rel=\"stylesheet\"/>";
    }
    public static String etiquetaParCierreCLASS(String nombreEtiqueta,String clas, String... content) {
         return etiquetaParCierreAtributos(nombreEtiqueta, " class=\""+clas+"\" ", content);
    }
    public static String etiquetaParCierreID(String nombreEtiqueta,String id, String... content) {
        return etiquetaParCierreAtributosID(nombreEtiqueta, id, "", content);
    }
    public static String etiquetaParCierreAtributosID(String nombreEtiqueta,String id,String atributos, String... content) {
        return etiquetaParCierreAtributos(nombreEtiqueta, " id=\""+id+"\" "+atributos, content);
      
    }
    public static String thCLASS(String clas,String... content) {
        return etiquetaParCierreCLASS("th",clas, unirString(content));
    }
    public static String tdCLASS(String clas,String... content) {
        return etiquetaParCierreCLASS("td",clas, unirString(content));
    }
    public static String tableCLASS(String clas,String... content) {
        return etiquetaParCierreCLASS("table",clas, unirString(content));
    }
    public static String divCLASS(String clas,String... content) {
         return etiquetaParCierreCLASS("div",clas, unirString(content));
    }
     public static String sectionID(String id,String... content) {
         return etiquetaParCierreID("section",id, unirString(content));
    }
     public static<E> String crearHTMLTheme(String []nombresColumnas,E[] E,Creador2<E,Integer,String[]> c){
         String a="assets/",ac=a+"css/",acs=ac+"style",css=".css";
     return html(head(title("Tabla"),linkCSS(ac+"bootstrap"+css)
             ,linkCSS(a+"font-awesome/css/font-awesome"+css)
             ,linkCSS(acs+css)
              ,linkCSS(acs+"-responsive"+css)
     ),body(crearTablaTheme(nombresColumnas, E, c)));
     }
    public static<E> String crearTablaTheme(String []nombresColumnas,E[] E,Creador2<E,Integer,String[]> c){
     String head="",body="",numeric="numeric";
         for (int i = 0; i < nombresColumnas.length; i++) {
             head+=thCLASS(numeric,nombresColumnas[i]);
         }
         String z[]=new String[E.length];
         Arrays.fill(z, "");
         for (int i = 0; i < E.length; i++) {
             String  fila[]=c.crear(E[i],i);
             for (int j = 0; j < fila.length; j++) {
                 z[i]+=tdCLASS(numeric,fila[j]);
             }
         }
         for (int i = 0; i < z.length; i++) {
            // System.out.println("tr(z[i])="+tr(z[i]));
             body+=tr(z[i]);
         }
         return sectionID("container", divCLASS("row", divCLASS("col-lg-12",sectionID("unseen", tableCLASS("table table-bordered table-striped table-condensed",thead(tr(head)),tbody(body))) )));
     //return table(thead(tr(head)),tbody(body));
    }
     public static<E> String crearTabla(String []nombresColumnas,E[] E,Creador2<E,Integer,String[]> c){
         String head="",body="";
         for (int i = 0; i < nombresColumnas.length; i++) {
             head+=th(nombresColumnas[i]);
         }
         String z[]=new String[E.length];
         Arrays.fill(z, "");
         for (int i = 0; i < E.length; i++) {
             String  fila[]=c.crear(E[i],i);
             for (int j = 0; j < fila.length; j++) {
                 z[i]+=td(fila[j]);
             }
         }
         for (int i = 0; i < z.length; i++) {
            // System.out.println("tr(z[i])="+tr(z[i]));
             body+=tr(z[i]);
         }
         //String ta=;
          return table(thead(tr(head)),tbody(body));
     
     }
     
     
      public static String tbody(String... content) {
        return etiquetaParCierre("tbody", unirString(content));
    }
     public static String table(String... content) {
        return etiquetaParCierre("table", unirString(content));
    }
    public static String header(String... content) {
        return etiquetaParCierre("header", unirString(content));
    }
    public static String thead(String... content) {
        return etiquetaParCierre("thead", unirString(content));
    }
    public static String tr(String... content) {
        return etiquetaParCierre("tr", unirString(content));
    }
    public static String td(String... content) {
        return etiquetaParCierre("td", unirString(content));
    }
    public static String th(String... content) {
        return etiquetaParCierre("th", unirString(content));
    }
    public static String canvas(String id,int alto,int ancho,String... content) {
        return etiquetaParCierreAtributos("canvas","id=\""+id+"\" height=\""+alto+"\" width=\""+ancho+"\"", unirString(content));
    }
     public static String script(String... content) {
        return etiquetaParCierreAtributos("script","type=\"text/javascript\"", unirString(content));
    }
    public static String htmlGeneral(String... content) {
        return "<!DOCTYPE html>"+ html(head(" <meta charset=\"utf-8\">"), body(content));
    }
 
    public static String html(String... content) {
        return etiquetaParCierre("html", unirString(content));
    }
    
    public static String center(String... content) {
        return etiquetaParCierre("center", unirString(content));
    }
    
    public static String centerLn(String... content) {
        return unirStringC(v -> center(v), content);
    }
    
    public static String head(String... content) {
        return etiquetaParCierre("head", unirString(content));
    }
    
    public static String body(String... content) {
        return etiquetaParCierre("body", unirString(content));
    }
    
    public static String brLn(String... content) {
        return unirStringC(v -> br(v), content);
    }

//    public static String br() {
//        return "<br>";
//    }
    public static String br(String... content) {
        return etiqueta("br") + unirString(content);
    }
    
    public static String strong(String... content) {
        return etiquetaParCierre("strong", unirString(content));
    }
    
    public static String big(String... content) {
        return etiquetaParCierre("big", unirString(content));
    }
    
    public static String etiquetaParCierre(String nombreEtiqueta, String... content) {
        return "<" + nombreEtiqueta + ">" + unirString(content) + "</" + nombreEtiqueta + ">";
    }
    public static String etiquetaParCierreAtributos(String nombreEtiqueta,String atributos, String... content) {
        return "<" + nombreEtiqueta+" "+atributos + " >" + unirString(content) + "</" + nombreEtiqueta + ">";
    }
    public static String etiqueta(String nombreEtiqueta) {
        return "<" + nombreEtiqueta + ">";
    }
}
