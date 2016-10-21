/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.phenotips;

import com.mycompany.pedfileparser.FamilyPedigreePedFormat;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author sa841
 */
public class PedigreeJsonFormat {
    /**
     * This class convert data from ped to json,while respecting the Phenotips description;
     *      sexe={male,female}
     *      phenotips == carrierStatus= {uncertain,affected,Unknown}...
     * 
     * @param familyPedigreePedFormat
     * @return jsonData
     */

    public String familyPedigreeJsonFormat(FamilyPedigreePedFormat familyPedigreePedFormat) {

        JSONArray familyJsonData = new JSONArray();

        for (int i = 0; i < familyPedigreePedFormat.getFamilyPedigreePedFormatArray().size(); i++) {
            JSONObject indivJsonData = new JSONObject();
            indivJsonData.put("id", (familyPedigreePedFormat.getFamilyPedigreePedFormatArray().get(i)).getIndividualId());

            if (((familyPedigreePedFormat.getFamilyPedigreePedFormatArray().get(i)).getSexe()).equals("1")) {
                indivJsonData.put("sex", "male");
            } else if (((familyPedigreePedFormat.getFamilyPedigreePedFormatArray().get(i)).getSexe()).equals("2")) {
                indivJsonData.put("sex", "female");
            }

            if (((familyPedigreePedFormat.getFamilyPedigreePedFormatArray().get(i)).getMaternalId()).equals("0") == false) {
                indivJsonData.put("mother", (familyPedigreePedFormat.getFamilyPedigreePedFormatArray().get(i)).getMaternalId());
            }

            if (((familyPedigreePedFormat.getFamilyPedigreePedFormatArray().get(i)).getPaternalId()).equals("0") == false) {
                indivJsonData.put("father", (familyPedigreePedFormat.getFamilyPedigreePedFormatArray().get(i)).getPaternalId());
            }

            switch ((familyPedigreePedFormat.getFamilyPedigreePedFormatArray().get(i)).getPhenotips()) {
                case "-9":
                    indivJsonData.put("carrierStatus", "uncertain");
                    break;
                case "1":
                    indivJsonData.put("carrierStatus", "Unknown");
                    break;
                case "2":
                    indivJsonData.put("carrierStatus", "affected");
                    break;
                default:
                    indivJsonData.put("carrierStatus", "Unknown");
            }
            familyJsonData.put(indivJsonData);

        }
        return familyJsonData.toString();
    }
}
