package ehu.isad.controllers.ui;

import ehu.isad.Main;
import ehu.isad.controllers.db.MainKudeatzaile;
import ehu.isad.model.Liburua;
import ehu.isad.utils.Sarea;
import ehu.isad.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class MainKud implements Initializable {

    private Main main;
    private List<Liburua> liburuLista = new ArrayList<Liburua>();
    private int unekoa;


    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private ImageView mgvw;

    @FXML
    private TextArea txtA_izenburua;

    @FXML
    private TextArea txtA_egilea;

    @FXML
    private TextArea txtA_urtea;

    @FXML
    private TextArea txtA_isbn;

    @FXML
    private Button btn_bilatu;

    @FXML
    private Button btn_atzera;

    @FXML
    private Button btn_aurrera;

    @FXML
    void onClick(ActionEvent event) throws IOException {
        if(event.getSource()==btn_atzera){
            unekoa--;
            datuakJarri();

        }
        else if(event.getSource()==btn_aurrera){
            unekoa++;
            datuakJarri();

        }
        else if(event.getSource()==btn_bilatu){
            Liburua l = new Liburua();
            l = l.datuakLortu(txtA_isbn.getText());
            l.setISBN(txtA_isbn.getText());
            String url = l.getThumbnail_url();
            url = url.substring(0,url.length()-5);
            url = url+"M.jpg";

            String[] zatiak = url.split("/");
            String izena = zatiak[zatiak.length-1];
            l.setThumbnail_url(izena);
            Sarea s = new Sarea();
            s.irudiaSortu(url,izena);

            MainKudeatzaile.getInstance().datuakGorde(l);
            liburuLista= MainKudeatzaile.getInstance().lortuDatuak();
            unekoa = liburuLista.size()-1;
            datuakJarri();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        liburuLista= MainKudeatzaile.getInstance().lortuDatuak();
        unekoa = 0;
        datuakJarri();

    }

    private void datuakJarri(){
        if(unekoa==0){
            btn_atzera.setDisable(true);
            btn_aurrera.setDisable(false);
        }
        else if(unekoa==liburuLista.size()-1){
            btn_aurrera.setDisable(true);
            btn_atzera.setDisable(false);
        }
        else{
            btn_aurrera.setDisable(false);
            btn_atzera.setDisable(false);
        }

        txtA_isbn.setText(liburuLista.get(unekoa).getISBN());
        txtA_izenburua.setText(liburuLista.get(unekoa).getDetails().getTitle());
        txtA_urtea.setText(liburuLista.get(unekoa).getDetails().getPublish_date());

        txtA_egilea.setText("");
        for(int i=0; i<liburuLista.get(unekoa).getDetails().getAuthors().length;i++){
            if(i==0){
                txtA_egilea.setText(liburuLista.get(unekoa).getDetails().getAuthors()[i].getName());
            }
            else{
                txtA_egilea.setText(txtA_egilea.getText()+"; "+liburuLista.get(unekoa).getDetails().getAuthors()[i].getName());
            }

        }
        irudiaKargatu();

    }

    private void irudiaKargatu(){
        String imagePath = Utils.lortuEzarpenak().getProperty("pathToImages")+liburuLista.get(unekoa).getThumbnail_url();
        try {
            mgvw.setImage(new Image(new FileInputStream(imagePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
