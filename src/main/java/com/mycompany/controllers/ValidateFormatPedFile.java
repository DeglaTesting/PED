/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controllers;

import com.mycompany.pedfileparser.*;
import com.mycompany.logger.LogFileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sa841
 */
public class ValidateFormatPedFile {
    /**
     * First, @validateFormatFile, it will check the data format in the ped file:
     * We must have 6 columns separate by ("\t")
     * Then, @collectFamilies, it will collect members belonging at the same family
     */

    String sPedLine;
    int nPedLine;
    List individualPedigreePedFormatArray = new ArrayList();
    List familiesPedigreePedFormatArray = new ArrayList();
    List famliyIdList = new ArrayList();

    public List validateFormatFile(String sPedLine, int nPedLine) {
        try {
            String[] pedColumns = sPedLine.split("\t");
            if (pedColumns.length != 6) {
                      LogFileWriter.log("Bad Formatted Line, Line number " + nPedLine);
            } else {
                individualPedigreePedFormatArray.add(collectIndividuals(pedColumns));
            }
        } catch (Exception ex) {
            Logger.getLogger(ValidateFormatPedFile.class.getName()).log(Level.SEVERE, null, ex);
        }
          return individualPedigreePedFormatArray;
    }

    public IndividualPedigreePedFormat collectIndividuals(String[] pedColumns) {
        IndividualPedigreePedFormat individualPedigreePedFormat = new IndividualPedigreePedFormat(pedColumns[0], pedColumns[1], pedColumns[2], pedColumns[3], pedColumns[4], pedColumns[5]);
        //individualPedigreePedFormat.printIndividualPedInfo();
        return individualPedigreePedFormat;
    }

    public List collectFamilies(List individualPedigreePedFormatArray) {
        intialization(individualPedigreePedFormatArray);
        for (int i = 1; i < individualPedigreePedFormatArray.size(); i++) {
            if (familyIdAlreadyStocked(((IndividualPedigreePedFormat) (individualPedigreePedFormatArray.get(i))).getFamilyId(), famliyIdList) == false) {
                famliyIdList.add(((IndividualPedigreePedFormat) (individualPedigreePedFormatArray.get(i))).getFamilyId());
                FamilyPedigreePedFormat familyPedigreePedFormat = new FamilyPedigreePedFormat(((IndividualPedigreePedFormat) (individualPedigreePedFormatArray.get(i))).getFamilyId());
                for (int j = i + 1; j < individualPedigreePedFormatArray.size(); j++) {
                    if ((((IndividualPedigreePedFormat) (individualPedigreePedFormatArray.get(i))).getFamilyId()).equals(((IndividualPedigreePedFormat) (individualPedigreePedFormatArray.get(j))).getFamilyId())) {
                        familyPedigreePedFormat.addNewFamilyMemeber((IndividualPedigreePedFormat) individualPedigreePedFormatArray.get(j));
                    }
                }
                familiesPedigreePedFormatArray.add(familyPedigreePedFormat);
            }
        }
        return familiesPedigreePedFormatArray;
    }

    public boolean familyIdAlreadyStocked(String familyId, List famliyIdList) {
        if (famliyIdList.contains(familyId)) {
            return true;
        } else {
            return false;
        }
    }

    public void intialization(List individualPedigreePedFormatArray) {
   
        famliyIdList.add(((IndividualPedigreePedFormat) (individualPedigreePedFormatArray.get(0))).getFamilyId());
        FamilyPedigreePedFormat familyPedigreePedFormat = new FamilyPedigreePedFormat((((IndividualPedigreePedFormat) individualPedigreePedFormatArray.get(0))).getFamilyId());
        familyPedigreePedFormat.addNewFamilyMemeber((IndividualPedigreePedFormat)(individualPedigreePedFormatArray.get(0)));
        for (int j = 1; j < individualPedigreePedFormatArray.size(); j++) {
            if ((((IndividualPedigreePedFormat) (individualPedigreePedFormatArray.get(0))).getFamilyId()).equals(((IndividualPedigreePedFormat) (individualPedigreePedFormatArray.get(j))).getFamilyId())) {
                familyPedigreePedFormat.addNewFamilyMemeber((IndividualPedigreePedFormat) individualPedigreePedFormatArray.get(j));
            }
        }
        familiesPedigreePedFormatArray.add(familyPedigreePedFormat);
    }
    public boolean compareFamilyIDs(IndividualPedigreePedFormat indiv1, IndividualPedigreePedFormat indiv2) {
        if ((indiv1.getFamilyId()).equals(indiv2.getFamilyId())) {
            return true;
        } else {
            return false;
        }
    }
}
