//SIMPLE BANKING APPLICATION
package com.edu.bank;

import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		
		while(true)
		{
			System.out.println("Database operations");
			System.out.println("Enter your choice");
			System.out.println("1.Admin");
			System.out.println("2.User");
			int n= sc.nextInt(); 
			switch(n)
			{
			case 1: Admin();
			break;
			 
			case 2: User();
			break;

		    default:System.out.println("exit");
			}
			System.out.println("do you want to continue yes/no");
			String choice=sc.next();
			if(choice.equalsIgnoreCase("no"))
			{
				break;
			}
		}
	}		

	private static void Admin() {
		while(true)
		{
		
		System.out.println("1.CreateAccount");
		System.out.println("2.Search Account Detail by account number");
		System.out.println("3.DisplayCustomerAccountDetails");
		Scanner a = new Scanner (System.in);
		int ch= a.nextInt(); 
		switch(ch)
		{
		case 1:BankDataOperations.createAccount();
		break;
	
		case 2:BankDataOperations.SearchAccountDetail();
		break;

		case 3:BankDataOperations.DisplayCustomerAccountDetails();
		break;

		default:System.out.println("exit");

		}
		System.out.println("do you want to continue yes/no");
		String c=a.next();
		if(c.equalsIgnoreCase("no"))
		{
			break;
		}
	}
		System.out.println("Process End\n  *******");

	}

	private static void User() {
		while (true)
		{
		System.out.println("1.DepositAmount");
		System.out.println("2.WithdrawAmount ");
		System.out.println("3.CheckBalance");
		Scanner c= new Scanner(System.in);
		int U= c.nextInt(); 
		switch(U)
		{

		case 1:BankDataOperations.DepositAmount();
		break;

		case 2:BankDataOperations.WithdrawAmount();
		break;

		case 3:BankDataOperations.CheckBalance();
		break;
		
		default:System.out.println("exit");

		}
		System.out.println("do you want to continue yes/no");
		String o=c.next();
		if(o.equalsIgnoreCase("no"))
		{
			break;
		}

	}	
		System.out.println("Process End\n  *******");
	}
	
}