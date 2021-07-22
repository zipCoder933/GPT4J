/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpt4j.output;

/**
 *
 * @author zipCoder933
 */
public class Choice{

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @return the logprobs
     */
    public Object getLogprobs() {
        return logprobs;
    }

    /**
     * @return the finish_reason
     */
    public String getFinish_reason() {
        return finish_reason;
    }
    private String text;
    private int index;
    private Object logprobs;
    private String finish_reason;
}