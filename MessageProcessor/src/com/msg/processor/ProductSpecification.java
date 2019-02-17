/**
 * 
 */
package com.msg.processor;

/**
 * @author priyanka
 *
 */
public class ProductSpecification {
	
		// product sale entry price value
		private double productPrice;

		// product sale entry quantity e.g apples = 1
		private int productQuantity;

		//  product sale entry adjustment request e.g., Add or Subtract or Multiply
		private String priceAdjustmentOperator;

		//  product sale entry type e.g apples or oranges, etc.
		private String productName;

		// total product type quantity e.g., apples = 20;
		private int totalQuantity;

		// total price of product e.g., apples = 6.300000;
		private double totalPrice;
		
		// Constructor
		public ProductSpecification(String type) {
			this.totalPrice = 0.0;
			this.totalQuantity = 0;
			this.productName = type;
			this.priceAdjustmentOperator = null;
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
		 * @return the priceAdjustmentOperator
		 */
		public String getPriceAdjustmentOperator() {
			return priceAdjustmentOperator;
		}

		/**
		 * @param priceAdjustmentOperator the priceAdjustmentOperator to set
		 */
		public void setPriceAdjustmentOperator(String priceAdjustmentOperator) {
			this.priceAdjustmentOperator = priceAdjustmentOperator;
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
		 * @return the totalQuantity
		 */
		public int getTotalQuantity() {
			return totalQuantity;
		}

		/**
		 * @param totalQuantity the totalQuantity to set
		 */
		public void setTotalQuantity(int totalQuantity) {
			this.totalQuantity += totalQuantity;
		}

		/**
		 * @return the totalPrice
		 */
		public double getTotalPrice() {
			return totalPrice;
		}

		/**
		 * @param totalPrice the totalPrice to set
		 */
		public void setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
		}
		
		// Calculate the given quantity with given price and return the value
		public double calculatePrice(int productQuantity, double productPrice) {
			return productQuantity * productPrice;
		}
		
		// Add the given value to the existing total price of the requested product.
		public void appendTotalPrice(double productPrice) {
			this.totalPrice += productPrice;
		}
		


}
