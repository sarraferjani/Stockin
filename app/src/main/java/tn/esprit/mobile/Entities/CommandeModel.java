package tn.esprit.mobile.Entities;

public class CommandeModel {
    private String codeCommande;
    private String commandeTitle;
    private String qte;
    private String adresse;
    private String total;

    public String getCodeCommande() {
        return codeCommande;
    }

    public void setCodeCommande(String codeCommande) {
        this.codeCommande = codeCommande;
    }

    public String getCommandeTitle() {
        return commandeTitle;
    }

    public void setCommandeTitle(String commandeTitle) {
        this.commandeTitle = commandeTitle;
    }

    public String getQte() {
        return qte;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
