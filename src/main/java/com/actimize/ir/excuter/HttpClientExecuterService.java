package com.actimize.ir.excuter;

import com.actimize.ir.model.Employee;
import com.actimize.ir.utils.DataUtils;
import com.actimize.ir.utils.JSONUtil;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class HttpClientExecuterService {
    private static final int numberOfThreads = 5;

    public static void main(String[] args) {
        //dummy text
        Instant start = Instant.now();
       ArrayList<Employee> employeeList ;
        employeeList = (ArrayList<Employee>) DataUtils.getListOfEmpployee();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        System.out.println("Main thread ::" + Thread.currentThread().getName());
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
         for(Employee employee : employeeList){
             HttpPutService httpPutServiceWorker = new HttpPutService(httpClient,employee);
             executor.execute(httpPutServiceWorker);
         }
        executor.shutdown();
        while (!executor.isTerminated()) {
            // empty body
        }
        System.out.println("\nFinished all threads");
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Total Time taken :: "+timeElapsed.getSeconds() );
         //getEmployee();
       // createEmployee(employee);
    }
    private static void getEmployee(){
        try {

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet getRequest = new HttpGet(
                    "http://localhost:8084/api/v1/employees/1");
            getRequest.addHeader("accept", "application/json");

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getCode() != HttpStatus.SC_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((((CloseableHttpResponse) response).getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            httpClient.close();

        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    private static void createEmployee(Employee employee){
        try {

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost postRequest = new HttpPost(
                    "http://localhost:8084/api/v1/employees");

           // StringEntity input = new StringEntity("{\"qty\":100,\"name\":\"iPad 4\"}");
            String inputJson = JSONUtil.convertObjectToJson(employee);
            System.out.println("Input ::" + inputJson);
            StringEntity stringEntity = new StringEntity(inputJson, ContentType.parse("application/json"));
            postRequest.setEntity(stringEntity);

            HttpResponse response = httpClient.execute(postRequest);

            if (response.getCode()!= 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((((CloseableHttpResponse) response).getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            httpClient.close();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
