/**
 * 
 */
package com.test;

import org.junit.Test;

import com.msg.processor.MessageTypes;

import junit.framework.TestCase;

/**
 * @author kartg
 *
 */
public class MessageTypeTest extends TestCase {
	
	@Test
	public void testMessageType1Validation() {
		MessageTypes msgtype = new MessageTypes("apple at 10p");
		boolean tstMsg=false;
		tstMsg= msgtype.messageValidation("apple at 10p");
		assert(tstMsg!=false);
	}
	
	
	@Test
	public void testMessageType2Validation() {
		MessageTypes msgtype = new MessageTypes("20 sales of apples at 10p each");
		boolean tstMsg=false;
		tstMsg= msgtype.messageValidation("20 sales of apples at 10p each");
		assert(tstMsg!=false);
	}
	
	@Test
	public void testMessageType3Validation() {
		MessageTypes msgtype = new MessageTypes("Subtract 5p cherries");
		boolean tstMsg=false;
		tstMsg= msgtype.messageValidation("Subtract 5p cherries");
		assert(tstMsg!=false);
	}
	
	@Test
	public void testInvalidMsgValidation() {
		MessageTypes msgtype = new MessageTypes("Subtract 5p cherries");
		boolean tstMsg=false;
		tstMsg= msgtype.messageValidation(" ");
		assert(tstMsg!=false);
	}
	
	@Test
	public void testProductTypeValidation() {
		MessageTypes msgtype = new MessageTypes("Subtract 5p cherries");
		String tstMsg;
		tstMsg= msgtype.productType("apple");
		assert(tstMsg.equals("apples"));
	}

	@Test
	public void testOtherProductTypeValidation() {
		MessageTypes msgtype = new MessageTypes("Subtract 5p cherries");
		String tstMsg;
		tstMsg= msgtype.productType("mango");
		assert(tstMsg.equals("mangoes"));
	}

}
