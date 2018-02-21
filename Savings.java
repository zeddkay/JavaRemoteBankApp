/* Savings.java */
/* BTP400 Zukhruf Khan */
/* Assignment 2 */


//package edu.btp400.w2017.classes;



import java.math.*;
import java.text.*;
import java.util.*;
import java.io.*;
class Savings extends Account implements Taxable {
	
	double startingBalance;
	private double annInterestRate;
	private double interestIncome;
	double taxAmount;
	
	
	public Savings() {
		super(null, null, -1);
		annInterestRate = 10;
	}
	
	
	public Savings(String name, String accNumber, double stBalance, double intRate) {
		super(name, accNumber, stBalance);
		annInterestRate = intRate;
	}
	
	public int hashCode() {
		return getFullName().hashCode() * getAccNumber().hashCode() * Double.valueOf(getAccBalance()).hashCode() * Double.valueOf(annInterestRate).hashCode();
	}
	
	@Override
	public double getAccBalance() {
		return super.getAccBalance();
	}
	
	
	public String toString() {
		StringBuffer x = new StringBuffer(super.toString());
		x.append("Type: SAV " + "\n" 
		+ "Interest Rate: " + ((double)annInterestRate/100) + "%\n"
		+ "Interest Income: $" + getTaxAmount() + "\n"
		+ "Final Balance: $" + getAccBalance() + "\n");
		return x.toString();
	}
	
	public void calculateTax(double taxRate) {
		interestIncome = getAccBalance() * (((double)taxRate/100)/100);
		taxAmount = interestIncome * (((double)taxRate/100)/100);
	}

   public  double getTaxAmount() {
		calculateTax(annInterestRate);
		return taxAmount;
	}

	public String createTaxStatement() {
		StringBuffer x = new StringBuffer("Tax rate: " + annInterestRate + "%\n");
		x.append("Account Number: " + getAccNumber() + "\n"
		+ "Interest income: $" + interestIncome + "\n"
		+ "Amount of tax: $ " + getTaxAmount() + "\n");
		return x.toString();
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		} else if (other instanceof Account && other instanceof Savings) {
			Savings account = (Savings) other;
			if (account.getFullName().equals(this.getFullName()) && 
			account.getAccNumber().equals(this.getAccNumber()) && 
			account.getAccBalance() == this.getAccBalance() &&
			account.annInterestRate == this.annInterestRate) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
}
