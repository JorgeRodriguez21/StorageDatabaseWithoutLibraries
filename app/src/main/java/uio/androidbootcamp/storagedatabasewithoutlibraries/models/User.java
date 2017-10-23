package uio.androidbootcamp.storagedatabasewithoutlibraries.models;

/**
 * Created by Jorge on 09/07/2017.
 */

public class User {

    private long idUser;
    private String name;

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return
                idUser + " "+ name;
    }
}
