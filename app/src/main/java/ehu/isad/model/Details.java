package ehu.isad.model;

public class Details {
    private String title;
    private String publish_date;
    private Author[] authors;

    public Details(String title, String publish_date, Author[] authors) {
        this.title = title;
        this.publish_date = publish_date;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public Author[] getAuthors() {
        return authors;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

}
