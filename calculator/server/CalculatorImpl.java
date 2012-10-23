package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.jws.WebService;

import common.ICalculator;

/**
 * Implementation of Calculator Interface.
 * 
 * The @WebService Annotation states this Interface will be used with JAX-WS to
 * use SOAP. the endpointInterface Attribute specifies the Interface this class
 * is the implementation for. the targetNamespace Attribute sets the Namespace
 * for this Endpoint.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
@WebService(endpointInterface = "common.ICalculator", targetNamespace = "http://server.calc/")
public class CalculatorImpl extends UnicastRemoteObject implements ICalculator {

	/**
	 * Default Serial ID.
	 */
	private static final long serialVersionUID = 1L;
	
	private static long NUMBER = -1;

	/**
	 * Explicit Constructor.
	 * 
	 * Implementations must have an explicit constructor in order to declare the
	 * RemoteException exception
	 * 
	 * @throws RemoteException
	 */
	public CalculatorImpl() throws RemoteException {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long add(long a, long b) throws RemoteException {
		return a + b;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long sub(long a, long b) throws RemoteException {
		return a - b;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNumber(long a) throws RemoteException {
		NUMBER = a;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getNumber() throws RemoteException {
		return NUMBER;
	}

}
