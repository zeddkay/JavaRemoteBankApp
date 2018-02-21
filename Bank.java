/* Bank.java */
/* BTP400 Zukhruf Khan */
/* Assignment 2 */


import java.util.ArrayList;
public class Bank  {
	private ArrayList<Account> accounts = new ArrayList<Account>();
	private String bankName;

	public Bank() {
		bankName = "Seneca@York";

	}
	
	public Bank(String bname) {
		bankName = bname;
	}
	
	public void setAccount(Account acc, int index) {
		accounts.add(acc);
	}
	
	public void setBankName(String bn) {
		bankName = bn;
	}
	
	
	public Account getAccount(int index) {
		return accounts.get(index);
	}
	
	public String getBankName() {
		return bankName;
	}
	
	
	public int getCount() {
		return accounts.size();
	}
	
	
	public boolean equals(Object other) {
		if (other instanceof Bank) {
			Bank bank = (Bank) other;
			//to test for the same bank name, ignoring case
			int compareName = this.getBankName().compareToIgnoreCase(bank.getBankName());
			if (compareName == 0 && /*bank.getC() == this.getMaxAccounts() &&*/ bank.getCount() == this.getCount()) {
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
	
	
	public int hashCode() {
		return bankName.hashCode(); 
	}

	public String toString() {
		String output;
		output = "Bank name: " + getBankName() + "\n";
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
	public boolean addAccount(Account add) {
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
	public Account removeAccount(String accNum) {
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
	public Account[] search(int bal) {
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
			/*loop through all accounts in bank again, because the indexes of matching results are required */
			if (getAccount(i).getAccBalance() == bal) {		
				results[rsize] = getAccount(i);			//add the matching accounts to the results array
				rsize++;								
			}
		}
		
		return results;
	}
	
	
	/** 
	* Returns an array of all Accounts in the bank 
	* @return an array of Accounts
	*/
	
	public Account [] getAllAccounts() {
		Account [] accountsArray = new Account[getCount()];
		for (int i = 0; i < getCount(); i++) {
			accountsArray[i] = getAccount(i);
		}
		return accountsArray;
	}
	
	
	
	
	
	/** 
	* Returns an array of all Accounts in the bank 
	* @param accountName: the name of the Account(s) to search for
	* @return an (array of) Account(s)
	*/
	public Account [] searchByAccountName(String accountName) {
		
		int rCount = 0;
		for (int i = 0; i < getCount(); i++) {
			if (accounts.get(i).getFullName().equals(accountName)){// || accounts.get(i) instanceof GIC)) {
				rCount++;					//count and/or increment the number of matching accounts
			}
		}
		
		//initialize an array of Account objects of matching accounts size (could be 0, if no matches were found)
		Account [] results = new Account[rCount];		
		
		int rsize =0;
		for (int i = 0; i < getCount(); i++) {
			if (accounts.get(i).getFullName() == accountName ){// || accounts.get(i) instanceof GIC)) {		
				results[rsize] = accounts.get(i);			//add the matching accounts to the results array
				rsize++;								
			}
		}
		
		return results;
	}
	
}
	
	
