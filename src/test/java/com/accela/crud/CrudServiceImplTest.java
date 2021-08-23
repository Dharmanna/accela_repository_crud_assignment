/**
 * 
 */
package com.accela.crud;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.accela.crud.mappers.AddressFormToAddress;
import com.accela.crud.mappers.PersonFormToPerson;
import com.accela.crud.models.AddressForm;
import com.accela.crud.models.PersonForm;
import com.accela.crud.respository.Address;
import com.accela.crud.respository.AddressRepository;
import com.accela.crud.respository.Person;
import com.accela.crud.respository.PersonRepository;
import com.accela.crud.service.CrudServiceInterface;
import com.accela.crud.service.impl.CrudServiceImpl;

/**
 * @author dharm
 *
 */

@SpringBootTest(classes = CrudServiceImpl.class)
public class CrudServiceImplTest {
	@MockBean
	PersonRepository personRepository;

	@MockBean
	AddressRepository addressRepository;

	@MockBean
	private PersonFormToPerson personFormToPerson;
	@MockBean
	private AddressFormToAddress addressFormToAddress;

	@Autowired
	private CrudServiceInterface crudService;

	@Test
	public void convertPersonFormTest() {

		Person person = getSinglePerson();
		PersonForm personForm = new PersonForm();
		// mock in-memory database call
		doReturn(person).when(personFormToPerson).convert(personForm);

		Person savedPerson = crudService.convertPersonForm(personForm);
		assertEquals(savedPerson.getFirstName(), person.getFirstName());
		assertEquals(savedPerson.getLastName(), person.getLastName());
	}

	@Test
	public void savOrUpdatePersonTest() {

		Person person = getSinglePerson();
		// mock in-memory database call
		doReturn(person).when(personRepository).save(person);

		Person savedPerson = crudService.saveOrUpdatePerson(person);
		assertEquals(savedPerson.getFirstName(), person.getFirstName());
		assertEquals(savedPerson.getLastName(), person.getLastName());
	}

	@Test
	public void getAllPersonsTest() {

		List<Person> personList = getListOfPersons();
		personRepository.saveAll(personList);

		doReturn(personList).when(personRepository).findAll();
		// when
		List<Person> actualPersons = crudService.getAllPersons();
		// then
		assertThat(actualPersons).isEqualTo(personList);
		assertEquals(actualPersons.size(), personList.size());
	}

	@Test
	public void getPersonByIdTest() {

		Optional<Person> person = Optional.of(getSinglePerson());

		// mock in-memory database call
		doReturn(person).when(personRepository).findById(1l);

		// when
		Person savedPerson = crudService.getPersonById(1l);
		// then
		assertEquals(savedPerson.getFirstName(), person.get().getFirstName());
		assertEquals(savedPerson.getLastName(), person.get().getLastName());
		assertEquals(savedPerson.getId(), person.get().getId());
	}

	@Test
	public void deletePersonByIdTest() {
		List<Person> personList = new ArrayList<Person>();
		// mock in-memory database call
		doReturn(personList).when(personRepository).findAll();
		// when
		crudService.deletePersonById(1l);
		List<Person> afterDeletionPersonList = crudService.getAllPersons();
		// then
		assertEquals(afterDeletionPersonList.size(), personList.size());
	}

	@Test
	public void convertAddressFormTest() {
		Address address = getSingleAddress();
		AddressForm addressForm = new AddressForm();
		// mock in-memory database call
		doReturn(address).when(addressFormToAddress).convert(addressForm);

		Address savedAddress = crudService.convertAddressForm(addressForm);
		assertEquals(savedAddress.getCity(), address.getCity());
		assertEquals(savedAddress.getPostalCode(), address.getPostalCode());
	}

	@Test
	public void saveOrUpdateAddressTest() {

		Address address = getSingleAddress();
		// mock in-memory database call
		doReturn(address).when(addressRepository).save(address);

		Address savedAddress = crudService.saveOrUpdateAddress(address);
		assertEquals(savedAddress.getCity(), address.getCity());
		assertEquals(savedAddress.getPostalCode(), address.getPostalCode());
	}

	@Test
	public void getAllAddressesTest() {

		List<Address> addressList = getListOfAddress();
		addressRepository.saveAll(addressList);

		doReturn(addressList).when(addressRepository).findAll();
		// when
		List<Address> actualAddress = crudService.getAllAddresses();
		// then
		assertThat(actualAddress).isEqualTo(addressList);
		assertEquals(actualAddress.size(), addressList.size());
	}

	@Test
	public void getAddressByIdTest() {

		Optional<Address> address = Optional.of(getSingleAddress());

		// mock in-memory database call
		doReturn(address).when(addressRepository).findById(1l);

		// when
		Address savedAddress = crudService.getAddressById(1l);
		// then
		assertEquals(savedAddress.getCity(), address.get().getCity());
		assertEquals(savedAddress.getPostalCode(), address.get().getPostalCode());
		assertEquals(savedAddress.getAddressId(), address.get().getAddressId());
	}

	@Test
	public void deleteAddressByIdTest() {
		List<Address> addressList = new ArrayList<Address>();
		// mock in-memory database call
		doReturn(addressList).when(addressRepository).findAll();
		// when
		crudService.deleteAddressById(1l, 1l);
		List<Address> afterDeletionAddressList = crudService.getAllAddresses();
		// then
		assertEquals(afterDeletionAddressList.size(), addressList.size());
	}

	private Address getSingleAddress() {
		Address address = new Address();
		address.setAddressId(1l);
		address.setStreet("Street");
		address.setCity("City");
		address.setState("State");
		address.setPostalCode("D16");
		address.setPersonId(1l);
		return address;
	}

	private List<Address> getListOfAddress() {
		List<Address> addList = new ArrayList<Address>();
		Address address = new Address();
		address.setAddressId(1l);
		address.setStreet("Street");
		address.setCity("City");
		address.setState("State");
		address.setPostalCode("D16");
		address.setPersonId(1l);

		Address a2 = new Address();
		a2.setAddressId(2l);
		a2.setStreet("Street2");
		a2.setCity("City2");
		a2.setState("State2");
		a2.setPostalCode("D162");
		a2.setPersonId(2l);

		addList.add(address);
		addList.add(a2);
		return addList;
	}

	private Person getSinglePerson() {
		Person person = new Person();
		person.setId(1l);
		person.setFirstName("Thomas");
		person.setLastName("Shelby");
		return person;
	}

	private List<Person> getListOfPersons() {
		List<Person> perList = new ArrayList<Person>();

		Person per = new Person();
		per.setId(1l);
		per.setFirstName("Cillian");
		per.setLastName("Murphy");

		Person per2 = new Person();
		per2.setId(2l);
		per2.setFirstName("Thomas");
		per2.setLastName("Shelby");

		perList.add(per);
		perList.add(per2);
		return perList;
	}
}
