package src.com.uca.entity;

public class AppartementEntity {
    private final int idAppartement;
    private int etage;
    private int superficie;
    private ArrayList<PersonneEntity> personneLiee;
    private Boolean estLoue;

    public AppartementEntity(int id){
        this.idAppartement=id;
        personneLiee= new ArrayList<PersonneEntity>;
    }

    //Liste des getters
    public int getIdAppartement(){
        return this.idAppartement;
    }

    public int getEtage(){
        return this.etage;
    }

    public int getSuperficie(){
        return this.superficie;
    }
    public ArrayList<PersonneEntity> getPersonneLiee(){
        return this.personneLiee;
    }
    public Boolean getEstLoue(){
        return this.estLoue;
    }
    //Liste des setters
    public void setEtage(int etage){
        this.etage=etage;
    }
    public void setSuperficie(int superficie){
        this.superficie=superficie;
    }
    public  void setEstLoue(Boolean estLoue){
        this.estLoue=estLoue;
    }

    //Ajout et suppression de personneLiee
    public void ajoutePersonneLiee(PersonneEntity personne){
        if (! this.personneLiee.contains(personne))
            this.personneLiee.add(personne);
    }
    public void supprimePersonneLiee(PersonneEntity personne){
        this.personneLiee.remove(personne);
    }


}
