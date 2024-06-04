package com.zeecoder.jackson.GeneralAnnotations;

import java.io.IOException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**

 Summary
     Without @JsonProperty: Jackson cannot map the JSON property id to the id field in the Student class
     if the getter and setter methods are not named according to JavaBean naming conventions (getId() and setId()).
     This results in the id field not being set correctly during deserialization.

     With @JsonProperty: By annotating the getter and setter methods with @JsonProperty("id"),
     you explicitly tell Jackson to map the JSON property id to the corresponding Java field,
     regardless of the method names. This ensures correct deserialization and serialization.

 output without Annotation:
    com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "id"
 output with Annotation:
 "1"

 */
public class JacksonTester {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"id\" : 1}";
        Student student = mapper.readerFor(Student.class).readValue(json);
        System.out.println(student.getTheId());
    }
}
class Student {
    private int id;
    Student(){}
    Student(int id){
        this.id = id;
    }
    @JsonProperty("id")
    public int getTheId() {
        return id;
    }
    @JsonProperty("id")
    public void setTheId(int id) {
        this.id = id;
    }
}