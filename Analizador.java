package proyectolfp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;


public class Analizador {

    String Cadena_Entrada;
    String Retornar_tokens;
    String Retornar_errores;
    
    static String [][] Jugador1 = new String[5][8];
    static String [][] Jugador2 = new String[5][8];
    static String [][] Guardar_Barcos = new String [50][5]; //Esta matriz solo sirve para guardar cada token en posicion y para Barcos
    static String [][] Guardar_Posiciones = new String [50][4]; //Esta matriz solo sirve para guardar cada token de poscion de los barcos esta es para definicion
    String [] Palabras_Reservadas  = new String [10];
    
    static ArrayList<Token> Lista_Tokens = new ArrayList<Token>();
    static ArrayList<Error> Lista_Errores = new ArrayList<Error>();
    ArrayList<Temporal> Lista_Temporal = new ArrayList<Temporal>();
    ArrayList<Manipulador> Lista = new ArrayList<Manipulador>();
    ArrayList<String> dato = new ArrayList<>();

    int fila = 0;
    int columna = 0;
     

public Analizador(String Cadena_Entrada){
    this.Cadena_Entrada = Cadena_Entrada;
}
  

public void Escaner_J(){
  
    //Palabras reservadas
    Palabras_Reservadas[0] = "Barco";
    Palabras_Reservadas[1] = "ID";
    Palabras_Reservadas[2] = "NOMBRE";
    Palabras_Reservadas[3] = "TAMAÑO";
    Palabras_Reservadas[4] = "TIPO";
    Palabras_Reservadas[5] = "COLOR";
    Palabras_Reservadas[6] = "X";
    Palabras_Reservadas[7] = "Y";
    Palabras_Reservadas[8] = "JUGADOR";
    Palabras_Reservadas[9] = "Definicion";
    
    
    String lexema = "";
    int tamano = Cadena_Entrada.length();
    int posicion = -1;
    int estado = 0;
    int contador = 0; //contador para filas de la matriz
   
    
    while(posicion<tamano-1){
        posicion++;      
        char caracter = Cadena_Entrada.charAt(posicion);       
        switch(estado){
          
            case 0:
                
                if(caracter == ';'){
                    lexema += ";";
                    Agregar_Token(lexema, fila++, columna++, "PuntoyComa");
                    Agregar_Temporal(lexema, "PuntoyComa");
                    lexema = "";
                }else if(caracter == '{'){
                    lexema += "{";
                    Agregar_Token(lexema, fila++, columna++, "Abrirllave");
                    Agregar_Temporal(lexema, "Abrirllave");
                    lexema = "";
                }else if(caracter == '}'){
                    lexema += "}";
                    Agregar_Token(lexema, fila++, columna++, "Cerrarllave");
                    Agregar_Temporal(lexema, "Cerrarllave");
                    lexema = "";
                }else if(caracter == '<'){
                    lexema += "<";
                    Agregar_Token(lexema, fila++, columna++, "Menor");
                    Agregar_Temporal(lexema, "Menor");
                    lexema = "";
                }else if(caracter == '>'){
                     lexema += ">";
                    Agregar_Token(lexema, fila++, columna++, "Mayor");
                    Agregar_Temporal(lexema, "Mayor");
                    lexema = "";
                }else if(caracter == ':'){
                    lexema += ":";
                    Agregar_Token(lexema, fila++, columna++, "Dospuntos");
                    Agregar_Temporal(lexema, "Dospuntos");
                    lexema = "";
                }else if(caracter == ','){
                    lexema += ",";
                    Agregar_Token(lexema, fila++, columna++, "Coma");
                    Agregar_Temporal(lexema, "Coma");
                    lexema = "";
                }else if(Character.isLetter(caracter)){
                    lexema += caracter;
                    estado = 1;
                }else if(Character.isDigit(caracter)){
                    lexema += caracter;
                    estado = 2;
                }else if(caracter == '"'){
                    lexema += caracter;
                    estado = 3;
                }else if(Character.isWhitespace(caracter)){
                    //aqui valida el espacio
                    //no hacer nada
                }else{
                    
                    System.out.println("Error " + caracter);
                    Agregar_Error(lexema, "Caracter no valido", fila++, columna++);
                    
                }
               
            break; //Fin del estado 0
            
            
            case 1: //Inicio del estado 1                
                if(Character.isLetter(caracter)){
                    lexema += caracter;
                    estado = 1;
                }else if(Character.isDigit(caracter)){
                    lexema += caracter;
                    estado = 1;
                }else if(caracter == '_'){
                    lexema += caracter;
                    estado = 1;
                }else{
                      
                    if(lexema.equalsIgnoreCase(Palabras_Reservadas[0])){
                    Agregar_Token(lexema, fila++, columna++, "ReservadaBarco");
                    Agregar_Temporal(lexema, "ReservadaBarco");
                    dato.add("ReservadaBarco");
                    lexema = "";
                    estado = 0;
                    posicion--;
                    
                    }else if(lexema.equalsIgnoreCase(Palabras_Reservadas[1])){
                    Agregar_Token(lexema, fila++, columna++, "ReservadaID");
                    Agregar_Temporal(lexema, "ReservadaID");
                    lexema = "";
                    estado = 0;
                    posicion--;
                    
                    }else if(lexema.equalsIgnoreCase(Palabras_Reservadas[2])){
                    Agregar_Token(lexema, fila++, columna++, "ReservadaNombre");
                    Agregar_Temporal(lexema, "ReservadaNombre");
                    lexema = "";
                    estado = 0;
                    posicion--;
                    
                    }else if(lexema.equalsIgnoreCase(Palabras_Reservadas[3])){
                    Agregar_Token(lexema, fila++, columna++, "ReservadaTamaño");
                    Agregar_Temporal(lexema, "ReservadaTamaño");
                    lexema = "";
                    estado = 0;
                    posicion--;
                    
                    }else if(lexema.equalsIgnoreCase(Palabras_Reservadas[4])){
                    Agregar_Token(lexema, fila++, columna++, "ReservadaTipo");
                    Agregar_Temporal(lexema, "ReservadaTipo");
                    lexema = "";
                    estado = 0;
                    posicion--;
                    
                    }else if(lexema.equalsIgnoreCase(Palabras_Reservadas[5])){
                    Agregar_Token(lexema, fila++, columna++, "ReservadaColor");
                    Agregar_Temporal(lexema, "ReservadaColor");
                    lexema = "";
                    estado = 0;
                    posicion--;
                    
                    }else if(lexema.equalsIgnoreCase(Palabras_Reservadas[6])){
                    Agregar_Token(lexema, fila++, columna++, "ReservadaX");
                    Agregar_Temporal(lexema, "ReservadaX");
                    lexema = "";
                    estado = 0;
                    posicion--;
                    
                    }else if(lexema.equalsIgnoreCase(Palabras_Reservadas[7])){
                    Agregar_Token(lexema, fila++, columna++, "ReservadaY");
                    Agregar_Temporal(lexema, "ReservadaY");
                    lexema = "";
                    estado = 0;
                    posicion--;
                    
                    }else if(lexema.equalsIgnoreCase(Palabras_Reservadas[8])){
                    Agregar_Token(lexema, fila++, columna++, "ReservadaJugador");
                    Agregar_Temporal(lexema, "ReservadaJugador");
                    lexema = "";
                    estado = 0;
                    posicion--;
                    
                    }else if(lexema.equalsIgnoreCase(Palabras_Reservadas[9])){
                    Agregar_Token(lexema, fila++, columna++, "ReservadaDefinicion");
                    Agregar_Temporal(lexema, "ReservadaDefinicion");
                    dato.add("ReservadaDefinicion");
                    lexema = "";
                    estado = 0;
                    posicion--;
                    
                    }else{
                    Agregar_Token(lexema, fila++, columna++, "Identificador");
                    Agregar_Temporal(lexema, "Identificador");
                    Agregar(lexema, "Identificador");
                    lexema = "";
                    estado = 0;
                    posicion--;
                    
                    }  
                                     
                }          
                break; //Fin del estado 1
            
            case 2: // Inicio del estado 2
                if(Character.isDigit(caracter)){
                    lexema += caracter;
                    estado = 2;
                }else{
                    Agregar_Token(lexema, fila++, columna++, "Numero");
                    Agregar_Temporal(lexema, "Numero");
                    Agregar(lexema, "Numero");
                    lexema = "";
                    estado = 0;
                    posicion--;
                }
                break; //Fin del estado 2
             
            case 3: // Inicio del estado 3
                if(caracter != '"'){
                    lexema += caracter;
                    estado = 3;
                }else if(caracter == '"'){
                    lexema += caracter;
                    estado = 4;
                }
                break;//Fin del estado 3
                
                
            case 4: //Inicio del estado 4
                 Agregar_Token(lexema, fila++, columna++, "Cadena");
                 Agregar_Temporal(lexema, "Cadena");
                 Agregar(lexema, "Cadena");
                 lexema = "";
                 estado = 0;
                 posicion--; 
                break; // Fin del estado 4
        }
    }
    
}


public void Arbitro(){
   
 if(dato.get(0).equals("ReservadaBarco")){
     Ordenador();
 }else if(dato.get(0).equals("ReservadaDefinicion")){
     Ordenador2();
     Mostrar_datos();
     
 }
    
}

     
public void Ordenador(){ //metodo solo para la etiqueta de barco
    
    try{
        int contadornuevo = 0;
   
        
          while(!Lista.isEmpty()){ 
          Guardar_Barcos[contadornuevo][0] = Lista.get(0).getLexema();  
          Guardar_Barcos[contadornuevo][1] = Lista.get(1).getLexema();
          Guardar_Barcos[contadornuevo][2] = Lista.get(2).getLexema();
          Guardar_Barcos[contadornuevo][3] = Lista.get(3).getLexema();
          Guardar_Barcos[contadornuevo][4] = Lista.get(4).getLexema();
          Lista.remove(0);
          Lista.remove(0);
          Lista.remove(0);
          Lista.remove(0);
          Lista.remove(0);
          contadornuevo++;
          }
          
           JOptionPane.showMessageDialog(null, "Barcos Guardados de forma correcta");
        
    }catch(Exception ios){
        JOptionPane.showMessageDialog(null, "Error no se puede ejecutar la cadena, por favor revise el texto");
    }
          
//      for(int i=0;i<Guardar_Barcos.length;i++){
//          for(int j =0;j<Guardar_Barcos[i].length;j++){
//              System.out.print("[" + Guardar_Barcos[i][j] + "]");
//          }
//          System.out.println("");
//      }
  
}

public void Ordenador2(){ //metodo solo para la etiqueta de Definicion
    
    
    try{
        int contadornuevo = 0;  
          while(!Lista.isEmpty()){ 
          Guardar_Posiciones[contadornuevo][0] = Lista.get(0).getLexema();  
          Guardar_Posiciones[contadornuevo][1] = Lista.get(1).getLexema();
          Guardar_Posiciones[contadornuevo][2] = Lista.get(2).getLexema();
          Guardar_Posiciones[contadornuevo][3] = Lista.get(3).getLexema();
          Lista.remove(0);
          Lista.remove(0);
          Lista.remove(0);
          Lista.remove(0);
          contadornuevo++;
          }
          
          JOptionPane.showMessageDialog(null, "Posiciones validas");
          
    }catch(Exception ios){
         JOptionPane.showMessageDialog(null, "Error no se puede ejecutar la cadena, por favor revise el texto");
    } 
    
//      for(int i=0;i<Guardar_Posiciones.length;i++){
//          for(int j =0;j<Guardar_Posiciones[i].length;j++){
//              System.out.print("[" + Guardar_Posiciones[i][j]+ "]");
//          }
//          System.out.println("");
//      }  
//      
      
      
}

public void Mostrar_datos(){
    
    int obtenerposicion1 = 0;
    int obtenerposicion2 = 0;
    //para matriz de barcos
    String datoobtenido1 = "";
    String nombre = "";
    String tamano = "";
    String color = "";
    String tipo = "";
    
    //para matriz de definicion
    String datoobtenido2 = "";
    String posicionx = "";
    String posiciony = "";
    String jugador = "";
    
    while(Guardar_Barcos[obtenerposicion1][0] != null){
    datoobtenido1 = Guardar_Barcos[obtenerposicion1][0];
    nombre = Guardar_Barcos[obtenerposicion1][1];
    tamano = Guardar_Barcos[obtenerposicion1][2];
    color = Guardar_Barcos[obtenerposicion1][3];
    tipo = Guardar_Barcos[obtenerposicion1][4];
    
    
        while(Guardar_Posiciones[obtenerposicion2][0] != null){
            datoobtenido2 = Guardar_Posiciones[obtenerposicion2][0];
            posicionx = Guardar_Posiciones[obtenerposicion2][1];
            int x =  Integer.valueOf(posicionx);
            posiciony = Guardar_Posiciones[obtenerposicion2][2];
            int y = Integer.valueOf(posiciony);
            jugador = Guardar_Posiciones[obtenerposicion2][3];
            
            if(datoobtenido2.equals(datoobtenido1)){
                if(jugador.equals("1")){
                        Jugador1[x][y] = datoobtenido2;
                    //System.out.println(datoobtenido2 + x + y);
                    obtenerposicion2++;
                }else if(jugador.equals("2")){
                    Jugador2[x][y] = datoobtenido2;
                   // System.out.println(datoobtenido2 + x + y);
                    obtenerposicion2++;
                }
            }else{
                 obtenerposicion2++;
            }
           
        }   
    obtenerposicion2 = 0;
    obtenerposicion1++;
    }
    
    for(int i=0;i<Jugador1.length;i++){
          for(int j =0;j<Jugador1[i].length;j++){
              System.out.print("[" + Jugador1[i][j]+ "]");
          }
          System.out.println("");
      }  
    
          System.out.println("------------------------");
          
           for(int x=0;x<Jugador2.length;x++){
          for(int y =0;y<Jugador2[x].length;y++){
              System.out.print("[" + Jugador2[x][y]+ "]");
          }
          System.out.println("");
      }     
 
          
}




public void Agregar_Token(String lexema, int filas, int columnas, String token){
    Token agregar = new Token(lexema,filas,columnas,token);
    Lista_Tokens.add(agregar);  
}

public void Agregar_Error(String caracter,String descripcion,int filax,int columnax){
    Error reconocer = new Error(caracter,descripcion,filax,columnax);
    Lista_Errores.add(reconocer);
}

public void Agregar_Temporal(String lexema, String token){
    Temporal tomar = new Temporal(lexema,token);
    Lista_Temporal.add(tomar);
}

public void Agregar(String lexema,String token){
    Manipulador agregar = new Manipulador(lexema, token);
    Lista.add(agregar);
}

//Metodo del reporte
public void GenerarReporteTokens(){
            try{
                FileWriter sw = new FileWriter("Reportedetokens.html");
                BufferedWriter redactor = new BufferedWriter(sw);
                String a1 = "<!DOCTYPE html>";
                String a2 = "<html lang = 'es'>";
                String a3 = "<head>";
                String a4 = "<title> " + " LPE " + "</title>";
                String a5 = "</head>";
                String a6 = "<body>";
                String a7 = "<header>";
                String a8 = "<h1 style=" + "text-align:center;" + ">" + "Reporte de Tokens" + " </h1>";
                String a9 = "<p style=" + "text-align:center;" + ">" + "Este reporte detalla todos los tokens encontrados en la consola" + " </p>";
                String a10 = "<table border = 5 " + "align = " + "center" + ">";
                String a11 = "<tr>";
                String a12 = "<th >" + "No." + "</th>";
                String a13 = "<th >" + "Lexema" + "</th>";
                String a14 = "<th >" + "Fila" + "</th>";
                String a15 = "<th >" + "Columna" + "</th>";
                String a16 = "<th >" + "Token" + "</th>";
                String a17 = "</tr>";
                String a18 = "<tr>";
                String a19 = "</tr>";
                String a20 = "</table>";
                String a21 = "</header>";
                String a22 = "</body>";
                String a23 = "</html>";

                redactor.write(a1);
                redactor.write(a2);
                redactor.write(a3);
                redactor.write(a4);
                redactor.write(a5);
                redactor.write(a6);
                redactor.write(a7);
                redactor.write(a8);
                redactor.write(a9);
                redactor.write(a10);
                redactor.write(a11);
                redactor.write(a12);
                redactor.write(a13);
                redactor.write(a14);
                redactor.write(a15);
                redactor.write(a16);
                redactor.write(a17);

                for (int k = 0; k < Lista_Tokens.size(); k++)
                {
                    Token reconocer = Lista_Tokens.get(k);
                    Retornar_tokens = "<td>" + k + "</td>" + "<td>" + reconocer.getLexema() + "</td>" + "<td>" + reconocer.getFila() + "</td>" + "<td>" + reconocer.getColumna() + "</td>" + "<td>" + reconocer.getToken() + "</td>";
                    redactor.write(a18);
                    redactor.write(Retornar_tokens);
                    redactor.write(a19);
                }
                redactor.write(a20);
                redactor.write(a21);
                redactor.write(a22);
                redactor.write(a23);
                redactor.close();

            }
            catch (Exception e)
            {

            }
        }
//Fin del metodo del reporte

//Metodo de Errores
public void GenerarReporteErrores(){
            try{
                FileWriter sw = new FileWriter("Reportedeerrores.html");
                BufferedWriter redactor = new BufferedWriter(sw);
                String a1 = "<!DOCTYPE html>";
                String a2 = "<html lang = 'es'>";
                String a3 = "<head>";
                String a4 = "<title> " + " LPE " + "</title>";
                String a5 = "</head>";
                String a6 = "<body>";
                String a7 = "<header>";
                String a8 = "<h1 style=" + "text-align:center;" + ">" + "Reporte de Errores" + " </h1>";
                String a9 = "<p style=" + "text-align:center;" + ">" + "Este reporte detalla todos los errores encontrados en la consola" + " </p>";
                String a10 = "<table border = 5 " + "align = " + "center" + ">";
                String a11 = "<tr>";
                String a12 = "<th >" + "No." + "</th>";
                String a13 = "<th >" + "Lexema" + "</th>";
                String a14 = "<th >" + "Descripción" + "</th>";
                String a15 = "<th >" + "Fila" + "</th>";
                String a16 = "<th >" + "Columna" + "</th>";
                String a17 = "</tr>";
                String a18 = "<tr>";
                String a19 = "</tr>";
                String a20 = "</table>";
                String a21 = "</header>";
                String a22 = "</body>";
                String a23 = "</html>";

                redactor.write(a1);
                redactor.write(a2);
                redactor.write(a3);
                redactor.write(a4);
                redactor.write(a5);
                redactor.write(a6);
                redactor.write(a7);
                redactor.write(a8);
                redactor.write(a9);
                redactor.write(a10);
                redactor.write(a11);
                redactor.write(a12);
                redactor.write(a13);
                redactor.write(a14);
                redactor.write(a15);
                redactor.write(a16);
                redactor.write(a17);

                for (int k = 0; k < Lista_Errores.size(); k++)
                {
                    Error reconocer = Lista_Errores.get(k);
                    Retornar_errores = "<td>" + k + "</td>" + "<td>" + reconocer.getCaracter()+ "</td>" + "<td>" + reconocer.getDescripcion() + "</td>" + "<td>" + reconocer.getFila() + "</td>" + "<td>" + reconocer.getColumna() + "</td>";
                    redactor.write(a18);
                    redactor.write(Retornar_errores);
                    redactor.write(a19);
                }
                redactor.write(a20);
                redactor.write(a21);
                redactor.write(a22);
                redactor.write(a23);
                redactor.close();

            }
            catch (Exception e)
            {

            }
        }
//Fin del metodo Errores        

}
