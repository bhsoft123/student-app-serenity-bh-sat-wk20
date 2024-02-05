package com.studentapp.studentinfo;

import com.studentapp.constants.EndPoints;
import com.studentapp.model.StudentPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jay       every step is reusable method
 *                      //shift+f6 to rename all occurances
 */

public class StudentSteps {

    @Step ("Creating student with  firstname :{0}, lastname :{1},email: {2}, programme :{3}, courstlist : {4}")
    public ValidatableResponse createStudent(String firstName,String lastName,String email,
                                    String programme,List<String> courseList ){

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Content-Type","application/json")
                .when()
                .body(studentPojo)
                .post()
                .then().log().all(); // this is returning validatable response
    }
    @Step ("Getting the student information with firstname :{0}")
    public HashMap<String,Object> getStudentInfoByFirstName(String firstName){

        String s1 = "findAll{it.firstName == '";
        String s2 = "'}.get(0)";

        HashMap<String,Object> studentMap =  SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then().statusCode(200)
                .extract()
                .path(s1+firstName+s2); //extracting Data in HAShmap
        return studentMap;
    }

    @Step ("Updating student information with studentID : {0}, firstname : {1}, lastname : {2}, email : {3}, programme : {4}, courses : {5}")
    public ValidatableResponse updateStudent(int studentId,String firstName,String lastName,String email,
                                             String programme,List<String> courseList ){
        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Content-Type","application/json")
                .pathParam("studentID", studentId)
                .when()
                .body(studentPojo)
                .put(EndPoints.UPDATE_STUDENT_BY_ID)
                .then().log().all(); // this is returning validatable response
                                    // you can use iftestfails method
    }

    @Step("Delete student information with studentID : {0}")
    public ValidatableResponse deleteStudent(int studentId){
        return  SerenityRest.given().log().all()
                .pathParam("studentID", studentId)
                .when()
                .delete(EndPoints.DELETE_STUDENT_BY_ID)
                .then().statusCode(204);
    }
    @Step ("Getting student information with studentID : {0}")
    public ValidatableResponse getStudentId(int studentId){
        return SerenityRest.given().log().all()
                .pathParam("studentID", studentId)
                .when()
                .get(EndPoints.GET_SINGLE_STUDENT_BY_ID)
               .then().statusCode(404);
    }
    @Step ("Getting all students information")
    public ValidatableResponse getAllStudentinfo(){
        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then();
    }

    @Step ("Getting the student information with email :{0}")
    public HashMap<String,Object> getStudentInfoByEmail(String email){

        String s1 = "findAll{it.email == '";
        String s2 = "'}.get(0)";

        HashMap<String,Object> studentMap =  SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then().statusCode(200)
                .extract()
                .path(s1+email+s2); //extracting Data in HAShmap
        return studentMap;
    }
    }


