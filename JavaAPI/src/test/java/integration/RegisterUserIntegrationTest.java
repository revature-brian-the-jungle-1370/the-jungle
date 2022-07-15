package integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.json.JSONObject;
import org.testng.Assert;

public class RegisterUserIntegrationTest {  
    @BeforeClass
    public void setUp() {
    }

    @Test
    public void testGetRegisterSuccess() {
        try {
            URL url = new URL("http://ec2-52-200-53-62.compute-1.amazonaws.com:8080/user/registration");
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestProperty("Accept", "application/json");
            httpConn.setDoOutput(true);
            JSONObject user = new JSONObject();
            user.put("userId", 0);
            user.put("firstName", "Joe");
            user.put("lastName", "John");
            user.put("email", "joehn@gmail.com");
            user.put("username", "MyNameisJoe");
            user.put("passcode", "PasscodeIsTooStrong");
            user.put("userAbout", "Name is Joe John. Nice to meet you.");
            user.put("userBirthdate", new Long(742892400000L));
            user.put("imageFormat", "imagesrc");

            OutputStreamWriter wr = new OutputStreamWriter(httpConn.getOutputStream());
            wr.write(user.toString());
            wr.flush();
            wr.close();
            wr.close();
            httpConn.connect();
            
            int code = httpConn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String reponseJson;
            while ((reponseJson = br.readLine()) != null) {
                sb.append(reponseJson);
            }
            br.close();

            Assert.assertEquals(code, 201);
            Assert.assertTrue(sb.toString().contains(user.get("email").toString()));

            
        } catch (Exception e) {
            //TODO: handle exception
        }                
    }

    @Test
    public void testRegisterFailureNullBirthdate() {
        try {
            URL url = new URL("http://ec2-52-200-53-62.compute-1.amazonaws.com:8080/user/registration");
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestProperty("Accept", "application/json");
            httpConn.setDoOutput(true);
            JSONObject user = new JSONObject();
            user.put("userId", 0);
            user.put("firstName", "Joe");
            user.put("lastName", "John");
            user.put("email", "joehn@gmail.com");
            user.put("username", "MyNameisJoe");
            user.put("passcode", "PasscodeIsTooStrong");
            user.put("userAbout", "Name is Joe John. Nice to meet you.");
            user.put("userBirthdate", JSONObject.NULL);
            user.put("imageFormat", "imagesrc");

            OutputStreamWriter wr = new OutputStreamWriter(httpConn.getOutputStream());
            wr.write(user.toString());
            wr.flush();
            wr.close();
            wr.close();
            httpConn.connect();
            
            int code = httpConn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String reponseJson;
            while ((reponseJson = br.readLine()) != null) {
                sb.append(reponseJson);
            }
            br.close();

            Assert.assertEquals(code, 400);
            Assert.assertTrue(sb.toString().contains("Please fill in the blanks"));

            
        } catch (Exception e) {
            //TODO: handle exception
        }                
    }

    @Test
    public void testRegisterFailureSpaceInUsername() {
        try {
            URL url = new URL("http://ec2-52-200-53-62.compute-1.amazonaws.com:8080/user/registration");
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestProperty("Accept", "application/json");
            httpConn.setDoOutput(true);
            JSONObject user = new JSONObject();
            user.put("userId", 0);
            user.put("firstName", "Joe");
            user.put("lastName", "John");
            user.put("email", "joehn@gmail.com");
            user.put("username", "My Name is Joe");
            user.put("passcode", "PasscodeIsTooStrong");
            user.put("userAbout", "Name is Joe John. Nice to meet you.");
            user.put("userBirthdate", new Long(742892400000L));
            user.put("imageFormat", "imagesrc");

            OutputStreamWriter wr = new OutputStreamWriter(httpConn.getOutputStream());
            wr.write(user.toString());
            wr.flush();
            wr.close();
            wr.close();
            httpConn.connect();
            
            int code = httpConn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String reponseJson;
            while ((reponseJson = br.readLine()) != null) {
                sb.append(reponseJson);
            }
            br.close();

            Assert.assertEquals(code, 400);
            Assert.assertTrue(sb.toString().contains("No spaces allowed in username or password"));

            
        } catch (Exception e) {
            //TODO: handle exception
        }                
    }

    @Test
    public void testRegisterFailureSpaceInPassword() {
        try {
            URL url = new URL("http://ec2-52-200-53-62.compute-1.amazonaws.com:8080/user/registration");
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestProperty("Accept", "application/json");
            httpConn.setDoOutput(true);
            JSONObject user = new JSONObject();
            user.put("userId", 0);
            user.put("firstName", "Joe");
            user.put("lastName", "John");
            user.put("email", "joehn@gmail.com");
            user.put("username", "MyNameisJoe");
            user.put("passcode", "Passcode Is Too Strong");
            user.put("userAbout", "Name is Joe John. Nice to meet you.");
            user.put("userBirthdate", new Long(742892400000L));
            user.put("imageFormat", "imagesrc");

            OutputStreamWriter wr = new OutputStreamWriter(httpConn.getOutputStream());
            wr.write(user.toString());
            wr.flush();
            wr.close();
            wr.close();
            httpConn.connect();
            
            int code = httpConn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String reponseJson;
            while ((reponseJson = br.readLine()) != null) {
                sb.append(reponseJson);
            }
            br.close();

            Assert.assertEquals(code, 400);
            Assert.assertTrue(sb.toString().contains("No spaces allowed in username or password"));

            
        } catch (Exception e) {
            //TODO: handle exception
        }                
    }

    @Test
    public void testRegisterFailureEmptyUsername() {
        try {
            URL url = new URL("http://ec2-52-200-53-62.compute-1.amazonaws.com:8080/user/registration");
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestProperty("Accept", "application/json");
            httpConn.setDoOutput(true);
            JSONObject user = new JSONObject();
            user.put("userId", 0);
            user.put("firstName", "Joe");
            user.put("lastName", "John");
            user.put("email", "joehn@gmail.com");
            user.put("username", JSONObject.NULL);
            user.put("passcode", "PasscodeIsTooStrong");
            user.put("userAbout", "Name is Joe John. Nice to meet you.");
            user.put("userBirthdate", new Long(742892400000L));
            user.put("imageFormat", "imagesrc");

            OutputStreamWriter wr = new OutputStreamWriter(httpConn.getOutputStream());
            wr.write(user.toString());
            wr.flush();
            wr.close();
            wr.close();
            httpConn.connect();
            
            int code = httpConn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String reponseJson;
            while ((reponseJson = br.readLine()) != null) {
                sb.append(reponseJson);
            }
            br.close();

            Assert.assertEquals(code, 400);
            Assert.assertTrue(sb.toString().contains("Please fill in the blanks"));

            
        } catch (Exception e) {
            //TODO: handle exception
        }                
    }
}


