package com.zeecoder.jackson.GeneralAnnotations.JsonFormat;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**

 Summary
 @JsonFormat is used to specify format while serialization or de-serialization. It is mostly used with Date fields.
 need add lib jsr310 and JavaTimeModule(


 output without Annotation:
 {
 "id" : 1,
 "birthDate" : [ 1900, 2, 1 ]
 }

 output with Annotation:
 {
 "id" : 1,
 "birthDate" : "01-02-1900"
 }

 */

public class JacksonTester {
    public static void main(String[] args) throws IOException, ParseException {
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();


        LocalDate date = LocalDate.of(1900,2,1);

        Student student = new Student(1, date);
        String jsonString = mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(student);
        System.out.println(jsonString);
    }
}
class Student {
    public int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public LocalDate birthDate;
    Student(int id, LocalDate birthDate){
        this.id = id;
        this.birthDate = birthDate;
    }
}