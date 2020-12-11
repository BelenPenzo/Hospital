package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    public Button btn_generar;
    public Button btn_limpiar;
    public TextField txt_cantSim;
    public TextField txt_desde;
    public TextField txt_hasta;
    public ImageView img_check;
    public TextField txt_media_con;
    public TextField txt_media_sin;
    public TextField txt_a_secretaria;
    public TextField txt_b_secretaria;
    public TextField txt_media_medico;
    public TextField txt_desv_medico;
    public TextField txt_cant_turnos;
    public TextField txt_cierre_consultorio;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Paciente paciente = new Paciente();



        btn_generar.setOnAction(e -> {
            paciente.reset();
            img_check.setVisible(false);
            img_check.managedProperty().bind(img_check.visibleProperty());
            Simulacion sim = null;
            try {
                sim = new Simulacion(Integer.parseInt(txt_cantSim.getText()), 0, Integer.parseInt(txt_desde.getText()), Integer.parseInt(txt_hasta.getText()),
                        Integer.parseInt(txt_media_con.getText()), Integer.parseInt(txt_media_sin.getText()), Integer.parseInt(txt_a_secretaria.getText()),
                        Integer.parseInt(txt_b_secretaria.getText()), Integer.parseInt(txt_media_medico.getText()), Integer.parseInt(txt_desv_medico.getText()),
                        Integer.parseInt(txt_cant_turnos.getText()),Integer.parseInt(txt_cierre_consultorio.getText()));
                img_check.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btn_limpiar.setOnAction(e -> {
            img_check.setVisible(false);
            img_check.managedProperty().bind(img_check.visibleProperty());

            txt_cantSim.setText("");
            txt_desde.setText("");
            txt_hasta.setText("");
        });


    }

}