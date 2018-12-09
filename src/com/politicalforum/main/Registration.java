package com.politicalforum.main;

import java.util.Scanner;

import com.politicalforum.services.PoliticalPartyServices;
import com.politicalforum.validation.Validations;

public class Registration {
	private static Scanner sc = new Scanner(System.in);
	private static volatile String firstName = null;
	private static volatile String lastName = null;
	private static volatile String emailId = null;
	private static volatile String aadharNumber = null;
	private static volatile String age = null;
	private static volatile String gender = null;
	private static volatile String politicianId = null;
	private static volatile String region = null;
	private static volatile String password = null;

	public static void userRegistration(PoliticalPartyServices politicalPartyServices) {
		try {
			int choice = 0;
			System.out.println("\t\tMenu\n\n1.General User \n\n2.Political User\n\n");
			System.out.println("Your choice: ");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			//General User Registration
			case 1:
				do {
					System.out.println("Your First Name:- ");
					firstName = sc.nextLine();
				} while (!Validations.validateName(firstName));

				do {
					System.out.println("Your Last Name:- ");
					lastName = sc.nextLine();
				} while (!Validations.validateName(lastName));

				do {
					System.out.println("Your Age:- ");
					age = sc.nextLine();
				} while (!Validations.validateAge(age));

				do {
					System.out.println("Your Email-ID:- ");
					emailId = sc.nextLine();
				} while (!Validations.validateEmail(emailId));

				do {
					System.out.println("Your Gender:- ");
					gender = sc.nextLine();
				} while (!Validations.validateGender(gender));

				do {
					System.out.println("Your 12 Digit Aadhar Number:- ");
					aadharNumber = sc.nextLine();
				} while (!Validations.validateAadhar(aadharNumber));

				do {
					System.out.println("Your Region:- ");
					region = sc.nextLine();
				} while (!Validations.validateRegion(region));

				System.out.println("Do you want to remain Anonymous(true/false)?");
				Boolean isAnonymous = sc.nextBoolean();
				sc.nextLine();
				String confirmPassword = null;
				
				do {

					System.out.println("Enter a Password:- ");
					password = sc.nextLine();
					System.out.println("Confirm password");
					confirmPassword = sc.nextLine();
					if (!confirmPassword.equals(password)) {
						System.out.println("Re-Enter password.");
					}
				} while (!confirmPassword.equals(password));
				
				GeneralUserMenu.menu(politicalPartyServices.registerUserDetails(firstName, lastName, 0, emailId, gender,
						aadharNumber, isAnonymous, region, password), politicalPartyServices);
				break;
				
			//Political User Registration
			case 2:
				
				do {
					System.out.println("Your First Name:- ");
					firstName = sc.nextLine();
				} while (!Validations.validateName(firstName));

				do {
					System.out.println("Your Last Name:- ");
					lastName = sc.nextLine();
				} while (!Validations.validateName(lastName));

				do {
					System.out.println("Your Age:- ");
					age = sc.nextLine();
				} while (!Validations.validateAge(age));

				do {
					System.out.println("Your Email-ID:- ");
					emailId = sc.nextLine();
				} while (!Validations.validateEmail(emailId));

				do {
					System.out.println("Your Gender:- ");
					gender = sc.nextLine();
				} while (!Validations.validateGender(gender));
				do {
					System.out.println("Your PoliticianId:- ");
					politicianId = sc.nextLine();
				} while (!Validations.validatePoliticalID(politicianId));

				do {
					System.out.println("Your Region:- ");
					region = sc.nextLine();
				} while (!Validations.validateRegion(region));

				System.out.println("Do you want to remain Anonymous(true/false)?");
				isAnonymous = sc.nextBoolean();
				sc.nextLine();
				password = null;
				confirmPassword = null;
				do {
					System.out.println("Enter a Password:- ");
					password = sc.nextLine();
					System.out.println("Confirm password");
					confirmPassword = sc.nextLine();
					if (!confirmPassword.equals(password)) {
						System.out.println("Reenter password.");
					}
				} while (!confirmPassword.equals(password));

				PoliticalUserMenu.menu(politicalPartyServices.registerPoliticalUserDetails(firstName, lastName, 0,
						emailId, gender, politicianId, isAnonymous, region, password), politicalPartyServices);
				break;

			default:
				System.out.println("Wrong option!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}

}
