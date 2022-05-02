package riosfernandez.antoniojuan.proyectocsv;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class App extends Application {
    
    String paisActual = "";
    String seleccionPais = "";
    String seleccionAnno = "";
    String usuariosInternetNumero = "";
    Boolean comprobacion = false;   
    float media = 0;
    int contador = 0;
    int usuarioTotales = 0;
    ImageView titulo;

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.TOP_CENTER);
        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
        
        //imagen del banner
        Image banner = new Image(getClass().getResourceAsStream("/images/banner.png"));
        titulo = new ImageView(banner);
        root.getChildren().add(titulo);
        
    
        HBox hBoxBANNER = new HBox(20);
        hBoxBANNER.setAlignment(Pos.CENTER);
        root.getChildren().add(hBoxBANNER);
        
        HBox hBoxComboBox = new HBox(20);
        hBoxComboBox.setAlignment(Pos.CENTER);
        root.getChildren().add(hBoxComboBox);
        
        //Hbox del Label
        HBox hBoxLabel = new HBox(20);
        hBoxLabel.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().add(hBoxLabel);
        
        
        //Hbox del Label 
        HBox hBoxLabelmedia = new HBox(20);
        hBoxLabelmedia.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().add(hBoxLabelmedia);
        
        
        
        //Hbox del boton buscar
        HBox hBoxComboBoxAbajo = new HBox(20);
        hBoxComboBoxAbajo.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().add(hBoxComboBoxAbajo);
        
        //Hbox del boton Exportar
        HBox hBoxComboBoxExport = new HBox(20);
        hBoxComboBoxExport.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().add(hBoxComboBoxExport);
        
        
        Operaciones operaciones = new Operaciones();
        operaciones.lecturaFichero(hBoxComboBox, hBoxLabel, hBoxComboBoxAbajo, hBoxComboBoxExport, hBoxLabelmedia);
    }
    
    public static void main(String[] args) {
        launch();
    }

}