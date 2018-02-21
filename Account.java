/* Account.java */
/* BTP400 Zukhruf Khan */
/* Assignment 2 */


import java.io.*;

public class Account implements Serializable {
	
	private String fullName;
	private String accNumber;
	private double accBalance;
	private String firstName;
	private String lastName;
	
	public Account( ) { 
		this(null, null, -1);
	}
	
	public Account(String name, String anumber, double abalance) {

		if (name != null && name.indexOf(',') != -1) {
				String[] names = name.split("\\s*,\\s*");
				if (fullName != " " && names.length != 0) {
						firstName =  names[1].trim();
                        lastName =  names[0].trim();
						fullName = names[1].trim() + ", " + names[0].trim();
				} else { 
					firstName = " ";
					lastName = " ";
					fullName = " ";
				}
		} else {
			fullName = " ";
			firstName = " ";
			lastName = " ";
		}
        accNumber = anumber;
		
		//setAccBalance(abalance);
		if (abalance > 0) {
			accBalance = abalance;
		} else {
			accBalance = 0;
		}
		
		
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getAccNumber() {
		return accNumber;
	}
	
	public double getAccBalance() {
		return accBalance;
	}
	
	
	public String toString() {
				
		return "Name: " + getFullName() + "\n"
		+ "Number: " + getAccNumber() + "\n"
		+ "Current Balance: $" + getAccBalance() + "\n";
	}

	
	public int hashCode() {
		return fullName.hashCode() * accNumber.hashCode() * Double.valueOf(accBalance).hashCode();
	}
	
	
	//overloaded equals() method to check that the full name and account number are not null
	public boolean equals(Object other) {
		
		if (this == other) {
			return true;
		} else if (other instanceof Account) {
			Account account = (Account) other;
			if (account.getFullName().equals(this.getFullName()) && 
			account.getAccNumber().equals(this.getAccNumber()) && 
			account.getAccBalance() == this.getAccBalance()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}	
	
	
	public boolean withdraw(double amount) {
		if (amount > 0 && amount < getAccBalance()) {
			accBalance -= amount;
			return true;
		} else {
			return false;
		} 
	}
	
	
	void deposit(double amount) {
		if (amount > 0) {
			accBalance += amount;
		} else {
			accBalance = accBalance;
		}
	}
	
	

}
	
