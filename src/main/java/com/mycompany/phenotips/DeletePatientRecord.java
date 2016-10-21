package com.mycompany.phenotips;

import com.sun.jersey.core.util.Base64;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author sa841
 */
public class DeletePatientRecord {
    /**
     * If Phenotips failed to draw the pedigree for the patient, 
     * the new patient created will be deleted.
     * The patient will be deleted using the restful API provided by Phenotips.
     * @param patientId
     * @throws Exception 
     */

    public void sendDelete(String patientId) throws Exception {

        String url = "http://192.168.1.63:8080/phenotips123/rest/patients/"+patientId;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        String userCredentials = "Admin:admin";
        Base64 b = new Base64();
        String basicAuth = "Basic " + new String(b.encode(userCredentials.getBytes()));
        con.setRequestProperty("Authorization", basicAuth);
        con.setUseCaches(true);
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setRequestMethod("DELETE");
        con.setAllowUserInteraction(true);
        con.setRequestProperty("Content-Type", "application/json; charset=utf8");
        System.out.println("Deleting patient..." + con.getResponseCode());
        //should be ==204 (if success)
    }

}
