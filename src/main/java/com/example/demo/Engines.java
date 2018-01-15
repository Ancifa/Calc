package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by i on 13.11.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Engines {

    private ArrayList<ArrayList<String>> data;
    private ArrayList<String> columns;

    public ArrayList<ArrayList<String>> getData() {
        return data;
    }

    public ArrayList<String> getColumns() {
        return columns;
    }

    private class DataValues {

        private Integer id;
        private String name;
        private String title;

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getTitle() {
            return title;
        }
    }

}
