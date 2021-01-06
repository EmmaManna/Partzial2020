package ehu.isad.model;

import com.google.gson.Gson;
import ehu.isad.utils.Sarea;

import java.io.IOException;

public class Liburua {
    private String ISBN;
    private Details details;
    private String thumbnail_url;

    public Liburua(String ISBN, Details details, String thumbnail_url) {
        this.ISBN = ISBN;
        this.details = details;
        this.thumbnail_url=thumbnail_url;
    }

    public Liburua() {
    }

    public String getISBN() {
        return ISBN;
    }

    public Details getDetails() {
        return details;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public Liburua datuakLortu(String isbn) throws IOException {
        Sarea s = new Sarea();
        String lerroa = s.readFromUrl(isbn);
        Gson gson = new Gson();
        Liburua l = gson.fromJson(lerroa, Liburua.class);
        return l;
    }
}
