package com.politicalforum.main;

import java.util.Scanner;

import com.politicalforum.providers.PoliticalPartyServicesProvider;
import com.politicalforum.services.PoliticalPartyServices;

public class MainClass {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Hello!");

		try {
			PoliticalPartyServices politicalPartyServices = PoliticalPartyServicesProvider
					.getPoliticalPartyServiceImplementor();
			int choice = 0;
			System.out.println("\t\t\t\t\tMain Class");
			System.out.println("\t\tMenu\n\n1.Register User \n\n2.Political User");
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
				System.out.println("User ID is:- " + politicalPartyServices.registerUserDetails(firstName, lastName,
						age, emailId, gender, aadharNumber, isAnonymous, region));
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
				politicalUserMenu(politicalPartyServices.registerPoliticalUserDetails(firstName, lastName, age, emailId,
						gender, politicianId, isAnonymous, region), politicalPartyServices);
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

	private static void politicalUserMenu(String groupOwnerId, PoliticalPartyServices politicalPartyServices) {
		int choice = 0;
		System.out.println("\t\tMenu\n\n1.Create Group \n\n2.Join Group\n");
		System.out.println("Enter Option:- ");
		choice = sc.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Enter Group Name:- ");
			String groupName = sc.next();
			System.out.println("Describe this group:- ");
			String groupDescription = sc.next();
			System.out.println("Group Created ID:- "
					+ politicalPartyServices.createGroup(groupName, groupDescription, groupOwnerId));
			break;
		default:
			System.out.println("Wrong Option!");
			break;

		}
	}

}
