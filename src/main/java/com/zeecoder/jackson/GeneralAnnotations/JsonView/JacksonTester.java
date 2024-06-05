package com.zeecoder.jackson.GeneralAnnotations.JsonView;

import java.io.IOException;
import java.text.ParseException;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 Summary
    @JsonView is used to control values to be serialized or not.

 output without Annotation:

    {
    "id" : 1,
    "name" : "Mark",
    "age" : 12
    }
 output with Annotation:
    {
    "id" : 1,
    "name" : "Mark"
    }


 */

public class JacksonTester {
    public static void main(String[] args) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        Student student = new Student(1, "Mark", 12);
        String jsonString = mapper
                .writerWithDefaultPrettyPrinter()
                .withView(Views.Public.class)
                .writeValueAsString(student);
        System.out.println(jsonString);
    }
}
class Student {
    @JsonView(Views.Public.class)
    public int id;
    @JsonView(Views.Public.class)
    public String name;
    @JsonView(Views.Internal.class)
    public int age;

    Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
class Views {
    static class Public {}
    static class Internal extends Public {}
}