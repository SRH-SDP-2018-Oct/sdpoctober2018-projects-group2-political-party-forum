package com.politicalforum.main;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.politicalforum.beans.Project;
import com.politicalforum.beans.User;
import com.politicalforum.exceptions.UnknownDateFormatException;
import com.politicalforum.services.PoliticalPartyServices;
import com.politicalforum.utils.Helper;
import com.politicalforum.validation.Validations;

public class ProjectFeatures {

	private static Scanner sc = new Scanner(System.in);

	public static void createProject(User user, PoliticalPartyServices politicalPartyServices,
			Boolean isUserPoliticianAndGroupOwner) {
		Date taskStartDate = null, taskEndDate = null, intendedCompletionDate = null;
		String endDate = null, tentativeComplete;
		System.out.println("\n-----------Create Project------------");
		System.out.println("Enter Project Name:- ");
		String taskName = sc.nextLine();
		taskName.trim();
		System.out.println("Enter Project Description:- ");
		String taskDescription = sc.nextLine();
		taskDescription.trim();
		String startDate = null;
		do {
			try {
				System.out.println("Enter Project Start Date(DD/MM/YYYY):- ");
				startDate = sc.nextLine();
				taskStartDate = Helper.convertDateToSqlDate(startDate);
			} catch (UnknownDateFormatException e) {
				System.out.println(e.getMessage());
			}
		} while (Validations.validateIfDateToCompareIsGreater(taskStartDate, Helper.getCurrentDateOfTypeJavaSql()));
		do {
			try {
				System.out.println("Enter Project End Date(DD/MM/YYYY):-");
				endDate = sc.nextLine();
				taskEndDate = Helper.convertDateToSqlDate(endDate);
			} catch (UnknownDateFormatException e) {
				System.out.println(e.getMessage());
			}
		} while (Validations.validateIfDateToCompareIsGreater(taskEndDate, taskStartDate));
		do {
			try {
				System.out.println("Enter Intended Completion Date(DD/MM/YYYY)");
				tentativeComplete = sc.nextLine();
				intendedCompletionDate = Helper.convertDateToSqlDate(tentativeComplete);
			} catch (UnknownDateFormatException e) {
				System.out.println(e.getMessage());
			}
		} while (Validations.validateIfDateToCompareIsGreater(intendedCompletionDate, taskStartDate));
		System.out.println("Enter Fund allocated for this project:- ");
		int taskAllocatedFund = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Contractor Name:- ");
		String contractorName = sc.nextLine();
		user = politicalPartyServices.createProject(user,
				new Project(taskName, taskStartDate, taskEndDate, intendedCompletionDate, taskDescription,
						taskAllocatedFund, Helper.getCurrentDateOfTypeJavaSql(), contractorName));
		System.out.println("Project Details:- " + user.getSelectedGroup().getSelectedProject().toString());
		viewProject(user, politicalPartyServices, isUserPoliticianAndGroupOwner);
	}

	public static void viewProject(User user, PoliticalPartyServices politicalPartyServices,
			Boolean isUserPoliticianAndGroupOwner) {
		Project selectedProject = user.getSelectedGroup().getSelectedProject();
		System.out.println("\n----------------Project------------------");
		System.out.println("\nProject Created On:- " + selectedProject.getTaskCreationTimestamp());
		System.out.println("\nProject Name:- " + selectedProject.getTaskName());
		System.out.println("\nProject Description:- " + selectedProject.getTaskDescription());
		System.out.println("\nContractor for this Project:- " + selectedProject.getContractorName());
		System.out.println("\nFund Allocated:- " + selectedProject.getTaskAllocatedFund());
		System.out.println("\nProject Started On:- " + selectedProject.getTaskStartDate());
		System.out.println("\nProject Ends On:- " + selectedProject.getTaskEndDate());
		String choice = null;
		do {
			System.out.println("Generate Report?(y/n)");
			choice = sc.nextLine();
		} while (!(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")));
		if (choice.equalsIgnoreCase("y")) {
			// Generate Report
		}
		if (isUserPoliticianAndGroupOwner) {
			do {
				System.out.println("Would you like to update the project(y/n):- ");
				choice = sc.nextLine();
			} while (!(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")));
			if (choice.equalsIgnoreCase("y")) {
				updateProject(user, politicalPartyServices);
			}
		}
	}

	public static void listProjects(User user, PoliticalPartyServices politicalPartyServices,
			Boolean isUserPoliticianAndGroupOwner) throws UnknownDateFormatException {
		int choice = 0;
		System.out.println("\nProjects For Group " + user.getSelectedGroup().getGroupName());
		List<Project> projects = politicalPartyServices.viewProjects(user.getSelectedGroup().getGroupId());
		if (projects.isEmpty()) {
			System.out.println("No Projects found for this group.");
			return;
		}
		HashMap<Integer, Project> map = new HashMap<>();
		for (int i = 0; i < projects.size(); i++) {
			System.out.println((i + 1) + ". " + projects.get(i).getTaskName());
			map.put(i + 1, projects.get(i));
		}
		System.out.println("\nYour Choice:- ");
		choice = sc.nextInt();
		sc.nextLine();
		user.getSelectedGroup().setSelectedProject(map.get(choice));
		viewProject(user, politicalPartyServices, isUserPoliticianAndGroupOwner);
	}

	public static void updateProject(User user, PoliticalPartyServices politicalPartyServices) {
		int choice = 0;
		do {
			System.out.println("---------------Update Project------------------");

			System.out.println("\nWhat would you like to update? ");
			System.out.println("\n1. Project End Date ");
			System.out.println("2. Project Contractor ");
			System.out.println("3. Back");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				Date newEndDate = null;
				do {
					try {
						System.out.println("Update Project End Date To(DD/MM/YYYY):- ");
						String updateEndDate = sc.nextLine();
						newEndDate = Helper.convertDateToSqlDate(updateEndDate);
					} catch (UnknownDateFormatException e) {
						System.out.println(e.getMessage());
					}
				} while (Validations.validateIfDateToCompareIsGreater(newEndDate,
						user.getSelectedGroup().getSelectedProject().getTaskStartDate()));
				politicalPartyServices.updateProject(user.getSelectedGroup(), newEndDate, null);
				break;
			case 2:
				System.out.println("Update Contractor To:- ");
				String newContractor = sc.nextLine();
				politicalPartyServices.updateProject(user.getSelectedGroup(), null, newContractor);
				break;
			case 3:
				break;
			default:
				System.out.println("Wrong Option");
				break;
			}
		} while (choice != 3);
	}
}
