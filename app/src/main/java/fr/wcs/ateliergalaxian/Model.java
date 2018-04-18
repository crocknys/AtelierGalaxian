package fr.wcs.ateliergalaxian;

/**
 * Created by wilder on 18/04/18.
 */

public class Model {

    String joueur;
    String mdp;
    String score;

    public Model (String joueur, String mdp, String score) {
        this.joueur = joueur;
        this.mdp = mdp;
        this.score = score;
    }

    public Model () {

    }


    public String getJoueur() {
        return joueur;
    }

    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
