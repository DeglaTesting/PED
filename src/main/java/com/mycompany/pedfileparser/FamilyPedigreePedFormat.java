/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pedfileparser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sa841
 */
public class FamilyPedigreePedFormat {

    /**
     * Describe the data format of a pedigree in a ped file for the same family.
     * The family members are identified by the same familyId.
     */

    String familyId;
    List<IndividualPedigreePedFormat> familyPedigreePedFormatArray = new ArrayList();

    public FamilyPedigreePedFormat(String familyId) {
        this.familyId = familyId;
    }

    public List<IndividualPedigreePedFormat> getFamilyPedigreePedFormatArray() {
        return familyPedigreePedFormatArray;
    }

    public void setFamilyPedigreePedFormatArray(List<IndividualPedigreePedFormat> familyPedigreePedFormatArray) {
        this.familyPedigreePedFormatArray = familyPedigreePedFormatArray;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public void addNewFamilyMemeber(IndividualPedigreePedFormat newMemeber) {
        familyPedigreePedFormatArray.add(newMemeber);
    }

    public String getMemeberGendre(String id) {
        String gendre = "";
        for (int i = 0; i < this.getFamilyPedigreePedFormatArray().size(); i++) {
            if (((this.getFamilyPedigreePedFormatArray().get(i)).getIndividualId()).equals(id)) {
                gendre = (this.getFamilyPedigreePedFormatArray().get(i)).getSexe();
                break;
            }
        }
        return gendre;
    }
}
