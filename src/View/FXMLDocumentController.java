/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Sekou
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
     @FXML
    private TextField input_message;
    @FXML
    private TextArea output_message;
    
  
    @FXML
      private void clickButtonAction(ActionEvent event) {
      
        System.out.println(input_message.getText());
        output_message.appendText(input_message.getText() + "\n");
        input_message.setText("");
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
    }    
    
}
