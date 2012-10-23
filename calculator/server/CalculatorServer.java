package server;

import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.xml.ws.Endpoint;

import common.ICalculator;

/**
 * Calculator Server. Starts the RMI Server and SOAP Endpoints.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class CalculatorServer {

	/**
	 * RMI Server URL
	 */
	private String rmiUrl = "rmi://localhost:1099/CalculatorService";

	/**
	 * SOAP Server URL
	 */
	private String soapUrl = "http://localhost:9999/server.calc";

	/**
	 * Constructor
	 */
	public CalculatorServer() {
		startRmi();
		startSoap();
	}

	/**
	 * Starts the SOAP Endpoint by specifying the URL we want the Endpoint to be
	 * deployed to, and what service we want at that endpoint in the publish
	 * method.
	 */
	private void startSoap() {
		try {
			Endpoint.publish(this.soapUrl, new CalculatorImpl());
			System.out.println("SOAP Ready on: " + this.soapUrl + "?wsdl");
		} catch (RemoteException e) {
			System.out.println("SOAP Trouble: " + e);
		}
	}

	/**
	 * Starts the RMI Server
	 */
	private void startRmi() {
		try {
			// Start Server
			java.rmi.registry.LocateRegistry.createRegistry(1099);

			// Create Calculator
			ICalculator c = new CalculatorImpl();

			// Register Calculator
			Naming.rebind(this.rmiUrl, c);
			System.out.println("RMI registry ready on: " + this.rmiUrl);
		} catch (Exception e) {
			System.out.println("RMI Trouble: " + e);
		}
	}

	/**
	 * Server Main Method
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		new CalculatorServer();
	}
}
