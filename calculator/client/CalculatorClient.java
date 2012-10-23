package client;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import common.ICalculator;

// run the program using
// java CalculatorClient <host> <port>
// where the host and port refer to the rmiregistry's host and port

/**
 * Client That Connects to RMI and SOAP.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class CalculatorClient {

	/**
	 * Client Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		callRmi();
		callSoap();
	}

	/**
	 * Calls the SOAP Service
	 */
	private static void callSoap() {
		try {
			// Create URL for SOAP Endpoint WSDL
			URL url = new URL("http://localhost:9999/server.calc?wsdl");

			// Specify The Namespace and Service of the Endpoint
			QName qname = new QName("http://server.calc/",
					"CalculatorImplService");

			// Create the Endpoint Service
			Service service = Service.create(url, qname);

			// Get the Calculator Object from the Service
			ICalculator calc = service.getPort(ICalculator.class);

			// Call the Calculator
			System.out.println("SOAP Sub: " + calc.sub(8, 3));
			System.out.println("SOAP Add: " + calc.add(9, 1));
			
			System.out.println("SOAP Get: " + calc.getNumber());
			
		} catch (MalformedURLException murle) {
			System.out.println("MalformedURLException");
			System.out.println(murle);
		} catch (RemoteException re) {
			System.out.println("RemoteException");
			System.out.println(re);
		}
	}

	/**
	 * Calls the RMI Service
	 */
	private static void callRmi() {
		try {
			// Look up Calculator with RMI
			ICalculator c = (ICalculator) Naming
					.lookup("rmi://localhost:1099/CalculatorService");

			// Use Calculator
			System.out.println("RMI Sub: " + c.sub(4, 3));
			System.out.println("RMI Add: " + c.add(4, 5));
			
			c.setNumber(78);
			System.out.println("RMI Set: 78");
			
		} catch (MalformedURLException murle) {
			System.out.println("MalformedURLException");
			System.out.println(murle);
		} catch (RemoteException re) {
			System.out.println("RemoteException");
			System.out.println(re);
		} catch (NotBoundException nbe) {
			System.out.println("NotBoundException");
			System.out.println(nbe);
		} catch (java.lang.ArithmeticException ae) {
			System.out.println("java.lang.ArithmeticException");
			System.out.println(ae);
		}
	}
}
