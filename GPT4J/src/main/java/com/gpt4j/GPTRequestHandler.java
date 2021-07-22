/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpt4j;

import com.gpt4j.request.GptRequest;
import com.gpt4j.output.GptOutput;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 *
 * @author zipCoder933
 */
public class GPTRequestHandler {

    private static String API_KEY;

    public GPTRequestHandler(String key) {
        this.API_KEY = key;
    }

    public enum Engine {
        DAVINCI,
        CURIE,
        BABBAGE,
        ADA
    };

    public GptOutput generate(GptRequest data, Engine engine) throws MalformedURLException, IOException, Exception {
        return generate(data, engine, "");
    }

    public GptOutput generate(GptRequest data, Engine engine, String illegalCharReplaceSeq) throws MalformedURLException, IOException, Exception {
//        String request = new Gson().toJson(data);
        if (illegalCharReplaceSeq.contains("\"")) {
            throw new Exception("Your illegal character string contains the illegal [\"] character.");
        }
        String request = data.getAsJsonString(illegalCharReplaceSeq);
//        System.out.println(request);

        String eng = "";
        switch (engine) {
            case DAVINCI:
                eng = "davanci";
                break;
            case CURIE:
                eng = "curie";
                break;
            case BABBAGE:
                eng = "babbage";
                break;
            case ADA:
                eng = "ada";
                break;
        }

        URL url = new URL("https://api.openai.com/v1/engines/" + eng + "/completions");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");
        http.setRequestProperty("Authorization", "Bearer " + API_KEY);

        byte[] out = request.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);

        if (http.getResponseCode() == 401) {
            throw new Exception("You are not authorized to make this request. (" + http.getResponseCode() + " " + http.getResponseMessage() + ")\nPossible solutions: Check if your API key is outdated or incorrect.");
        } else if (http.getResponseCode() != 200) {
            System.err.println("Http: " + http.getResponseCode() + " " + http.getResponseMessage());
        }

        InputStream inputStream = http.getInputStream();
        StringBuilder textBuilder = new StringBuilder();
        try ( Reader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        Gson gson = new Gson();
        GptOutput output = gson.fromJson(textBuilder.toString(), GptOutput.class);
        http.disconnect();
        return output;
    }
}
