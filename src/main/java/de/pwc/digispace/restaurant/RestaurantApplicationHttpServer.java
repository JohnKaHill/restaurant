package de.pwc.digispace.restaurant;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestaurantApplicationHttpServer extends Thread{

	public static final Logger LOGGER = LoggerFactory.getLogger(RestaurantApplicationHttpServer.class);
	
	static final String HTML_START = "<html><title>HTTP Server in Java</title></html>";
	static final String HTML_END = "</body></html>";
	
	Socket connectedClient = null;
	BufferedReader in = null;
	PrintWriter out = null;
	
	public RestaurantApplicationHttpServer(Socket client) {
		connectedClient = client;
	}
	
	@Override
	public void run() {
		try {
			// Establish connection with DB
			Connection conn = DriverManager.
	            getConnection("jdbc:h2:~/restaurant", "owner", "owner");
			
			LOGGER.info("Client {}:{} is connected.", connectedClient.getInetAddress(), connectedClient.getPort());
			inFromClient = new BufferedReader(new InputStreamReader(connectedClient.getInputStream()));
			outToClient = new DataOutputStream(connectedClient.getOutputStream());
			
			String requestString = inFromClient.readLine();
			String headerLine = requestString;
			
			StringTokenizer tokenizer = new StringTokenizer(headerLine);
			String httpMethod = tokenizer.nextToken();
			String httpQueryString = tokenizer.nextToken();
			
			StringBuffer responseBuffer = new StringBuffer();
			responseBuffer.append("<b> This is the HTTP Server Home Page....</b><BR>");
			responseBuffer.append("The HTTP Client request is  ....<BR>");
			LOGGER.info("The HTTP request string is ...");
			
			while( inFromClient.ready()) {
				//Read the complete HTTP query
				responseBuffer.append(requestString + "<BR>");
				LOGGER.info("{}", requestString);
				requestString = inFromClient.readLine();
			}
			
//			String restMapping
//			switch (httpQueryString) {
//			case value:
//				
//				break;
//
//			default:
//				break;
//			}
			if (httpQueryString.equals("/")) {
				//The default homepage
				sendResponse(200, responseBuffer.toString(), false);
			}
			
			if (httpMethod.equals("GET")){
				if (httpQueryString.equals("/")) {
					//The default homepage
					sendResponse(200, responseBuffer.toString(), false);
				} else {
					// This is interpreted as a filename
					String filename = httpQueryString.replaceFirst("/", "");
					filename = URLDecoder.decode(filename, "UTF-8");
					if (new File(filename).isFile()) {
						sendResponse(200, filename, true);
					} else {
						sendResponse(404, "<b>The Request resource not found ..." + 
								"Usage: http://127.0.0.1:8081 or http://127.0.0.1:8081/</b>", false);
					}
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendResponse(int statusCode, String responseString, boolean isFile) throws Exception {
		String statusLine = null;
		String serverDetails = "Server: Java HTTPServer";
		String contentLengthLine = null;
		String filename = null;
		String contentTypeLine = "Content-Type: text/html" + "\r\n";
		FileInputStream fInputStream = null;
		
		if (statusCode == 200) {
			statusLine = "HTTP/1.1 200 OK" + "\r\n";
		} else {
			statusLine = "HTTP/1.1 404 Not Found" + "\r\n";
		}
		
		if (isFile) {
			filename = responseString;
			fInputStream = new FileInputStream(filename);
			contentLengthLine = "Content-Length: " + Integer.toString(fInputStream.available()) + "\r\n";
			if (!filename.endsWith(".htm") && !filename.endsWith("html")) {
				contentTypeLine = "Content-Length: \r\n";
			}
		} else {
				responseString = RestaurantApplicationHttpServer.HTML_START + responseString + RestaurantApplicationHttpServer.HTML_END;
				contentLengthLine = "Content-Length: " + responseString.length() + "\r\n";
		}
		outToClient.writeBytes(statusLine);
		outToClient.writeBytes(serverDetails);
		outToClient.writeBytes(contentTypeLine);
		outToClient.writeBytes(contentLengthLine);
		outToClient.writeBytes("Connection: close\r\n");
		outToClient.writeBytes("\r\n");
		
		if (isFile) {
			sendFile(fInputStream, outToClient);
		} else {
			outToClient.writeBytes(responseString);
		}
		LOGGER.info("Closing Socket");
//		outToClient.close();		
	}
	
	public void sendFile( FileInputStream fileInputStream, DataOutputStream outputStream) throws Exception {
		byte[] buffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = fileInputStream.read(buffer)) != -1 ) {
			outputStream.write(buffer, 0, bytesRead);
		}
		fileInputStream.close();
	}
	
	public static void main(String[] args) throws Exception{
				
		final int PORT_NUMBER = 8081;
		
		ServerSocket Server = new ServerSocket(PORT_NUMBER, 10, InetAddress.getByName("127.0.0.1"));
		
		LOGGER.info("TCP Server waiting for client on port {}", PORT_NUMBER);
		
		while(true) {
			Socket connected = Server.accept();
			(new RestaurantApplicationHttpServer(connected)).start();
			LOGGER.info("reached end of while-loop!");
		}
		
	}
	
}
