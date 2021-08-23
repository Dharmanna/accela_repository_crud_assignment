/**
 * 
 */
package com.accela.crud.models;

import javax.validation.constraints.NotBlank;

/**
 * @author dharm
 *
 */
public class PersonForm {
	private Long id;
	@NotBlank(message = "First Name cannot be blank")
	private String firstName;
	@NotBlank(message = "Last Name cannot be blank")
	private String lastName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
