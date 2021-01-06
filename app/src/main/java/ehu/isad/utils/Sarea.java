package ehu.isad.utils;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Sarea {
    public String readFromUrl(String isbn) throws IOException {
        String lerroa = " ";
        try {
            URL openLibrary = new URL("https://openlibrary.org/api/books?bibkeys=ISBN:"+ isbn + "&jscmd=details&format=json");
            URLConnection yc = openLibrary.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            lerroa = in.readLine();
            in.close();
        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        lerroa=zatitu(lerroa,isbn);
        System.out.println(lerroa);
        return lerroa;
    }

    private String zatitu(String lerroa, String isbn){
        String[] zatiak = lerroa.split("ISBN:" + isbn+ "\": ");
        lerroa = zatiak[1].substring(0, zatiak[1].length()-1);
        return lerroa;
    }

    public void irudiaSortu(String url, String hFitxategia) throws IOException{
        BufferedImage image;
        try{
            URL Url =new URL(url);
            image = ImageIO.read(Url);
            ImageIO.write(image, "jpg", new File(Utils.lortuEzarpenak().getProperty("pathToImages")+hFitxategia));
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
