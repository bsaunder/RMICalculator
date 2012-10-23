package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * Calculator Interface for RMI and SOAP Web Services.
 * 
 * The @WebService Annotation states this Interface will be used with JAX-WS to
 * use SOAP. @SOAPBinding(style=Style.RPC) states that the web service uses
 * simple data types like long, integer and boolean.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ICalculator extends Remote {
	public long add(long a, long b) throws RemoteException;

	public long sub(long a, long b) throws RemoteException;
	
	public void setNumber(long a) throws RemoteException;
	
	public long getNumber() throws RemoteException;
}
