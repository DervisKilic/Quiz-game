package com.example.gualbertoscolari.grupp3;

public class CreateAndDelete {

    CreateCategoryActivity c1 = new CreateCategoryActivity();

    String cat = c1.getCategory();

    public void createProfile(){
        //Creates a profile and uses DBHelper to add it
        //to the Data Base
        //Takes a specific category as argument
    }

    public void createCategory(){
        //Creates a category and uses DBHelper to add it
        //to the Data Base
        //Takes a specific category as argument
    }

    public void createQuestion(){
        //Creates a question and uses DBHelper to add it
        //to the Data Base
        //Takes a specific category as argument
    }

    public void deleteProfile(){
        //Takes a specific profile as an argument and
        //deletes with DBHelper

    }

    public void deleteCategory(){
        //Takes a specific category as an argument and
        //deletes with DBHelper
    }

    public void deleteQuestion(){
        //Takes a specific question as an argument and
        //deletes with DBHelper
    }
}
