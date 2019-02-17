/**
 * 
 */
package com.msg.processor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author priyanka
 *
 */
public class PriceAdjustment {

	// adjustedPrice holds the adjusted price value
		private double priceAdjusted;

		// product holds the Product object.
		private ProductSpecification productSpec;

		// Constructor takes Product as argument.
		public PriceAdjustment(ProductSpecification productSpec) {
			this.productSpec = productSpec;
			this.priceAdjusted = 0.0;
		}

		/*
		 * 
		 * 
		 * @returns adjusted price value
		 */
		public double getPriceAdjusted() {
			String adjustmentMethod = String.format("%sPrice", productSpec.getPriceAdjustmentOperator().toLowerCase());
			try {
				Method method = this.getClass().getMethod(adjustmentMethod, null);
				method.invoke(this, null);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return priceAdjusted;
		}

		// Adds product totalprice with the requested price value.
		public void addPrice() {
			this.priceAdjusted = this.productSpec.getTotalPrice()
					+ (this.productSpec.getTotalQuantity() * this.productSpec.getProductPrice());
		}

		//  Subtracts product totalprice with the requested price value
		public void subtractPrice() {
			this.priceAdjusted = this.productSpec.getTotalPrice()
					- (this.productSpec.getTotalQuantity() * this.productSpec.getProductPrice());
		}

		//  Multiplies product total price and quantity with the
		// requested price and appends to existing total value.
		public void multiplyPrice() {
			this.priceAdjusted = this.productSpec.getTotalPrice()
					+ (this.productSpec.getTotalPrice() * this.productSpec.getProductPrice())
					+ (this.productSpec.getTotalQuantity() * this.productSpec.getProductPrice());
		}

		//  e.g "Performed Add 20p to 21 apples and price adjusted from
		// 2.10p to 6.30p"
		public String adjustmentReport() {
			String adjustmentReport = String.format("Performed %s %.2fp to %d %s and price adjusted from %.2fp to %.2fp",
					this.productSpec.getPriceAdjustmentOperator(), this.productSpec.getProductPrice(), this.productSpec.getTotalQuantity(),
					this.productSpec.getProductName(), this.productSpec.getTotalPrice(), this.priceAdjusted);
			return adjustmentReport;
		}
	
}
