package Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "oceny", schema = "school", catalog = "")
public class OcenyEntity {
    private int id;
    private String imię;
    private String nazwisko;
    private String przedmiot;
    private int ocena;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "imię", nullable = false, length = 16)
    public String getImię() {
        return imię;
    }

    public void setImię(String imię) {
        this.imię = imię;
    }

    @Basic
    @Column(name = "nazwisko", nullable = false, length = 16)
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Basic
    @Column(name = "przedmiot", nullable = false, length = 16)
    public String getPrzedmiot() {
        return przedmiot;
    }

    public void setPrzedmiot(String przedmiot) {
        this.przedmiot = przedmiot;
    }

    @Basic
    @Column(name = "ocena", nullable = false)
    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OcenyEntity that = (OcenyEntity) o;
        return id == that.id &&
                ocena == that.ocena &&
                Objects.equals(imię, that.imię) &&
                Objects.equals(nazwisko, that.nazwisko) &&
                Objects.equals(przedmiot, that.przedmiot);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, imię, nazwisko, przedmiot, ocena);
    }
}
