/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pedfileparser;

import com.mycompany.logger.FlushLogFile;

/**
 *
 * @author sa841
 */
public class Runner {
    /*
    *   Main class.
    */
    
    public static void main(String[] args) {
        FlushLogFile FlushLogFile = new FlushLogFile();
        FlushLogFile.flushLogFile();
        PedFileParser pedFileReader = new PedFileParser();
        pedFileReader.pedFileReader();
    }  
}
