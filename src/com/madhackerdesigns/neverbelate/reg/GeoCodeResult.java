package com.madhackerdesigns.neverbelate.reg;

public class GeoCodeResult {
	
	private String countryCode;
	private String zipCode;
	
	/**
	 * @return the countryCode
	 */
	protected String getCountryCode() {
		return countryCode;
	}
	
	/**
	 * @param countryCode the countryCode to set
	 */
	protected void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	/**
	 * @return the zipCode
	 */
	protected String getZipCode() {
		return zipCode;
	}
	
	/**
	 * @param zipCode the zipCode to set
	 */
	protected void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.countryCode + " " + this.zipCode;
	}

}
