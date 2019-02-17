/**
 * 
 */
package com.msg.processor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author priyanka
 *
 */
public class ProductSaleLog {
	
	
	// lineitems stores product hashmaps with their product object that contains product details
		
		private HashMap<String, ProductSpecification> lineItems = new HashMap();

		// Used to total the sale value of the product. 
		private double totalSalesValue;

		//  sales messages that are successfully transactioned.
		private ArrayList normalReports;

		//  adjustment reports of the sale transaction.
		private ArrayList adjustmentReports;

		// Constructor
		public ProductSaleLog() {
			this.normalReports = new ArrayList();
			this.adjustmentReports = new ArrayList();
			this.totalSalesValue = 0.0;
		}

		// Get the product from line item based on their type e.g, apple
		public ProductSpecification getProductName(String type) {
			return lineItems.getOrDefault(type, new ProductSpecification(type));
		}

		// Update the line item product with new details.
		public void updateProduct(ProductSpecification product) {
			lineItems.put(product.getProductName(), product);
		}

		// Get all the reports that have been processed so far.
		public ArrayList getNormalReports() {
			return normalReports;
		}

		// Set a sales notice message
		public void setNormalReports(String normalReport) {
			this.normalReports.add(normalReport);
		}

		// Get all the adjustment report as an array list
		public ArrayList getAdjustmentReports() {
			return adjustmentReports;
		}

		// Set an adjustment report string to the adjustmentReports array
		public void setAdjustmentReports(String adjustmentReport) {
			this.adjustmentReports.add(adjustmentReport);
		}

		// return the total sales value
		public double getTotalSalesValue() {
			return totalSalesValue;
		}

		// Append any given value to the totalSalesValue field
		public void appendTotalSalesValue(double productTotalPrice) {
			totalSalesValue += productTotalPrice;
		}

		// Set total sales value with the given value
		public void setTotalSalesValue(double productTotalPrice) {
			totalSalesValue = productTotalPrice;
		}

		/*
		 * Report outputs sales information to system console on every 10th report
		 * iteration using modulo. Displays in a table formatted structure and stops
		 * execution of the application after 50th message iteration.
		 */
		public void report() {

			// Report after 10th iteration and not at the beginning.
			if ((normalReports.size() % 10) == 0 && normalReports.size() != 0) {
				setTotalSalesValue(0.0);
				// System.out.println(normalReports);
				System.out.println("Log for 10 sales message notification");
				System.out.println("*************** Log Report *****************");
				System.out.println("|MessageProcessor.ProductSale           |Quantity   |Value      |");
				lineItems.forEach((k, v) -> formatReports(k, v));
				System.out.println("-------------------------------------------");
				System.out.println(String.format("|%-30s|%-11.2f|", "Total Sales", getTotalSalesValue()));
				System.out.println("-------------------------------------------");
				System.out.println("End\n\n");
				try {
					// Add 2 second pause
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// Report and stop execution after 50th message
			if ((normalReports.size() % 50) == 0 && normalReports.size() != 0) {
				System.out.println(
						"Application reached 50 messages and cannot process further. The following are the adjustment records made;\n");
				// Display all the adjustment reports so far recorded.
				getAdjustmentReports().forEach(System.out::println);
				System.exit(1);
			}
		}

			//format the report in proper structure 
		public void formatReports(String type, ProductSpecification product) {
			String lineItem = String.format("|%-18s|%-11d|%-11.2f|", product.getProductName(), product.getTotalQuantity(),
					product.getTotalPrice());
			appendTotalSalesValue(product.getTotalPrice());
			System.out.println(lineItem);
		}


}
