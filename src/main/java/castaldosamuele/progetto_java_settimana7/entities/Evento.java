package castaldosamuele.progetto_java_settimana7.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue
    private long id;
    private String titolo;
    private String descrizione;
    private LocalDate date;
    private String luogo;
    private int post;
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    public Evento() {}

    public Evento(String titolo, String descrizione, LocalDate date, String luogo, int post, Utente utente) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.date = date;
        this.luogo = luogo;
        this.post = post;
        this.utente = utente;
    }

    public long getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", date=" + date +
                ", luogo='" + luogo + '\'' +
                ", post=" + post +
                ", utente=" + utente +
                '}';
    }
}
