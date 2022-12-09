package com.actimize.ir.main;

import com.actimize.ir.model.Employee;
import com.actimize.ir.utils.JSONUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestServiceCallingThroughURL {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setFirstName("Bhushan");
        employee.setLastName("Sharma");
        employee.setEmailId("bhushanSharma@gmail.com");
        // getEmployee();
        createEmployee(employee);
    }
    private static void getEmployee(){
        try {

            // URL url = new URL("http://localhost:8080/RESTfulExample/json/product/get");
            URL url = new URL("http://localhost:8084/api/v1/employees/1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    private static void createEmployee(Employee employee){
        try {

            // URL url = new URL("http://localhost:8080/RESTfulExample/json/product/post");
            URL url = new URL("http://localhost:8084/api/v1/employees");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            //String input = "{\"qty\":100,\"name\":\"iPad 4\"}";
            String input = JSONUtil.convertObjectToJson(employee);
            System.out.println("Input ::" + input);

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
