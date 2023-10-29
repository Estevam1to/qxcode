package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

@Table(schema = "categoria")
@Entity
public class categoria {

    @Column(name = "id_categoria")
    private Long id;

    @Column(name = "titulo")
    String title;

    public categoria(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
