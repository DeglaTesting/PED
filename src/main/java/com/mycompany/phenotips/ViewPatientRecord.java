/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.phenotips;

import com.sun.jersey.core.util.Base64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author sa841
 */
public class ViewPatientRecord {
    /**
     * For each new patient created, we have to identify its id(ids are generate automatically by Phenotips).
     * Ids help us to export pedigrees to the specific patient.
     * 
     * @return patientId
     * @throws Exception 
     */

    public String sendGet() throws Exception {

        String url = "http://192.168.1.63:8080/phenotips123/rest/patients/?orderFiled=id&number=1&order=desc";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        String userCredentials = "Admin:admin";
        Base64 b = new Base64();
        String basicAuth = "Basic " + new String(b.encode(userCredentials.getBytes()));
        con.setRequestProperty("Authorization", basicAuth);
        con.setUseCaches(true);
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setRequestMethod("GET");
        con.setAllowUserInteraction(true);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public String getPatientId(String getResponse) {
        String id = "";
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(getResponse)));
            NodeList patientsNodes = doc.getElementsByTagName("patients");
            Element idElement = (Element) patientsNodes.item(0);
            id = idElement.getElementsByTagName("id").item(0).getTextContent();
            System.out.println("Exporting data to "+ id);
        } catch (ParserConfigurationException | IOException | SAXException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }
}
