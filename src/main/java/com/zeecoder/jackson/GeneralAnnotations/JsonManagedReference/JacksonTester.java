package com.zeecoder.jackson.GeneralAnnotations.JsonManagedReference;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 Summary
    JsonManagedReferences and JsonBackReferences are used to display objects with parent child relationship.
    JsonManagedReferences is used to refer to parent object and @JsonBackReferences is used to mark child objects.

 output without Annotation:
    infinite recursion problem when serializing the Book object to JSON.
    This happens because the Book object contains a reference to a Student object, which in turn has a list of Book objects

    com.fasterxml.jackson.databind.JsonMappingException: Document nesting depth (1002) exceeds the maximum allowed

 output with Annotation:
 {
 "id" : 1,
 "name" : "Learn HTML",
 "owner" : {
 "rollNo" : 1,
 "name" : "Mark"
 }
 }


 */
public class JacksonTester {
    public static void main(String[] args) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        Student student = new Student(1, "Mark");
        Book book1 = new Book(1,"Learn HTML", student);
        Book book2 = new Book(1,"Learn JAVA", student);

        student.addBook(book1);
        student.addBook(book2);

        String jsonString = mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(book1);
        System.out.println(jsonString);
    }
}
class Student {
    public int rollNo;
    public String name;

    @JsonBackReference
    public List<Book> books;

    Student(int rollNo, String name){
        this.rollNo = rollNo;
        this.name = name;
        this.books = new ArrayList<Book>();
    }
    public void addBook(Book book){
        books.add(book);
    }
}
class Book {
    public int id;
    public String name;

    Book(int id, String name, Student owner){
        this.id = id;
        this.name = name;
        this.owner = owner;
    }
    @JsonManagedReference
    public Student owner;
}