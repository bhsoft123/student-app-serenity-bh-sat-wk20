package com.studentapp.utils;

import com.github.javafaker.Faker;

import java.util.Random;

public class TestUtils {

	// use faker website for genrating random, add its dependency from mvnrespoitory
	//https://fakerjs.dev/
	//check in website what can be  generated


	public static String getRandomValue(){
		Random random = new Random();
		int randomInt = random.nextInt(100000);
		return Integer.toString(randomInt);
	}

	public static String generateFirstName(){
		Faker faker = new Faker();
		return faker.name().firstName(); // give firstName("female") and many different parameters
	}
	public static void main(String[] args){ // to see in console what is generated
		Faker  faker = new Faker();
		System.out.println(faker.address().city());
		System.out.println(generateFirstName());
	}
}
