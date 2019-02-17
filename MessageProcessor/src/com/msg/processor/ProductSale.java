/**
 * 
 */
package com.msg.processor;

/**
 * @author priyanka
 *
 */
public class ProductSale {
	
	public ProductSaleLog salesLog;

	//price adjustment is made using this object
	private PriceAdjustment priceAdjustment;

	// This holds the product details
	private ProductSpecification productSpecification;

	// Constructor
	public ProductSale() {
		salesLog = new ProductSaleLog();
	}

	//process the message content and set the product details and normal report and update the productdetails
	public boolean processMessage(String msgContent) {

		// MessageTypes helps to process the incoming messages and obtain product sale
		// information.
		MessageTypes messageTypes;

		// Process the given message
		messageTypes = new MessageTypes(msgContent);

		// Get the product name e.g 'apple'
		String productName = messageTypes.getProductName();

		// Check if product type is empty return false and do nothing.
		if (productName.isEmpty()) {
			return false;
		}

		// Returns an existing product else returns a new Product object
		this.productSpecification = salesLog.getProductName(productName);

		// Prepare the product details for adjustment
		this.priceAdjustment = new PriceAdjustment(productSpecification);

		// Set the product details from the message
		this.productSpecification.setProductQuantity(messageTypes.getProductQuantity());
		this.productSpecification.setTotalQuantity(messageTypes.getProductQuantity());
		this.productSpecification.setProductPrice(messageTypes.getProductPrice());
		this.productSpecification.setPriceAdjustmentOperator(messageTypes.getOperatorType());

		// Set the total value of the product.
		setProductTotalPrice();

		// Set the sale log reports
		salesLog.setNormalReports(msgContent);

		// Update the product with the new details
		salesLog.updateProduct(productSpecification);

		return true;
	}

	// Set or Append Total product price based on any adjustment if given.
	private void setProductTotalPrice() {
		double priceAdjusted;
		double productValue;

		if (!productSpecification.getPriceAdjustmentOperator().isEmpty()) {
			priceAdjusted = priceAdjustment.getPriceAdjusted();
			productSpecification.setTotalPrice(priceAdjusted);
			salesLog.setAdjustmentReports(priceAdjustment.adjustmentReport());
		} else {
			productValue = productSpecification.calculatePrice(productSpecification.getProductQuantity(), productSpecification.getProductPrice());
			productSpecification.appendTotalPrice(productValue);
		}
	}



}
