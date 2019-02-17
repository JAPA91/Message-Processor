/**
 * 
 */
package com.msg.processor;

/**
 * @author priyanka
 *
 */
public class MessageTypes {
	
	// product name
	private String productName;

	//  product price
	private double productPrice;

	//  product quantity
	private int productQuantity;

	//  product operatorType e.g Add, Subtract
	private String operatorType;

	/**
	 * @return the productQuantity
	 */
	public int getProductQuantity() {
		return productQuantity;
	}

	/**
	 * @param productQuantity the productQuantity to set
	 */
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	/**
	 * @return the productPrice
	 */
	public double getProductPrice() {
		return productPrice;
	}

	/**
	 * @param productPrice the productPrice to set
	 */
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	

	/**
	 * @return the operatorType
	 */
	public String getOperatorType() {
		return operatorType;
	}

	/**
	 * @param operatorType the operatorType to set
	 */
	
	  public void setOperatorType(String operatorType) { 
		  this.operatorType = operatorType;
		  }
	 

	public MessageTypes(String msgContent) {
		this.productName = "";
		this.productPrice = 0.0;
		this.productQuantity = 0;
		this.operatorType = "";
		messageValidation(msgContent);
	}


	// Validates the message and differentiate the message type and set the values from array to the product name,price and quantiy
	public boolean messageValidation(String msgContent) {
		if (msgContent == null || msgContent.isEmpty()) {
			return false;
		}
		String[] messageArray = msgContent.trim().split("\\s+");
		String msgFirstWord = messageArray[0];
		if (msgFirstWord.matches("Add|Subtract|Multiply")) {
			return messageType3(messageArray);
		} else if (msgFirstWord.matches("^\\d+")) {
			return messageType2(messageArray);
		} else if (messageArray.length == 3 && messageArray[1].contains("at")) {
			return messageType1(messageArray);
		} else {
			System.out.println("Wrong sales notice");
		}
		return true;
	}

	//  message type 1
	private boolean messageType1(String[] messageArray) {
		if (messageArray.length > 3 || messageArray.length < 3)
			return false;
		productName = productType(messageArray[0]);
		productPrice = productPrice(messageArray[2]);
		productQuantity = 1; // Will always be 1
		return true;
	}

	//  message type 2
	private boolean messageType2(String[] messageArray) {
		if (messageArray.length > 7 || messageArray.length < 7)
			return false;
		productName = productType(messageArray[3]);
		productPrice = productPrice(messageArray[5]);
		productQuantity = Integer.parseInt(messageArray[0]);
		return true;
	}

	//  message type 3
	private boolean messageType3(String[] messageArray) {
		if (messageArray.length > 3 || messageArray.length < 3)
			return false;
		operatorType = (messageArray[0]);
		productName = productType(messageArray[2]);
		productQuantity = 0;
		productPrice = productPrice(messageArray[1]);
		return true;
	}

	// To handle the plural cases of the fruit products assuming the sample message taken as inpurt
	//e.g 'cherry' will become 'cherries'
	public String productType(String rawType) {
		String productType = "";
		String typeWithoutLastChar = rawType.substring(0, rawType.length() - 1);
		// enum DEPREC
		if (rawType.endsWith("o")) {
			productType = String.format("%soes", typeWithoutLastChar);
		} else if (rawType.endsWith("y")) {
			productType = String.format("%sies", typeWithoutLastChar);
		} else if (rawType.endsWith("h")) {
			productType = String.format("%shes", typeWithoutLastChar);
		} else if (!rawType.endsWith("s")) {
			productType = String.format("%ss", rawType);
		} else {
			productType = String.format("%s", rawType);
		}
		return productType.toLowerCase();
	}

	// parsing string to double the Price and divide by 100 for 20p to 0.2p
	public double productPrice(String rawPrice) {
		double price = Double.parseDouble(rawPrice.replaceAll("£|p", ""));
		if (!rawPrice.contains(".")) {
			price = Double.valueOf(Double.valueOf(price) / Double.valueOf("100"));
		}
		return price;
	}


}
