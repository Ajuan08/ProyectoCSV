package riosfernandez.antoniojuan.proyectocsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Operaciones {
    String paisActual = "";
    String seleccionPais = "";
    String seleccionAnno = "";
    String usuariosInternetNumero = "";
    Boolean comprobacion = false;   
    float media = 0;
    int contador = 0;
    int usuarioTotales = 0;
    
    public void lecturaFichero(HBox hBoxComboBox, HBox hBoxLabel, HBox hBoxComboBoxAbajo, HBox hBoxComboBoxExport, HBox hBoxLabelmedia){
        //Creacion de arraylist
        ArrayList<String> listaPais = new ArrayList();
        ArrayList<String> listaAnno = new ArrayList();
        
       String nombreFichero = "number-of-internet-users-by-country.csv";
       // Declarar una variable BufferedReader
        BufferedReader br = null;
        try {
            // Crear un objeto BufferedReader al que se le pasa 
            //   un objeto FileReader con el nombre del fichero
            br = new BufferedReader(new FileReader(nombreFichero));
            // Leer la primera línea, guardando en un String
            String texto = br.readLine();
            // Repetir mientras no se llegue al final del fichero
            while(texto != null) {
                String[] valores = texto.split(",");
                UsuariosInternet usuariosInternet = new UsuariosInternet();
                usuariosInternet.setNumUsuarios(valores[3]);
                usuariosInternet.setAño(valores[2]);
                usuariosInternet.setPais(valores[0]);
                System.out.println(usuariosInternet.getNumUsuarios());
                if(!paisActual.equals(usuariosInternet.getPais()) && !usuariosInternet.getPais().equals("Entity") && !usuariosInternet.getPais().isEmpty()){
                    paisActual = usuariosInternet.getPais();
                    listaPais.add(paisActual);
                }
                // Leer la siguiente línea
                texto = br.readLine();
            }
            for(int i=1990;i<=2017;i++)
                {
                    listaAnno.add(String.valueOf(i));
                }
        }
        // Captura de excepción por fichero no encontrado
        catch (FileNotFoundException ex) {
            System.out.println("Error: Fichero no encontrado");
            ex.printStackTrace();
        }
        // Captura de cualquier otra excepción
        catch(Exception ex) {
            System.out.println("Error de lectura del fichero");
            ex.printStackTrace();
        }
        // Asegurar el cierre del fichero en cualquier caso
        finally {
            try {
                // Cerrar el fichero si se ha podido abrir
                if(br != null) {
                    br.close();
                }
            }
            catch (Exception ex) {
                System.out.println("Error al cerrar el fichero");
                ex.printStackTrace();
            }
        }
        
        //COMBOBOX PAIS
        ComboBox<String> comboBoxPais = new ComboBox(FXCollections.observableList(listaPais));
        hBoxComboBox.getChildren().add(comboBoxPais);
        comboBoxPais.setPromptText("Seleccionar país");

        comboBoxPais.setOnAction((t) -> {
            seleccionPais = comboBoxPais.getValue();
        });
       
        //COMBOBOX AÑO
        ComboBox<String> comboBoxAnno = new ComboBox(FXCollections.observableList(listaAnno));
        hBoxComboBox.getChildren().add(comboBoxAnno);
        comboBoxAnno.setPromptText("Seleccionar año");
        
        comboBoxAnno.setOnAction((t) -> {
            seleccionAnno = comboBoxAnno.getValue();
        });
        
        //Label NumUsuarios
        Label internetLabel = new Label();
        internetLabel.setStyle("-fx-font-weight: bold");
        hBoxLabel.getChildren().add(internetLabel);
        
        //Label mediaTotal
        Label mediaTotalInternetLabel = new Label();
        mediaTotalInternetLabel.setStyle("-fx-font-weight: bold");
        hBoxLabelmedia.getChildren().add(mediaTotalInternetLabel);
        
        
        //BOTON BUSCAR
        Button buttonSearch = new Button("Buscar");
        hBoxComboBoxAbajo.getChildren().add(buttonSearch);
        buttonSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                
                
                // Declarar una variable BufferedReader
                BufferedReader br = null;
                try {
                    // Crear un objeto BufferedReader al que se le pasa
                    //   un objeto FileReader con el nombre del fichero
                    br = new BufferedReader(new FileReader(nombreFichero));
                    // Leer la primera línea, guardando en un String
                    String texto = br.readLine();
                    // Repetir mientras no se llegue al final del fichero
                    System.out.println("se ha seleccionado "+seleccionPais+" en "+seleccionAnno);
                    while(texto != null) {
                        String[] valores = texto.split(",");
                        UsuariosInternet usuariosInternet = new UsuariosInternet();
                        usuariosInternet.setNumUsuarios(valores[3]);
                        usuariosInternet.setAño(valores[2]);
                        usuariosInternet.setPais(valores[0]);
                        
                        if(usuariosInternet.getPais().equals(seleccionPais) && usuariosInternet.getAño().equals(seleccionAnno)){
                                System.out.println("ha entrado");
                                usuariosInternetNumero = usuariosInternet.getNumUsuarios();
                                comprobacion = true;
                            
                        }
                        
                        if(usuariosInternet.getPais().equals(seleccionPais)){
                            usuarioTotales += Integer.parseInt(usuariosInternet.getNumUsuarios());
                            contador++;
                            
                        }
                        // Leer la siguiente línea
                        texto = br.readLine();
                    }
                    
                    if(comprobacion==true){
                        internetLabel.setText("Numero de usuarios en "+seleccionPais+" en el año "+seleccionAnno+": "+usuariosInternetNumero);
                        comprobacion = false;
                    }else{
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setHeaderText(null);
                            alert.setTitle("REGISTROS");
                            alert.setContentText("NO SE ENCUENTRAN REGISTROS EN ESTA FECHA");
                            alert.showAndWait();
                    }
                    
                    media = usuarioTotales / contador;
                    mediaTotalInternetLabel.setText("La media total de usuarios en " +seleccionPais+ " es:" +media);
                    contador = 0;
                    usuarioTotales = 0;
                    
                }
                // Captura de excepción por no encontrarse el fichero
                catch (FileNotFoundException ex) {
                    System.out.println("Error: No se encuentra el fichero");
                    ex.printStackTrace();
                }
                // Captura de cualquier otra excepción
                catch(Exception ex) {
                    System.out.println("Error de lectura del fichero");
                    ex.printStackTrace();
                }
                // Asegurar el cierre del fichero en cualquier caso
                finally {
                    try {
                        // Cerrar el fichero si se ha podido abrir
                        if(br != null) {
                            br.close();
                        }
                    }
                    catch (Exception ex) {
                        System.out.println("Error al cerrar el fichero");
                        ex.printStackTrace();
                    }
                }
                
            }
        });
        
        //BOTON EXPORTAR
        Button buttonExport = new Button("Exportar");
        hBoxComboBoxExport.getChildren().add(buttonExport);
        buttonExport.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
               Exportar(media,seleccionPais);
            }
        });
    }   
             
    
    public void Exportar(float media, String seleccionPais){
        String nombreFichero = "Media_usuarios.csv";
        String textoEncabezado = "Pais,Media de usuarios ";
        String textoDatos = seleccionPais+","+media;
        BufferedWriter bw = null;
        
        try {
        //Crear un objeto BufferedWriter. Si ya existe el fichero, 
        //  se borra automáticamente su contenido anterior.
            bw = new BufferedWriter(new FileWriter(nombreFichero));
        //Escribir en el fichero el texto con un salto de línea
            bw.write(textoEncabezado + "\n");
            bw.write(textoDatos + "\n");
        }
        // Comprobar si se ha producido algún error
        catch(Exception ex) {
            System.out.println("Error de escritura del fichero");
            ex.printStackTrace();
        }
        // Asegurar el cierre del fichero en cualquier caso
        finally {
            try {
                // Cerrar el fichero si se ha podido abrir
                if(bw != null)
                    bw.close();
            }
            catch (Exception ex) {
                System.out.println("Error al cerrar el fichero");
                ex.printStackTrace();
            }
        }
    }

}
