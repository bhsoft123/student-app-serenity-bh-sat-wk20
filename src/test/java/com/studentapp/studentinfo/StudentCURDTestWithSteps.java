package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

/**
 * Created by Jay
 */
@RunWith(SerenityRunner.class)
public class StudentCURDTestWithSteps extends TestBase {

    static String firstName = TestUtils.getRandomValue()+ "PrimeUser";
    static String lastName = TestUtils.getRandomValue()+ "PrimeUser";
    static String programme = "API Testing";
    static String email = TestUtils.getRandomValue() +"xyz@gmail.com";
    static int studentId;

    @Steps
    StudentSteps steps; // object creation in serenity


    @Title("This will create a new student")
    @Test
    public void test001(){
        List<String> courseList = new ArrayList<>();
        courseList.add("Java");
        courseList.add("Rest Assured");
//        StudentPojo studentPojo = new StudentPojo();
//        studentPojo.setFirstName(firstName);
//        studentPojo.setLastName(lastName);
//        studentPojo.setEmail(email);
//        studentPojo.setProgramme(programme);
//        studentPojo.setCourses(courseList);
//
//        SerenityRest.given()
//                .contentType(ContentType.JSON)
//                .header("Content-Type","application/json")
//                .when()
//                .body(studentPojo)
//                .post()
//                .then().log().all(); // this is returning validatable response
        //creating reusability by adding above code in steps method and calling by creating object of steps
        ValidatableResponse response = steps.createStudent(firstName, lastName, email, programme, courseList);
        response.statusCode(201);
    }


    @Title("Verify if the student was created successfully")
    @Test
    public void test002() {
        //taking below code in steps class
//        String s1 = "findAll{it.firstName == '";
//        String s2 = "'}.get(0)";
//
//        HashMap<String,Object> studentMap =  SerenityRest.given()
//                .when()
//                .get(EndPoints.GET_ALL_STUDENT)
//                .then().statusCode(200)
//                .extract()
//                .path(s1+firstName+s2);

        HashMap<String,Object> studentMap = steps.getStudentInfoByFirstName(firstName);
        Assert.assertThat(studentMap, hasValue(firstName));
        studentId = (int)studentMap.get("id");
    }

    @Title("Update the student information and verify the updated information")
    @Test
    public void test003(){
        firstName = firstName +"_updated";
        List<String> courseList = new ArrayList<>();
        courseList.add("Java");
        courseList.add("Rest Assured");

//        StudentPojo studentPojo = new StudentPojo();
//        studentPojo.setFirstName(firstName);
//        studentPojo.setLastName(lastName);
//        studentPojo.setEmail(email);
//        studentPojo.setProgramme(programme);
//        studentPojo.setCourses(courseList);
//
//        SerenityRest.given()
//                .contentType(ContentType.JSON)
//                .header("Content-Type","application/json")
//                .pathParam("studentID", studentId)
//                .when()
//                .body(studentPojo)
//                .put(EndPoints.UPDATE_STUDENT_BY_ID)
//                .then().log().all().statusCode(200);
        ValidatableResponse response = steps.updateStudent(studentId, firstName, lastName, email, programme, courseList);
        response.statusCode(200);
        //or write like this
        //steps.updateStudent(studentId, firstName, lastName, email, programme, courseList).statusCode(201);


//        String s1 = "findAll{it.firstName == '";
//        String s2 = "'}.get(0)";
//        HashMap<String, Object> studentMap = SerenityRest.given()
//                .when()
//                .get(EndPoints.GET_ALL_STUDENT)
//                .then().statusCode(200)
//                .extract()
//                .path(s1 + firstName + s2);
        HashMap<String,Object> studentMap = steps.getStudentInfoByFirstName(firstName);
        Assert.assertThat(studentMap, hasValue(firstName));

    }
    @Title("This method will delete the student")
    @Test
    public void test004(){
//        SerenityRest.given().log().all()
//                .pathParam("studentID", studentId)
//                .when()
//                .delete(EndPoints.DELETE_STUDENT_BY_ID)
//                .then().statusCode(204);
        steps.deleteStudent(studentId).statusCode(204);

//        SerenityRest.given().log().all()
//                .pathParam("studentID", studentId)
//                .when()
//                .get(EndPoints.GET_SINGLE_STUDENT_BY_ID)
//                .then().statusCode(404);
        steps.getStudentId(studentId).statusCode(404);
    }
}



