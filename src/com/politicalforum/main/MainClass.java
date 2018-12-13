package com.politicalforum.main;

import java.util.Scanner;

import com.politicalforum.exceptions.ServiceNotFoundException;
import com.politicalforum.providers.PoliticalPartyServicesProvider;
import com.politicalforum.services.PoliticalPartyServices;

public class MainClass {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int choice = 0;
		do {
			try {
				PoliticalPartyServices politicalPartyServices = PoliticalPartyServicesProvider
						.getPoliticalPartyServiceImplementor();

				System.out.println("\t\tMenu\n\n1.Register User \n\n2.Login\n\n3.Exit\n");
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
				case 3:
					System.out.println("Closing connection....");
					politicalPartyServices.exit();
					System.out.println("Connection Closed");
					System.exit(0);
					break;
				default:
					System.out.println("Wrong option! Select Again.");
					break;
				}
			} catch (ServiceNotFoundException e) {
				System.err.println(e.getMessage());
			} catch (Exception e) {
				System.err.println(":(   "+e.getMessage() + "    ):");
			}
		} while (choice != 3);
	}
}
