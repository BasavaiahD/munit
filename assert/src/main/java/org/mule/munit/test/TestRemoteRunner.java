package org.mule.munit.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.mule.munit.test.result.TestResult;
import org.mule.munit.test.result.notification.NotificationListener;


public class TestRemoteRunner {
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message;

	
	public void run(int port, String path, String resource)
	{
		try{
			//1. creating a socket to connect to the server
			requestSocket = new Socket("localhost", port);
			System.out.println("Connected to localhost in port "+ port);
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			
			MunitTestRunner runner = new MunitTestRunner(resource);
			
			runner.setNotificationListener(new NotificationListener() {
				
				public void notifyStartOf(Test test) {
					try {
						out.writeObject("1;"+test.getName());
						out.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				
				public void notify(TestResult testResult) {
					try {
						if( testResult.getError() != null )
						{
							out.writeObject("3;" + testResult.getTestName()+ ";'"+testResult.getFailure().getFullMessage()+"'");
							out.flush();
						}
						else if( testResult.getFailure() != null )
						{
							out.writeObject("2;" + testResult.getTestName() + ";'"+testResult.getFailure().getFullMessage()+"'");
							
							out.flush();
						}
						else{
							out.writeObject("4;" + testResult.getTestName());
							out.flush();	
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			out.writeObject("0;" + runner.getNumberOfTests());
			out.writeObject("5;" + path);
			runner.run();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			//4: Closing connection
			try{
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
 	
	public static void main(String args[])
	{
		String resource = null;
		int port = -1;
		String path = null;
		for ( int i=0; i<args.length; i++ )
		{
			if ( args[i].equalsIgnoreCase("-resource"))
			{
				resource = args[i+1];
			}
			if ( args[i].equalsIgnoreCase("-port"))
			{
				port = Integer.valueOf(args[i+1]);
			}	
			if ( args[i].equalsIgnoreCase("-path"))
			{
				path = args[i+1];
			}
		}
		TestRemoteRunner server = new TestRemoteRunner();
		server.run(port, path, resource);
		
	}
	
}
