/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.logger;

import static com.mycompany.logger.LogFileWriter.file;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 *
 * @author sa841
 */
public class FlushLogFile {
    /**
    * Responsible of the initialisation of the log file.
    * it tests the current date with the date of the information written in the log file; 
    * if fails the file will be flushed.
    *
    */
    public void flushLogFile() {

        try {
            FileWriter fr = new FileWriter(file.getAbsoluteFile(), false);
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            if (br.readLine() != null) {
                String firstLine = br.readLine();
                if ((getDate(firstLine)).equals(getCurrentDayDate()) == false) {
                    fr.flush();
                }
            }
            br.close();
            fr.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getDate(String firstLine) {
        String[] line = firstLine.split(" ");
        return line[0];
    }

    public String getCurrentDayDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date currentdate = new Date();
        String[] date = (dateFormat.format(currentdate)).split(" ");
        return date[0];
    }
}
