/**
 * 
 */
package com.accela.crud.service;

import java.util.List;

import com.accela.crud.models.AddressForm;
import com.accela.crud.models.PersonForm;
import com.accela.crud.respository.Address;
import com.accela.crud.respository.Person;

/**
 * @author dharm
 *
 */
public interface CrudServiceInterface {

	/* Covert person-form to person type */
	public Person convertPersonForm(PersonForm personForm);

	/* Save or update person details */
	public Person saveOrUpdatePerson(Person person);

	/* Delete person by id */
	public void deletePersonById(Long valueOf);

	/* Delete address by id */
	public void deleteAddressById(Long personId, Long addressId);

	/* List all persons list */
	public List<Person> getAllPersons();

	/* Get persons by Id */
	public Person getPersonById(Long id);

	/* Covert address-form to address type */
	public Address convertAddressForm(AddressForm addressForm);

	/* Save or update address details */
	public Address saveOrUpdateAddress(Address address);

	/* Get address by Id */
	public Address getAddressById(Long id);

	/* List all persons addresses */
	public List<Address> getAllAddresses();

}
