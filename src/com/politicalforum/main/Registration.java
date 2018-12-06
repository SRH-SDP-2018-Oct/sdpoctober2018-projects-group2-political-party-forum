package com.politicalforum.main;

import java.util.Scanner;

import com.politicalforum.services.PoliticalPartyServices;

public class Registration {
	private static Scanner sc = new Scanner(System.in);

	public static void userRegistration(PoliticalPartyServices politicalPartyServices) {
		try {
			int choice = 0;
			System.out.println("\t\t\t\t\tMain Class");
			System.out.println("\t\tMenu\n\n1.General User \n\n2.Political User\n\n");
			System.out.println("Your choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Your First Name:- ");
				String firstName = sc.next();
				System.out.println("Your Last Name:- ");
				String lastName = sc.next();
				System.out.println("Your Age:- ");
				int age = sc.nextInt();
				System.out.println("Your Email-ID:- ");
				String emailId = sc.next();
				System.out.println("Your Gender:- ");
				String gender = sc.next();
				System.out.println("Your aadharNumber:- ");
				String aadharNumber = sc.next();
				System.out.println("Your Region:- ");
				String region = sc.next();
				System.out.println("Do you want to remain Anonymous(true/false)?");
				Boolean isAnonymous = sc.nextBoolean();
				String password = null;
				String confirmPassword = null;
				do {
					System.out.println("Enter a Password:- ");
					password = sc.next();
					System.out.println("Confirm password");
					confirmPassword = sc.next();
					if (!confirmPassword.equals(password)) {
						System.out.println("Reenter password.");
					}
				} while (!confirmPassword.equals(password));
				GeneralUserMenu.menu(politicalPartyServices.registerUserDetails(firstName, lastName, age, emailId,
						gender, aadharNumber, isAnonymous, region, password), politicalPartyServices);
				break;
			case 2:
				System.out.println("Your First Name:- ");
				firstName = sc.next();
				System.out.println("Your Last Name:- ");
				lastName = sc.next();
				System.out.println("Your Age:- ");
				age = sc.nextInt();
				System.out.println("Your Email-ID:- ");
				emailId = sc.next();
				System.out.println("Your Gender:- ");
				gender = sc.next();
				System.out.println("Your PoliticianId:- ");
				String politicianId = sc.next();
				System.out.println("Your Region:- ");
				region = sc.next();
				System.out.println("Do you want to remain Anonymous(true/false)?");
				isAnonymous = sc.nextBoolean();
				password = null;
				confirmPassword = null;
				do {
					System.out.println("Enter a Password:- ");
					password = sc.next();
					System.out.println("Confirm password");
					confirmPassword = sc.next();
					if (!confirmPassword.equals(password)) {
						System.out.println("Reenter password.");
					}
				} while (!confirmPassword.equals(password));

				PoliticalUserMenu.menu(politicalPartyServices.registerPoliticalUserDetails(firstName, lastName, age,
						emailId, gender, politicianId, isAnonymous, region, password), politicalPartyServices);
				break;

			default:
				System.out.println("Wrong option!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			sc.close();
		}
	}

}
