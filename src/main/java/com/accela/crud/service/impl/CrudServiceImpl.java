/**
 * 
 */
package com.accela.crud.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accela.crud.mappers.AddressFormToAddress;
import com.accela.crud.mappers.PersonFormToPerson;
import com.accela.crud.models.AddressForm;
import com.accela.crud.models.PersonForm;
import com.accela.crud.respository.Address;
import com.accela.crud.respository.AddressRepository;
import com.accela.crud.respository.Person;
import com.accela.crud.respository.PersonRepository;
import com.accela.crud.service.CrudServiceInterface;

/**
 * @author dharm
 *
 */
@Service
public class CrudServiceImpl implements CrudServiceInterface {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private PersonFormToPerson personFormToPerson;
	@Autowired
	private AddressFormToAddress addressFormToAddress;


	@Override
	public Person convertPersonForm(PersonForm personForm) {
		Person savedPerson = saveOrUpdatePerson(personFormToPerson.convert(personForm));
		return savedPerson;
	}

	@Override
	public Person saveOrUpdatePerson(Person person) {
		personRepository.save(person);
		return person;
	}

	@Override
	public Person getPersonById(Long id) {
		Optional<Person> optional = personRepository.findById(id);
		return optional.get();
	}

	@Override
	public List<Person> getAllPersons() {
		List<Person> persons = new ArrayList<>();
		personRepository.findAll().forEach(persons::add);
		return persons;
	}

	@Override
	public void deletePersonById(Long id) {
		personRepository.deleteById(id);
	}

	@Override
	public Address convertAddressForm(AddressForm addressForm) {
		Address savedAddress = saveOrUpdateAddress(addressFormToAddress.convert(addressForm));
		return savedAddress;
	}

	@Override
	public Address saveOrUpdateAddress(Address address) {
		addressRepository.save(address);
		return address;
	}

	@Override
	public List<Address> getAllAddresses() {
		List<Address> addresses = new ArrayList<>();
		addressRepository.findAll().forEach(addresses::add);
		return addresses;
	}

	@Override
	public Address getAddressById(Long id) {
		Optional<Address> optional = addressRepository.findById(id);
		return optional.get();
	}

	@Override
	public void deleteAddressById(Long personId, Long addressId) {
		addressRepository.deleteById(addressId);

	}

}
