package com.edu.bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BankDataOperations {
	static Connection scon=null;
	static ResultSet rs=null;
	static Statement st=null;

	static Scanner sc=new Scanner(System.in);
	static String Acc_no,Name,Acc_type,IFSC,Branch,Address,Phone;
	static float Deposit,Withdrawls,Balance;

	//Creating a new Account
	public static void createAccount() { 
		try
		{
			scon=DbConnect.getConnection();
			st=scon.createStatement();
			String sql="select * from AccountDetails where Acc_no="+Acc_no;
			rs=st.executeQuery(sql);
			if(!rs.next()) {
				System.out.print("Enter Account No: ");  
				Acc_no = sc.next();
				System.out.print("Enter Name: "); 
				Name = sc.next();  
				System.out.print("Enter Account type: "); 
				Acc_type = sc.next();  
				System.out.print("Enter IFSC: ");  
				IFSC = sc.next();
				System.out.print("Enter Branch: ");  
				Branch = sc.next();
				System.out.print("Type Address: ");  
				Address = sc.next();
				System.out.print("Enter phone_no: ");  
				Phone = sc.next();  
				String ins="insert into AccountDetails values("+Acc_no+",'"+Name+"','"+Acc_type+"','"+IFSC+"','"+Branch+"','"+Address+"','"+Phone+"')";
				int i =st.executeUpdate(ins);

				if(i>0)
				{
					System.out.println("Account Created");
				}

			}else
			{
				System.out.println("Account Not Created");
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	//Deposit Amount
	public static void DepositAmount() {

		try
		{
			scon=DbConnect.getConnection();
			st=scon.createStatement();
			System.out.println("Enter Acc_no to deposit Amount");
			Acc_no=sc.next();
			System.out.println("Enter Deposit Amount");
			Deposit=sc.nextFloat();
			String ins="insert into transaction(Acc_no,Deposit,Balance) values("+Acc_no+","+Deposit+","+Balance+")";
			int i=st.executeUpdate(ins);
			String sql="update transaction set Balance="+(Balance+Deposit)+" where Acc_no="+Acc_no;
			st.executeUpdate(sql);

			if(i>0)
			{
				System.out.println("Balance is Rs."+Deposit);
			}
			else
			{
				System.out.println("Enter valid Acc_no");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//Withdraw Amount
	public static void WithdrawAmount() {
		try {
			scon=DbConnect.getConnection();
			st=scon.createStatement();
			String sql="select * from transaction where acc_no="+Acc_no;
			rs=st.executeQuery(sql);

			if(rs.next())
			{
				System.out.println("enter the Withdraw amount");
				Withdrawls=sc.nextFloat();
				if(Withdrawls>rs.getFloat(3))
				{
					System.out.println("Your Balance is less than "+Withdrawls+"\n Transaction failed..!");

				}
				else if(Withdrawls<rs.getFloat(3))
				{
					Balance=rs.getFloat(3)-Withdrawls;
					String ins="insert into transaction (Acc_no,Withdrawls,Balance) values("+Acc_no+","+Withdrawls+","+Balance+")";
					st.executeUpdate(ins);
					String bal="update transaction set Balance="+Balance+" where acc_no="+Acc_no;
					int i = st.executeUpdate(bal);

					if(i>0)
					{
						System.out.println("Withdraw balance succesfully");
						System.out.println("Your Available Balance is="+Balance);

					}
				}
			}
			else
			{
				System.out.println("Account number is incorrect");

			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//Checking Account Balance
	public static void CheckBalance() {
		try {
			scon=DbConnect.getConnection();
			st=scon.createStatement();
			System.out.println("Enter Acc_no to Check balance");
			Acc_no=sc.next();
			String sql="select * from Transaction where Acc_no="+Acc_no;
			rs=st.executeQuery(sql);
			if(rs.next()) {
				System.out.println("Available Balance Rs."+rs.getFloat(3));
			}else {
				System.out.println("Enter valid Acc_no");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	//Searching Account Details by Account Number
	public static void SearchAccountDetail() {
		try {
			scon=DbConnect.getConnection();
			st=scon.createStatement();
			System.out.println("Enter Acc_no to display Information");
			Acc_no=sc.next();
			String sql="select * from AccountDetails where Acc_no="+Acc_no;
			rs=st.executeQuery(sql);
			if(rs.next()) {
				System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getString(7));
			}else {
				System.out.println("Enter valid Acc_no");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	//Display all Customer Account Details
	public static void DisplayCustomerAccountDetails() {
		try {
			scon=DbConnect.getConnection();
			st=scon.createStatement();
			String sql="select * from AccountDetails";
			rs=st.executeQuery(sql);
			System.out.println("Acc_no\t\tName\t\tAcc_type\tIFSC\t\tBranch\t\tAddress\t\tPhone");
			while(rs.next())
			{
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getString(7));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
