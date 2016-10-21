/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pedfileparser;

import com.mycompany.controllers.ValidateDataSet;
import com.mycompany.controllers.ValidateFormatPedFile;
import com.mycompany.phenotips.ExportToPhenotips;
import com.mycompany.phenotips.InsertNewPatientRecord;
import com.mycompany.phenotips.PedigreeJsonFormat;
import com.mycompany.phenotips.ViewPatientRecord;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sa841
 */
public class PedFileParser {

    /**
     * Read from the ped file(input file), validate its format and the dataSet
     * (@ValidateFormatPedFile for each line the the file && @validatePedDataSet
     * for each collected family) Draws pedigree for each family
     * (@drowingPedigrees)
     */
    List individualPedigreePedFormatArray = new ArrayList();
    List<FamilyPedigreePedFormat> familiesPedigreePedFormatArray = new ArrayList();

    public void pedFileReader() {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            int nCurrentLine = 0;
            br = new BufferedReader(new FileReader("PID20161004.ped"));
            ValidateFormatPedFile validateFormatPedFile = new ValidateFormatPedFile();
            System.out.println("Checking ped file format........");
            while ((sCurrentLine = br.readLine()) != null) {
                nCurrentLine++;
                individualPedigreePedFormatArray = validateFormatPedFile.validateFormatFile(sCurrentLine, nCurrentLine);
            }
            familiesPedigreePedFormatArray = validateFormatPedFile.collectFamilies(individualPedigreePedFormatArray);
            System.out.println("******* Initially, we have " + familiesPedigreePedFormatArray.size() + " families. *************");
            System.out.println("Checking ped dataSet........");
            for (int i = 0; i < familiesPedigreePedFormatArray.size(); i++) {
                ValidateDataSet validateDataSet = new ValidateDataSet(familiesPedigreePedFormatArray.get(i));
                if (validateDataSet.runValidater() == false) {
                    familiesPedigreePedFormatArray.remove(i);
                    i--;
                }
            }
            System.out.println("***** We are exporting " + familiesPedigreePedFormatArray.size() + " families. ****************");
            drowingPedigrees(familiesPedigreePedFormatArray);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void drowingPedigrees(List chechekFamiliesDataSet) throws Exception {
        String data = "";
        PedigreeJsonFormat pedigreeJsonFormat = new PedigreeJsonFormat();
        InsertNewPatientRecord insertNewPatientRecord = new InsertNewPatientRecord();
        ViewPatientRecord viewPatientRecord = new ViewPatientRecord();
        ExportToPhenotips ep = new ExportToPhenotips();
        WebDriver driver = ep.login();
        for (int i = 0; i < chechekFamiliesDataSet.size(); i++) {
            insertNewPatientRecord.sendPost();
            TimeUnit.SECONDS.sleep(2);
            String newPatientId = viewPatientRecord.getPatientId(viewPatientRecord.sendGet());
            data = pedigreeJsonFormat.familyPedigreeJsonFormat((FamilyPedigreePedFormat) chechekFamiliesDataSet.get(i));
            ep.exportPedigreeToPhenotips(driver, data, newPatientId);
            TimeUnit.SECONDS.sleep(2);
        }
        ep.logout(driver);
    }
}
