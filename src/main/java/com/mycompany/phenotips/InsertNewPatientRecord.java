/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.phenotips;

import com.sun.jersey.core.util.Base64;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 *
 * @author sa841
 */
public class InsertNewPatientRecord {
    /**
     * For each new pedigree to draw, a new patient will be created.
     * The patient will be created using the restful API provided by Phenotips.
     * 
     * @throws ProtocolException
     * @throws IOException 
     */

    public void sendPost() throws ProtocolException, IOException {

        try {
            String url = "http://192.168.1.63:8080/phenotips123/rest/patients";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            String userCredentials = "Admin:admin";
            Base64 b = new Base64();
            String basicAuth = "Basic " + new String(b.encode(userCredentials.getBytes()));
            con.setRequestProperty("Authorization", basicAuth);
            con.setUseCaches(true);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json; charset=utf8");
            con.setAllowUserInteraction(true);
            con.setRequestMethod("POST");
            OutputStream outputStream = con.getOutputStream();
            outputStream.flush();
            System.out.println("Creating new patient...");
            System.out.println("Response Code : " + con.getResponseCode());
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
