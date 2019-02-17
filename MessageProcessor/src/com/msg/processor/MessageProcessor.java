package com.msg.processor;

import java.io.BufferedReader;
import java.io.FileReader;

import com.msg.processor.ProductSale;

/**
 * @author priyanka
 *
 */
public class MessageProcessor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ProductSale prdsale = new ProductSale();

		// Reading the message notification from the input file and processing it.
		try {
			String msgContent;
			BufferedReader inputFile = new BufferedReader(new FileReader("messageInput/message.txt"));
			  while ((msgContent = inputFile.readLine()) != null) { // Method to processthe message line by line
			  prdsale.processMessage(msgContent); 
			  // Report generated after 10th iteration and stops at 50th iteration and perform adjustment operation
			  prdsale.salesLog.report();
			  }
			 
		} catch (Exception e) {
			System.out.println("Not a Valid Input File");
		}
	}
}