/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pedfileparser;

/**
 *
 * @author sa841
 */
public class IndividualPedigreePedFormat {
    
    /**
     * Describe the data format of a pedigree in a ped file.
     * Encapsulate each line in the ped file in @IndividualPedigreePedFormat object.
     */

    String familyId;
    String individualId;
    String paternalId;
    String maternalId;
    String sexe;
    String phenotips;

    public IndividualPedigreePedFormat(String FamilyID, String IndividualID, String PaternalID, String MaternalID, String sexe, String phenotips) {
        this.familyId = FamilyID;
        this.individualId = IndividualID;
        this.paternalId = PaternalID;
        this.maternalId = MaternalID;
        this.sexe = sexe;
        this.phenotips = phenotips;
    }

    public IndividualPedigreePedFormat() {
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public String getIndividualId() {
        return individualId;
    }

    public void setIndividualId(String individualId) {
        this.individualId = individualId;
    }

    public String getPaternalId() {
        return paternalId;
    }

    public void setPaternalId(String paternalId) {
        this.paternalId = paternalId;
    }

    public String getMaternalId() {
        return maternalId;
    }

    public void setMaternalId(String maternalId) {
        this.maternalId = maternalId;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getPhenotips() {
        return phenotips;
    }

    public void setPhenotips(String phenotips) {
        this.phenotips = phenotips;
    }
}
