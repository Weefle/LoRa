package com.company;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);
        server.createContext("/", new CustomHandler());
        server.start();
    }
}

class CustomHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) {

        Object obj = JSONValue.parse(new InputStreamReader(exchange.getRequestBody()));

        JSONObject jsonObject = (JSONObject) obj;

        JSONObject payload = (JSONObject) jsonObject.get("payload_fields");

        System.out.println(payload.toJSONString());

    }

}