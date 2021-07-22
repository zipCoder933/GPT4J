/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gpt4j.output;

import java.util.List;

/**
 *
 * @author zipCoder933
 */
public class GptOutput {

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the object
     */
    public String getObject() {
        return object;
    }

    /**
     * @return the created
     */
    public int getCreated() {
        return created;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @return the choices
     */
    public List<Choice> getChoices() {
        return choices;
    }
    private String id;
    private String object;
    private int created;
    private String model;
    private List<Choice> choices;
}
