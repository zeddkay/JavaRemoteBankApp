/* GIC.java */
/* BTP400 Zukhruf Khan */
/* Assignment 1 */

//package edu.btp400.w2017.classes;


import java.math.*;
import java.text.*;
import java.util.*;



class GIC extends Account implements Taxable {

	int investmentYears;
	double interestRate;
	double interestIncome;
	double maturityBalance;
	double taxAmount;
	
	public GIC() {
		super(null, null, -1);
		investmentYears = 1;
		interestRate = 1.25;
	}
	
	public GIC(String name, String accNumber, double stBalance, int years, double rate) {
		super(name, accNumber, stBalance);
		investmentYears = years;
		interestRate = rate;
	}
	
	
	public int hashCode() {
		return getFullName().hashCode() * getAccNumber().hashCode() 
		* Double.valueOf(getAccBalance()).hashCode() 
		* Double.valueOf(interestRate).hashCode() * investmentYears;
	}
	
	@Override
	public boolean withdraw(double amount) {
		
		return false;
	}
	
	
	@Override
	void deposit(double amount) {
		
	}
	
	
	@Override
	public double getAccBalance() {
		return super.getAccBalance();
	}
	
	
	public String toString() {
		StringBuffer x = new StringBuffer(super.toString());
		x.append("Type: GIC " + "\n" 
		+ "Annual Interest Rate: " + ((double)interestRate/100) + "%\n"
		+ "Period of Investment: " + investmentYears + " years\n"
		+ "Interest Income at Maturity: " + interestIncome + "\n"
		+ "Balance at Maturity: $" + getAccBalance() + "\n");
		return x.toString();
	}
	
	public void calculateTax(double taxRate) {
		maturityBalance = super.getAccBalance() * Math.pow(1+interestRate, investmentYears);
		
		interestIncome = maturityBalance - super.getAccBalance();
		taxAmount = interestIncome * (((double)taxRate/100)/100);
	}

   public  double getTaxAmount() {
		calculateTax(interestRate);
		return taxAmount;
	}

	public String createTaxStatement() {
		StringBuffer x = new StringBuffer("Tax rate: " + interestRate + "%\n");
		x.append("Account Number: " + getAccNumber() + "\n"
		+ "Interest income: $" + interestIncome + "\n"
		+ "Amount of tax: $ " + getTaxAmount() + "\n");
		return x.toString();
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		} else if (other instanceof Account && other instanceof GIC) {
			GIC account = (GIC) other;
			if (account.getFullName().equals(this.getFullName()) && 
			account.getAccNumber().equals(this.getAccNumber()) && 
			account.getAccBalance() == this.getAccBalance() &&
			account.investmentYears == this.investmentYears &&
			account.interestRate == this.interestRate) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
}
