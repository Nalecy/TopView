package com.Nalecy.www.po;

import java.text.DecimalFormat;

public class Hotel {
    private Integer id;
    private String name;
    private Integer star;
    private Double score;
    private Integer numOfScore;
    private String description;

    public Hotel() {
    }

    public Hotel(Integer id, String name, Integer star, Double score, Integer numOfScore, String description) {
        this.id = id;
        this.name = name;
        this.star = star;
        this.score = score;
        this.numOfScore = numOfScore;
        this.description = description;
    }

    public void updateScore(Integer score){
        this.score = (this.score * numOfScore + score)/(++numOfScore);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name+"，");
        for(int i = 0; i<star ; i++) sb.append("★");
        sb.append("级酒店，综合评分："+getScore().toString()+"，总评分人数"+numOfScore+"，详细介绍： "+description);
        return sb.toString();
    }

    public Integer getNumOfScore() { return numOfScore; }
    public void setNumOfScore(Integer numOfScore) {this.numOfScore = numOfScore; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getStar() { return star; }
    public void setStar(Integer star) { this.star = star; }
    public Double getScore() {
        Double s = score;
        new DecimalFormat("#.00").format(s);
        return s;
    }
    public void setScore(Double score) {
        this.score = score;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
