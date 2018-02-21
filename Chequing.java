/* Chequing.java */
/* BTP400 Zukhruf Khan */
/* Assignment 1 */

//package edu.btp400.w2017.classes;


import java.math.*;
import java.text.*;
import java.util.*;

class Chequing extends Account {
	
	double serviceCharge;
	int maxTransactions;
	int tranCounter = 0;
	double totalServiceCharges = 0.00;
	double startingBalance;
	
	double [] transactions;
	
	public Chequing() {
		super(null, null, -1);
		serviceCharge = 0.25;
		maxTransactions = 3;
		
		transactions = new double[maxTransactions];
	}
	
	public Chequing(String name, String accNumber, double stBalance, double serCharge, int maxTran) {
		super(name, accNumber, stBalance);
		serviceCharge = serCharge;
		maxTransactions = maxTran;
		
		startingBalance = stBalance;
		
		transactions = new double[maxTransactions];
	}
	
	
	@Override
	public boolean withdraw(double amount) {
		if (amount > 0 && amount < getAccBalance() && tranCounter != maxTransactions) {
			transactions[tranCounter] = (amount*-1); 
			tranCounter++;
			totalServiceCharges += serviceCharge;
			return true;
		} else {
			return false;
		} 
	}
	
	
	@Override
	void deposit(double amount) {
		if (amount > 0) {
			transactions[tranCounter] = amount; 
			tranCounter++;
			totalServiceCharges += serviceCharge;
		} else {
			//getAccBalance() = getAccBalance();
		}
	}
	
	
	@Override
	public double getAccBalance() {
		return super.getAccBalance();
	}
	
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		} else if (other instanceof Account && other instanceof Chequing) {
			Chequing account = (Chequing) other;
			if (account.getFullName().equals(this.getFullName()) && 
			account.getAccNumber().equals(this.getAccNumber()) && 
			account.getAccBalance() == this.getAccBalance() &&
			account.maxTransactions == this.maxTransactions &&
			account.serviceCharge == this.serviceCharge) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return getFullName().hashCode() * getAccNumber().hashCode() * Double.valueOf(serviceCharge).hashCode() * maxTransactions;
	}
	
	public String toString() {
		StringBuffer x = new StringBuffer(super.toString());
		x.append("Type: CHQ " + "\n" 
		+ "Service Charges: $" + serviceCharge + "\n"
		+ "Total Service Charges: $" + (serviceCharge * tranCounter) + "\n"
		+ "Number of Transactions Allowed: " + maxTransactions + "\n"
		+ "List of Transactions: ");
		for (int i = 0; i < tranCounter; i++) {
				if (transactions[i] > 0) {
					if (i == tranCounter - 1) {
						x.append("+" + transactions[i]);
					} else {
						x.append("+" + transactions[i] + ", ");
					}
					
				} else {
					if (i == tranCounter - 1) {
						x.append(transactions[i]);
					} else {
						x.append(transactions[i] + ", ");
					}
				}
		}
		x.append("\n"
		+ "Final Balance: $" + getAccBalance() + "\n");
		return x.toString();
	}
	
	
	
}
