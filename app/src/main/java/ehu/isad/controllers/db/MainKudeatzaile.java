package ehu.isad.controllers.db;

import ehu.isad.model.Author;
import ehu.isad.model.Details;
import ehu.isad.model.Liburua;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainKudeatzaile {
    private static final MainKudeatzaile instance = new MainKudeatzaile();

    public static MainKudeatzaile getInstance() {
        return instance;
    }

    private MainKudeatzaile() { }

    public List<Liburua> lortuDatuak(){
        String query = "SELECT isbn, egilea, data, izenburua, filename FROM liburuak";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<Liburua> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {
                String isbn = rs.getString("isbn");
                String egilea = rs.getString("egilea");
                String data = rs.getString("data");
                String izenburua = rs.getString("izenburua");
                String filename = rs.getString("filename");

                Author a = new Author(egilea);
                Author aList[] = new Author[1];
                aList[0]=a;
                Details d = new Details(izenburua,data,aList);
                Liburua l = new Liburua(isbn,d,filename);
                emaitza.add(l);

            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return emaitza;
    }

    public void datuakGorde(Liburua l) {
        String authors="";
        for(int i=0; i<l.getDetails().getAuthors().length;i++){
            if(i==0){
                authors = l.getDetails().getAuthors()[i].getName();
            }
            else{
                authors = authors+"; "+l.getDetails().getAuthors()[i].getName();
            }

        }
        String query = "INSERT INTO liburuak VALUES('" + l.getISBN() + "' , '" + authors + "' , '"+l.getDetails().getPublish_date()+"' , '"+l.getDetails().getTitle()+"' , '"+l.getThumbnail_url()+"')";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
    }

}
