package src.com.uca.entity;

public class PersonneEntity {
    private final int idPersonne;
    private String nom;
    private String prenom;
    private int numeroDeTelephone;

    public PersonneEntity(int id){
        this.idPersonne=id;
    }
    //Liste des getteurs
    public String getNom(){
        return this.nom
    }
    public String getPrenom(){
        return this.prenom;
    }
    public int getNumeroDeTelephone(){
        return this.numeroDeTelephone;
    }
    public int getIdPersonne(){
        return this.idPersonne;
    }

    //Liste des setters

    public void setNom(String nom){
        this.nom=nom;
    }
    public void setPrenom(String prenom){
        this.prenom=prenom;
    }
    public void setNumeroDeTelephone(int numero){
        this.numeroDeTelephone=numero;
    }

}
