package seniorcare.crudseniorcare;

public class Lingua {
    private int id_lingua;
    private String lingua;

    public int getId_lingua() {
        return id_lingua;
    }

    public void setId_lingua(int id_lingua) {
        this.id_lingua = id_lingua;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    @Override
    public String toString() {
        return "Lingua{" +
                "id_lingua=" + id_lingua +
                ", lingua='" + lingua + '\'' +
                '}';
    }
}
