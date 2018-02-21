/* BTP400 SCC Assignment 2
Zukhruf Khan 
RemoteBankClient.java 
*/


import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RemoteBankClient
{
	
	public static void displayMenu(String bankName) {
		System.out.println("\nWelcome to " + bankName + " Bank!");
		System.out.println("1. Open an account. \n" 
		+ "2. Close an account. \n"
		+ "3. Search accounts by balance. \n"
		+ "4. Search accounts by name. \n"
		+ "5. Exit. \n");
	}
	
	public static int menuChoice() {
		System.out.println("Please enter your choice: ");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		return choice;
	}
	
	public static void displayAccount(Account account) {
		if (account != null) {
			System.out.println(account);
		} else {
			System.out.println("Account is null!");
		}
		
	}
	
	
	public static void add(RemoteBank bank) throws RemoteException {
		Scanner input = new Scanner(System.in);
		System.out.println("\nPlease enter the account type (SAV/CHQ/GIC): ");
		String type = input.nextLine();
		String info;
		
		if (type.trim().toUpperCase().equals("SAV")) {
			System.out.println("Please enter account information at one line \n" + 
			"(e.g. Doe,John;A1234;1000.00;3.65):");
			info = input.nextLine();
			if (info != null) {
				String [] tokens = info.split(";");
				Savings s = new Savings(tokens[0], tokens[1], Double.parseDouble(tokens[2]), Integer.valueOf(tokens[3]));
				System.out.println("Account full name: " + s.getFullName());
				Account [] results = bank.searchByAccountName(tokens[0]);
				
				if (results.length >= 1) {
					System.out.println("Accountholder already exists. Please try a new account name.");
				} else if (results.length == 0){
				
				bank.addAccount(s);
				}
				
			} else  {
				System.out.println("Incorrect format. Please try again.");
			}
			
		} else if (type.trim().toUpperCase().equals("CHQ")) {
			System.out.println("Please enter account information at one line \n" + 
			"(e.g. Doe,John;A1234;1000.00;0.25;5):");
			info = input.nextLine();
			if (info != null) {
				String [] tokens = info.split(";");
				Chequing c = new Chequing(tokens[0], tokens[1], Double.parseDouble(tokens[2]),  Double.parseDouble(tokens[3]), Integer.valueOf(tokens[4]));
				
				Account [] results = bank.searchByAccountName(tokens[0]);
				if (results.length > 0) {
					System.out.println("Accountholder already exists. Please try a new account name.");
				} else {
				bank.addAccount(c);
				}
			} else  {
				System.out.println("Incorrect format. Please try again.");
			}
			
			
		} else if (type.trim().toUpperCase().equals("GIC")) {
			System.out.println("Please enter account information at one line \n" + 
			"(e.g. Doe,John;A1234;1000.00;5;2.50):");
			info = input.nextLine();
			if (info != null) {
				String [] tokens = info.split(";");
				GIC g = new GIC(tokens[0], tokens[1], Double.parseDouble(tokens[2]), Integer.valueOf(tokens[3]),  Double.parseDouble(tokens[4]));
				
				Account [] results = bank.searchByAccountName(tokens[0]);
				if (results.length > 0) {
					System.out.println("Accountholder already exists. Please try a new account name.");
				} else {
					bank.addAccount(g);
				}
	
			
			} else  {
				System.out.println("Incorrect format. Please try again.");
			}
		}
	}
	
	
	public static void remove(RemoteBank bank) throws RemoteException {
		System.out.println("Please enter the account number of the account you wish to close: ");
		Scanner input = new Scanner(System.in);
		String close = input.nextLine();
		Account a = bank.removeAccount(close);
		
		System.out.println("The account with account number " + close + " has been removed.");
		
	}
	
	
	public static void searchByBalance(RemoteBank bank) throws RemoteException {
			Scanner input = new Scanner(System.in);
		 System.out.println("\nPlease enter an account balance to search all accounts for: ");
			String search = input.nextLine();
			Account [] results = bank.search(Integer.valueOf(search));
			if (results.length > 0) {
				for (int i = 0; i < results.length; i++) {
				 displayAccount(results[i]);
			    }
			} else {
				 System.out.println("No matching accounts found! Please try again!");
			}
		
	}
	
	public static void searchByName(RemoteBank bank) throws RemoteException{
			Scanner input = new Scanner(System.in);
		System.out.println("\nPlease enter an account name (e.g. Mary, Ryan) to search all accounts for: ");
			String search = input.nextLine();
			Account [] results = bank.searchByAccountName(search.trim());
			if (results.length > 0) {
				for (int i = 0; i < results.length; i++) {
				 displayAccount(results[i]);
			    }
			} else {
				 System.out.println("No matching accounts found! Please try again!");
			}
		
	}
	

	

	
	public static void main(String[] args) {


       String url = "rmi://localhost:5678/";

	   try {
			System.out.println( "Running a client application..." );

 			System.out.println( "+++ retrieving bindings from the RMI Registry\n" +
			                    "  + these services have been registered with the RMI Registry:" );
              
			// use of RMI URL
 			//String names[] = Naming.list( "rmi://localhost:5678" );

			// retrieve bindings from the RMI Registry
 		/*	for(int k=0; k < names.length; k++)
    				System.out.println( "....... " + names[k] );*/
 
			System.out.println( "\n+++ get remote references\n" +
			                    "  + bind local object variables to remote objects..." );

            //use of RMI URL
			RemoteBank b1 = (RemoteBank) Naming.lookup( url + "bank1" );

           
			
			Scanner input = new Scanner(System.in);
			//displays the menu, allows for menu options to be chosen until 5 (exit) is entered
			int choice = 0;
			while (choice != 5) {
				displayMenu(b1.getBankName());
				choice = menuChoice();
			
				switch (choice) {
					
					case 1:
					add(b1);
					break;
				
					case 2:
					remove(b1);
					break;
				
					case 3:
					searchByBalance(b1);
					break;
				
					case 4: 
					searchByName(b1);
					break;
				
				}
			
			} 
				

	    }

	   catch( Exception e ) {
				 System.out.println( "Error " + e );
 	   }

	   System.out.println( "\nRMI client: THE END!" );
	}
}

/* lookup
	- look up a remote object
	- obtain the reference to the remote object (i.e. a reference to a stub for the remote object)
	- remote objects are registered with a remot object registry
	  - the Naming class provides methods for storing and obtaining remote references
*/

/* a stub
     - maintain an internal reference to the remote object and
       forward a remote method call throught the remote reference layer
*/
