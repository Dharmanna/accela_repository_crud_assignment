package com.accela.crud.models;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author dharm
 *
 */
@Data
public class AddressForm implements Serializable {

	public Long personId;
	@NotBlank(message = "Street cannot be blank")
	public String street;
	@NotBlank(message = "City cannot be blank")
	public String city;
	@NotBlank(message = "State cannot be blank")
	public String state;
	@NotBlank(message = "Postalcode cannot be blank")
	public String postalCode;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Long addressId;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
