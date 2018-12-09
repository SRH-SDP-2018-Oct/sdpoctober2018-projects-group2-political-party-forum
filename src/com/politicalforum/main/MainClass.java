package com.politicalforum.main;

import java.util.Scanner;

import com.politicalforum.providers.PoliticalPartyServicesProvider;
import com.politicalforum.services.PoliticalPartyServices;

public class MainClass {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		try {
			PoliticalPartyServices politicalPartyServices = PoliticalPartyServicesProvider
					.getPoliticalPartyServiceImplementor();
			int choice = 0;
			System.out.println("\t\tMenu\n\n1.Register User \n\n2.Login\n\n\n");
			System.out.println("Your choice: ");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				Registration.userRegistration(politicalPartyServices);
				break;
			case 2:
				Login.userLogin(politicalPartyServices);
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
