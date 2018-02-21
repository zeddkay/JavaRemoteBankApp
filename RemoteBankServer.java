/* BTP400 SCC Assignment 2
Zukhruf Khan 
RemoteBankServer.java 
*/


import java.rmi.*;
import java.rmi.server.*;

public class RemoteBankServer {
	
	public static void loadBank(RemoteBankImpl bank) throws RemoteException {
		Savings s1 = new Savings("Doe, John", "A1234", 125.25, 15);
		Savings s2 = new Savings("Mary, Ryan", "A007", 1000.00, 25);
		bank.addAccount(s1);
		bank.addAccount(s2);
		Chequing c1 = new Chequing("Doe, John", "B004", 200, 0.30, 8);
		Chequing c2 = new Chequing("Mary, Ryan", "B003", 200, 0.25, 10);
		bank.addAccount(c1);
		bank.addAccount(c2);
		GIC g1 = new GIC("Doe, John", "A4343", 6000, 2, 1.5);
		GIC g2 = new GIC("Mary, Ryan", "A939394", 15000, 4, 2.5);
		bank.addAccount(g1);
		bank.addAccount(g2);
	
	}
	

    public static void main(String[] args) {

		try {
			System.out.println( "Starting a server..." );
	   
			RemoteBankImpl b1 = new RemoteBankImpl();
	   
			loadBank(b1);		//loads the bank with a variety of accounts before sending to client


			System.out.println( "Binding remote objects to rmi registry" );


			// for localhost only
			java.rmi.registry.Registry registry = 
				java.rmi.registry.LocateRegistry.createRegistry(5678);
			
			registry.rebind( "bank1", b1 );

			System.out.println( "These remote objects are waiting for clients..." );
		}

		catch( Exception e ) {
	                        System.out.println( "Error: " + e );
		}

		//System.out.println( "... bye bye" );
		System.out.println( "... the main thread is put into a wait state!!!" );

		/* a separate thread is started after a remote object has been created */
   }
}
