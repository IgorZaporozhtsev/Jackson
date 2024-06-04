package com.zeecoder.jackson.JsonAnyGetter;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The @JsonAnyGetter annotation allows a Java class to include dynamically determined key-value pairs in its JSON output,
 * merging these additional properties with the other defined properties of the class into the top-level JSON object.

 output without Annotation:
 {
     "iban" : "6518f3e2-c182-4190-a402-2095c9e02cc6",
     "RollNo" : "1",
     "Name" : "Mark"
 }
 output with Annotation:
 {
     "properties" : {
         "RollNo" : "1",
         "Name" : "Mark"
     },
     "iban" : "3a1a2c69-b379-4783-b8ea-ab217c67c720"
 }

 * */

public class JacksonTester {
    public static void main(String args[]){
        ObjectMapper mapper = new ObjectMapper();
        try{
            Student student = new Student(UUID.randomUUID());
            student.add("Name", "Mark");
            student.add("RollNo", "1");
            String jsonString = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(student);
            System.out.println(jsonString);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Student {
    private Map<String, String> properties;
    private UUID iban;
    public Student(UUID iban){
        properties = new HashMap<>();
        this.iban = iban;
    }
    //@JsonAnyGetter
    public Map<String, String> getProperties(){
        return properties;
    }
    public void add(String property, String value){
        properties.put(property, value);
    }

    public UUID getIban(){
        return iban;
    }
}