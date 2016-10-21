/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author sa841
 */


public class LogFileWriter {
    
 /**
  * Write logs in a file log "log.txt"
  * the file will be created if does not exist.
  * The initialisation of the log file is held by @FlushLogFile
  */

    public static File file = new File("log.txt");
  
    public static void log(String logMessage) {
        try {
            FileWriter  fw = new FileWriter(file.getAbsoluteFile(), true);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(fw);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            bw.write(dateFormat.format(date) + " : " + logMessage);
            bw.newLine();
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
