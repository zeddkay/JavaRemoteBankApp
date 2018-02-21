/* BTP400 SCC Assignment 2
Zukhruf Khan 
RemoteBankImpl.java 
*/


import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

public class RemoteBankImpl extends UnicastRemoteObject
			 implements RemoteBank {

			 
			 
	private ArrayList<Account> accounts = new ArrayList<Account>();
	private String bankName;

	public RemoteBankImpl() throws RemoteException {
		bankName = "Seneca@York";

	}
	
	public RemoteBankImpl(String bname) throws RemoteException {
		bankName = bname;
	}
	
	public void setAccount(Account acc, int index) throws RemoteException {
		accounts.add(acc);
	}
	
	public void setBankName(String bn) throws RemoteException {
		bankName = bn;
	}
	
	
	public Account getAccount(int index) throws RemoteException {
		return accounts.get(index);
	}
	
	public String getBankName() throws RemoteException {
		return bankName;
	}
	
	
	public int getCount() throws RemoteException {
		return accounts.size();
	}
	
	
	//
	/*
	@Override
	public boolean equals(Object other) throws RemoteException {
		if (other instanceof Bank) {
			RemoteBankImpl bank = (RemoteBankImpl) other;
			//to test for the same bank name, ignoring case
			int compareName = this.getBankName().compareToIgnoreCase(bank.getBankName());
			if (compareName == 0 && bank.getCount() == this.getMaxAccounts() &&
			 bank.getCount() == this.getCount()) {
				for (int i = 0; i < getCount(); i++) {
					if (bank.accounts.get(i).equals(this.accounts.get(i))) {
						return true;
					} else {
						return false;
					}
				}
			} return false;
		} else {
			return false;
		}
	}
	//*/
	
	
	public int hashCode()  {
		return bankName.hashCode(); 
	}

	//@Override
	public String toSstring() throws RemoteException    {
		String output;
		output = "Bank name: " + getBankName()  + "\n";
		output+= "Bank accounts opened: " + getCount() + "\n";	//to see how many accounts are actually in use
		if (getCount() > 0) {
			output+= "Bank acounts: \n";
			for (int i = 0; i < getCount(); i++) {
				output+= i+1 + ". Account name: " + accounts.get(i).getFullName() 
				+ "\tAccount number: " +accounts.get(i).getAccNumber()
				+ "\tAccount balance: " + accounts.get(i).getAccBalance() + "\n";	
			}
		}
		output+= "\n";
		return output;
	}
	
	
	/** 
	* Adds an Account to the Bank
	* @param add : The Account to be added
	* @return a bool on if the acount has been added
	*/
	public boolean addAccount(Account add) throws RemoteException {
		int same = -1;
		for (int i = 0; i < getCount(); i++) {
			if (accounts.get(i).equals(add)) {
				same = 0;
				break;
			}
		}
		if (add==null || same !=-1) {		
			return false;
		}	else {
			setAccount(add, getCount());
			return true;
		}
	}
	
	
	
	/** 
	* Removes an Account from the Bank
	& @param accNum : The Account number for the Account to be removed
	* @return the removed Account
	*/
	public Account removeAccount(String accNum) throws RemoteException {
		Account removed = new  Account();
		boolean found = false;
		if (accNum != null) {
			for (int i = 0; i < getCount(); i++) {
				if (accounts.get(i).getAccNumber().equals(accNum)) {	//check for the account number
					removed = accounts.get(i);
					accounts.remove(i);
					found = true;
					return removed;
				}
			}
		}
		if (!found) {
			//System.out.println("Not found!");
			
		}
		return removed;
	}
	
	

	/** 
	* Returns an array of all Accounts in the bank 
	* @param bal: the balance of the Account(s) to search for
	* @return an (array of) Account(s)
	*/
	public Account[] search(int bal) throws RemoteException {
		int rCount = 0;
		for (int i = 0; i < getCount(); i++) {
			if (getAccount(i).getAccBalance() == bal) {
				rCount++;					//count and/or increment the number of matching accounts
			}
		}
		
		//initialize an array of Account objects of matching accounts size (could be 0, if no matches were found)
		Account [] results = new Account[rCount];		
		
		int rsize =0;
		for (int i = 0; i < getCount(); i++) {
			if (getAccount(i).getAccBalance() == bal) {		
				results[rsize] = getAccount(i);			//add the matching accounts to the results array
				rsize++;								
			}
		}
		
		return results;
	}
	
	
	/** 
	* Returns an array of all Accounts in the bank 
	* @param accountName: the name of the Account(s) to search for
	* @return an (array of) Account(s)
	*/
	public Account[] searchByAccountName(String accountName) throws RemoteException {
		
		int rCount = 0;
		String[] names = accountName.split("\\s*,\\s*");
		String firstName =  names[1].trim();
						
        String lastName =  names[0].trim();
						
        String fullName = names[1].trim() + ", " + names[0].trim();
			
		for (int i = 0; i < getCount(); i++) {
			if (getAccount(i).getFullName().toUpperCase().equals(fullName.toUpperCase())){// || accounts.get(i) instanceof GIC)) {
				rCount++;					//count and/or increment the number of matching accounts
			}
		}
		
		//initialize an array of Account objects of matching accounts size (could be 0, if no matches were found)
		Account [] results = new Account[rCount];		
		
		int rsize =0;
		for (int i = 0; i < getCount(); i++) {
			if (getAccount(i).getFullName().toUpperCase().equals(fullName.toUpperCase()) ){// || accounts.get(i) instanceof GIC)) {		
				results[rsize] = getAccount(i);			//add the matching accounts to the results array
				rsize++;								
			}
		}
		
		return results;
	}

}
