package com.example.application.Data;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class HomeDetail {

    @NotBlank
    private String text;
    // @NotBlank
    // private List<String> selector;

    public String getText(){return text;}
    public void setText(String text){this.text = text;}
    // public List<String> getSelector(){
    //     return selector;
    // }
    // public void setSelector(List<String> selector){this.selector = selector;}
    
}
