package com.javarush.demain;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(schema = "movle", name = "film_text")
public class FilmText  {
    @Id
    @Column(name="film_id")
    private Short id;

    @OneToOne
    @JoinColumn(name="film_id")
    private Film film;

    private String title;

    @Column(columnDefinition = "decription")
    @Type(type = "text")
    private String description;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
