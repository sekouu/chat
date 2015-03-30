/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.MultiCastClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Sekou
 */
public class ChatWindow extends Application {
    
    private final MultiCastClient client ;
   
    public ChatWindow()
    {
      client = new MultiCastClient();
    }
    
        public ChatWindow(MultiCastClient cl)
    {
        this.client = cl;
    }
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane p = fxmlLoader.load(getClass().getResource("FXMLDocument.fxml").openStream());
        FXMLDocumentController controller = (FXMLDocumentController)fxmlLoader.getController();
        
        controller.setClient(client);
        
        Scene scene = new Scene(p, 600, 362);
        stage.setMinWidth(600.0);
        stage.setMinHeight(362.0);
        stage.setScene(scene);
        stage.setTitle("Chat");
        stage.show();
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
