package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay   // https://www.mockaroo.com/ -can generate 100s of data for testing
 * //this 100data is for valid only, cant take it for invalid. create list,preview data and copy from RAW
 * //It wll not runagain, to run again delete container in docker and restart the app
 *                  //https://fakerjs.dev/
 */


//commenting out to check tags test, otherwise it will run for 100times
//@Concurrent(threads = "4x")    // to make it faster
//@UseTestDataFrom("src/test/resources/testdata/studentinfo.csv")   // content root for data file .csv
//@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentDataDrivenTest extends TestBase {

    private String firstName;
    private String lastName;
    private String email;
    private String programme;
    private List<String> courses;
    private String course1;
    private String course2;

    @Steps
    StudentSteps steps;

    @Title("Data deiven test for adding multiple students to application")
    @Test
    public void createMulitpleStudents(){
        List<String> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        steps.createStudent(firstName,lastName,email,programme,courses);
    }
}
