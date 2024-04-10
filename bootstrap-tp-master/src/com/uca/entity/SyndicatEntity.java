package src.com.uca.entity;

public class SyndicatEntity {
    private final int idSyndicat;
    private String name;
    private String adresse;
    private String personneReference;
    private int numeroDeTelephone;
    private String adresseEmail;

    public SyndicatEntity(int idSyndicat){
        this.idSyndicat=idSyndicat;
    }
    //Liste des getteur
    public String getName(){
        return this.name;
    }
    public String getAdresse(){
        return this.adresse;
    }

    public String getPersonneReference(){
        return this.personneReference;
    }

    public int getNumeroDeTelephone(){
        return this.numeroDeTelephone;
    }

    public String getAdresseEmail(){
        return this.adresseEmail;
    }

    //Liste des Setteur
    public void setName(String name){
        this.name=name;
    }

    public void setAdresse(String adresse){
        this.adresse=adresse;
    }

    public void setPersonneReference(String personne){
        this.personneReference=personne;
    }

    public void setNumeroDeTelephone(String numero){
        this.numeroDeTelephone=numero;
    }

    public void setAdresseEmail(String email){
        this.adresseEmail=email;
    }

}
