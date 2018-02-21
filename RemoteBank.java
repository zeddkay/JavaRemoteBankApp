/* BTP400 SCC Assignment 2
Zukhruf Khan 
RemoteBank.java 
*/

import java.rmi.*;

public interface RemoteBank extends Remote
{

	public Account getAccount(int index) throws RemoteException;
		
	public String getBankName() throws RemoteException;

	public boolean addAccount(Account add)  throws RemoteException;
	
	public Account removeAccount(String accNum) throws RemoteException;
	
	public Account[] search(int bal) throws RemoteException;

	public Account [] searchByAccountName(String accountName) throws RemoteException;
	
}
