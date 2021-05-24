package com.example.SpringRepetition.model;

public enum Category {
    IT("it"),
    DEV_OPS("dev ops"),
    DS("data scientist");

    private String categoryName;

    Category (String categoryName){
         this.categoryName = categoryName;
    }

    private String getCategoryName(){
        return categoryName;
    }
}
