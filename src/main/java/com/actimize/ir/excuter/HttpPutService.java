package com.actimize.ir.excuter;

import com.actimize.ir.model.Employee;
import com.actimize.ir.utils.JSONUtil;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class HttpPutService implements  Runnable{

    private  CloseableHttpClient httpClient;
    private  Employee employee;

    public HttpPutService(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }
    public HttpPutService(CloseableHttpClient httpClient,Employee employee) {
        this.httpClient = httpClient;
        this.employee = employee;
    }

    @Override
    public void run() {
        createEmployee(employee);
        System.out.println("child thread ::" + Thread.currentThread().getName());
    }

    private void createEmployee(Employee employee){
        try {
            if(httpClient!=null){
                httpClient = HttpClientBuilder.create().build();
            }
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

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
