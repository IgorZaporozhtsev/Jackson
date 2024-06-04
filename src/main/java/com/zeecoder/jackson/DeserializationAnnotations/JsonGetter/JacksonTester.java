package com.zeecoder.jackson.DeserializationAnnotations.JsonGetter;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
* This is useful when we want to map a POJOs field to a JSON field using a different name.
  output without Annotation:
     {
       "rollNo" : 1,
        "studentName" : "Mark"
     }
 output with Annotation:

 {
   "rollNo" : 1,
   "studentFirstName" : "Mark"
 }
 */
public class JacksonTester {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

            Student student = new Student("Mark", 1);
            String jsonString = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(student);
            System.out.println(jsonString);
    }
}
class Student {
    private String name;
    private int rollNo;
    public Student(String name, int rollNo){
        this.name = name;
        this.rollNo = rollNo;
    }
    @JsonGetter("studentFirstName")
    public String getStudentName(){
        return name;
    }
    public int getRollNo(){
        return rollNo;
    }
}
