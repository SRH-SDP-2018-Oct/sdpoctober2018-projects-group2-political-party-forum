package com.politicalforum.main;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.politicalforum.beans.Group;
import com.politicalforum.providers.PoliticalPartyServicesProvider;
import com.politicalforum.services.PoliticalPartyServices;
import com.politicalforum.utils.Helper;

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
				HashMap<String, Object> user = politicalPartyServices.login(emailId, password);
				for(String key: user.keySet()) {
					if(Helper.checkIfUserIsPolitician(key)) {
						politicalUserMenu(key, politicalPartyServices);
					} else {
						System.out.println("General User Login");
					}
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

	private static void politicalUserMenu(String groupOwnerId, PoliticalPartyServices politicalPartyServices) throws SQLException {
		int choice = 0;
		System.out.println("\t\tMenu\n\n1.Create Group \n\n2.Browse Groups\n");
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
		case 2: 
			System.out.println("Available groups to join");
			List<Group> groups = politicalPartyServices.browseGroups();
			for(int i=0; i<groups.size();i++) {
				System.out.println((i+1)+". " + groups.get(i).getGroupName());
			}
			break;
		default:
			System.out.println("Wrong Option!");
			break;

		}
	}

}
