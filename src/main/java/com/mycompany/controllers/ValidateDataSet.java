/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controllers;

import com.mycompany.logger.LogFileWriter;
import com.mycompany.pedfileparser.FamilyPedigreePedFormat;
import com.mycompany.pedfileparser.IndividualPedigreePedFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sa841
 */
public class ValidateDataSet {

    FamilyPedigreePedFormat familyPedigreePedFormat;
    List<String> motherIdArray = new ArrayList();
    List<String> fatherIdArray = new ArrayList();
    List<String> indivIdArray = new ArrayList();
    boolean valide;

    public ValidateDataSet(FamilyPedigreePedFormat familyPedigreePedFormat) {
        this.familyPedigreePedFormat = familyPedigreePedFormat;
        this.valide = true;
    }

    public boolean runValidater() {
        this.collectIds();
        return (this.checkMotherGender() && this.checkFatherGender());
    }

    public void collectIds() {
        List<IndividualPedigreePedFormat> familyMembers = this.familyPedigreePedFormat.getFamilyPedigreePedFormatArray();
        for (int i = 0; i < familyMembers.size(); i++) {
            if (doesNotContains(indivIdArray, familyMembers.get(i).getIndividualId())) {
                indivIdArray.add(familyMembers.get(i).getIndividualId());
            }
            if (doesNotContains(motherIdArray, familyMembers.get(i).getMaternalId())) {
                motherIdArray.add(familyMembers.get(i).getMaternalId());
            }
            if (doesNotContains(fatherIdArray, familyMembers.get(i).getPaternalId())) {
                fatherIdArray.add(familyMembers.get(i).getPaternalId());
            }
        }
    }

    public boolean doesNotContains(List array, String string) {
        return (!(array.contains(string)));
    }

    public boolean checkMotherGender() {
        boolean check = true;
        for (int i = 0; i < motherIdArray.size(); i++) {
            if (!(motherIdArray.get(i)).equals("0")) {
                if (!(this.familyPedigreePedFormat.getMemeberGendre(motherIdArray.get(i)).equals("2"))) {
                    LogFileWriter.log("Check the family " + this.familyPedigreePedFormat.getFamilyId() + "; Mother gender should be \"female\".");
                    check = false;
                    break;
                }
            }
        }
        return check;
    }

    public boolean checkFatherGender() {
        boolean check = true;
        for (int i = 0; i < fatherIdArray.size(); i++) {
            if (!(fatherIdArray.get(i)).equals("0")) {
                if (!(this.familyPedigreePedFormat.getMemeberGendre(fatherIdArray.get(i)).equals("1"))) {
                    LogFileWriter.log("Check the family " + this.familyPedigreePedFormat.getFamilyId() + "; Father gender should be \"male\".");
                    check = false;
                    break;
                }
            }
        }
        return check;
    }
}
