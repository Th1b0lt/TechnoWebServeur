package src.com.uca.entity;

public class PersonneEntity {
    private final String idPersonne;
    private String nom;
    private String prenom;
    private String numeroDeTelephone;

    public PersonneEntity(String id){
        this.idPersonne=id;
    }
    //Liste des getteurs
    public String getNom(){
        return this.nom;
    }
    public String getPrenom(){
        return this.prenom;
    }
    public String getNumeroDeTelephone(){
        return this.numeroDeTelephone;
    }
    public String getIdPersonne(){
        return this.idPersonne;
    }

    //Liste des setters

    public void setNom(String nom){
        this.nom=nom;
    }
    public void setPrenom(String prenom){
        this.prenom=prenom;
    }
    public void setNumeroDeTelephone(String numero){
        this.numeroDeTelephone=numero;
    }

}
