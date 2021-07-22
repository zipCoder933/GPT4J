/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpt4j.request;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zipCoder933
 */
public class GptRequest {

    public GptRequest() {
        prompt = "";
        top_p = 1;
        max_tokens = 64;
        temperature = 0.7;
        stop = new ArrayList<String>();
    }

    /**
     * @return the prompt
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * @param prompt the prompt to set
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    /**
     * @return the temperature
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * @return the max_tokens
     */
    public int getMax_tokens() {
        return max_tokens;
    }

    /**
     * @param max_tokens the max_tokens to set
     */
    public void setMax_tokens(int max_tokens) {
        this.max_tokens = max_tokens;
    }

    /**
     * @return the top_p
     */
    public double getTop_p() {
        return top_p;
    }

    /**
     * @param top_p the top_p to set
     */
    public void setTop_p(int top_p) {
        this.top_p = top_p;
    }

    /**
     * @return the best_of
     */
    public int getBest_of() {
        return best_of;
    }

    /**
     * @param best_of the best_of to set
     */
    public void setBest_of(int best_of) {
        this.best_of = best_of;
    }

    /**
     * @return the frequency_penalty
     */
    public double getFrequency_penalty() {
        return frequency_penalty;
    }

    /**
     * @param frequency_penalty the frequency_penalty to set
     */
    public void setFrequency_penalty(double frequency_penalty) {
        this.frequency_penalty = frequency_penalty;
    }

    /**
     * @return the presence_penalty
     */
    public double getPresence_penalty() {
        return presence_penalty;
    }

    /**
     * @param presence_penalty the presence_penalty to set
     */
    public void setPresence_penalty(double presence_penalty) {
        this.presence_penalty = presence_penalty;
    }

    /**
     * @return the stop
     */
    public List<String> getStop() {
        return stop;
    }

    /**
     * @param stop the stop to set
     */
    public void setStop(ArrayList<String> stop) {
        this.stop = stop;
    }

    /**
     * @param stop the stop sequence to add
     */
    public void addStopSequence(String stopSeq) {
        this.stop.add(stopSeq);
    }

    private String prompt;
    private double temperature;
    private int max_tokens;
    private double top_p;
    private int best_of;
    private double frequency_penalty;
    private double presence_penalty;
    private ArrayList<String> stop;

    public String getAsJsonString() {
        return getAsJsonString("");
    }

    public String getAsJsonString(String illegalCharReplaceSeq) {
        StringBuilder builder = new StringBuilder();

        if (this.getPrompt().contains("\"")) {
            System.err.println("WARNING: Your prompt contains the illegal [\"] character. It will be replaced by a [" + illegalCharReplaceSeq + "] sequence.");
        }
        builder.append("{\n");
        builder.append("  \"prompt\": \"" + this.getPrompt().replace("\"", illegalCharReplaceSeq).replace("\n", "\\n").replace("\r", "\\r") + "\",\n");
        builder.append("  \"temperature\": " + formatNumber(this.getTemperature()) + ",\n");
        builder.append("  \"max_tokens\": " + this.getMax_tokens() + ",\n");
        builder.append("  \"top_p\": " + formatNumber(this.getTop_p()) + ",\n");
        builder.append("  \"frequency_penalty\": " + formatNumber(this.getFrequency_penalty()) + ",\n");
        builder.append("  \"presence_penalty\": " + formatNumber(this.getPresence_penalty()));
        if (this.getBest_of() > 1) {
            builder.append(",\n  \"best_of\": " + formatNumber(this.getBest_of()));
        }
        if (this.getStop().size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("[\"");
            for (int i = 0; i < this.getStop().size(); i++) {
                sb.append(this.getStop().get(i).replace("\"", illegalCharReplaceSeq).replace("\n", "\\n").replace("\r", "\\r"));
                if (i < this.getStop().size() - 1) {
                    sb.append("\",\"");
                }
            }
            sb.append("\"]");

            builder.append(",\n  \"stop\": " + sb.toString());
        }

        builder.append("\n}");
        return builder.toString();
    }

    private String formatNumber(double num) {
        if (num == 0) {
            return "0";
        } else if (num == 1) {
            return "1";
        } else {
            return num + "";
        }
    }

}
