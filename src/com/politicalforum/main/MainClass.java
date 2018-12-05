package com.politicalforum.main;

import java.util.Scanner;

import com.politicalforum.beans.PoliticalUser;
import com.politicalforum.beans.User;
import com.politicalforum.providers.PoliticalPartyServicesProvider;
import com.politicalforum.services.PoliticalPartyServices;
import com.politicalforum.utils.GenericUser;

public class MainClass {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		try {
			PoliticalPartyServices politicalPartyServices = PoliticalPartyServicesProvider
					.getPoliticalPartyServiceImplementor();
			int choice = 0;
			System.out.println("\t\t\t\t\tMain Class");
			System.out.println("\t\tMenu\n\n1.Register User \n\n2.Political User\n\n3. Login");
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
					if(!confirmPassword.equals(password)) {
						System.out.println("Reenter password.");
					}
				} while(!confirmPassword.equals(password));
				System.out.println("User ID is:- " + politicalPartyServices.registerUserDetails(firstName, lastName,
						age, emailId, gender, aadharNumber, isAnonymous, region, password));
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
					if(!confirmPassword.equals(password)) {
						System.out.println("Reenter password.");
					}
				} while(!confirmPassword.equals(password));
				politicalUserMenu(politicalPartyServices.registerPoliticalUserDetails(firstName, lastName, age, emailId,
						gender, politicianId, isAnonymous, region, password), politicalPartyServices);
				break;
			case 3:
				System.out.println("Enter Email-Id:- ");
				emailId = sc.next();
				System.out.println("Enter Password:- ");
				password = sc.next();
				GenericUser<Object> genericUser = politicalPartyServices.login(emailId, password);
				if(genericUser.getGenericUser() instanceof User) {
					User usr = (User)genericUser.getGenericUser();
					System.out.println(usr.toString());
				} else if(genericUser.getGenericUser() instanceof PoliticalUser) {
					PoliticalUser pu = (PoliticalUser)genericUser.getGenericUser();
					System.out.println(pu.toString());
					politicalUserMenu( pu.getPoliticalUserId(), politicalPartyServices);
				}
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
