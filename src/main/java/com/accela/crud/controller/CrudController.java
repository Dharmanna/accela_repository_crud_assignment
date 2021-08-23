/**
 * 
 */
package com.accela.crud.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.accela.crud.mappers.AddressToAddressForm;
import com.accela.crud.mappers.PersonToPersonForm;
import com.accela.crud.models.AddressForm;
import com.accela.crud.models.PersonForm;
import com.accela.crud.respository.Address;
import com.accela.crud.respository.Person;
import com.accela.crud.service.CrudServiceInterface;

/**
 * @author dharm
 *
 */
@Controller
public class CrudController {

	@Autowired
	private CrudServiceInterface crudService;

	@Autowired
	private CrudServiceInterface personService;
	@Autowired
	private PersonToPersonForm personToPersonForm;

	@Autowired
	private AddressToAddressForm addressToAddressForm;

	/*
	 * All processing starts from here
	 */
	@RequestMapping("/")
	public String redirecToListOfPerson() {
		return "/person/list";
	}

	/*
	 * Add new person request's handler
	 */
	@RequestMapping("/person/new")
	public String newPerson(Model model) {
		model.addAttribute("personForm", new PersonForm());
		return "/person/personform";
	}

	/*
	 * Save new person to in-memory DB and show saved person details
	 */
	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public String saveOrUpdatePerson(@Valid PersonForm personForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/person/personform";
		}
		Person savedPerson = crudService.convertPersonForm(personForm);
		return "redirect:/person/show/" + savedPerson.getId();
	}

	/*
	 * View particular person details from in-memory DB
	 */
	@RequestMapping("/person/show/{id}")
	public String getPerson(@PathVariable String id, Model model) {
		model.addAttribute("person", crudService.getPersonById(Long.valueOf(id)));
		return "person/show";
	}

	/*
	 * View all person's details from in-memory DB
	 */
	@RequestMapping({ "/person/list", "/person" })
	public String listOfPersons(Model model) {
		model.addAttribute("persons", personService.getAllPersons());
		model.addAttribute("numberOfPersons", personService.getAllPersons().size());
		return "/person/list";
	}

	/*
	 * Edit person's details from in-memory DB
	 */
	@RequestMapping("person/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		Person person = crudService.getPersonById(Long.valueOf(id));
		PersonForm personForm = personToPersonForm.convert(person);

		model.addAttribute("personForm", personForm);
		return "person/personform";
	}

	/*
	 * Edit person's details from in-memory DB
	 */
	@RequestMapping("/person/delete/{id}")
	public String delete(@PathVariable String id) {
		personService.deletePersonById(Long.valueOf(id));
		return "redirect:/person/list";
	}

	/*
	 * Add new address request's handler
	 */
	@RequestMapping("/person/addAddress/{personId}")
	public String newAddress(@PathVariable String personId, Model model) {
		AddressForm addressForm = new AddressForm();
		addressForm.setPersonId(Long.parseLong(personId));
		model.addAttribute("addressForm", addressForm);
		return "/person/addressform";
	}

	/*
	 * Edit person's address details from in-memory DB
	 */
	@RequestMapping("person/editAddress/{id}")
	public String editAddress(@PathVariable String id, Model model) {
		Address address = crudService.getAddressById(Long.valueOf(id));
		AddressForm addressForm = addressToAddressForm.convert(address);

		model.addAttribute("addressForm", addressForm);
		return "person/addressform";
	}

	/*
	 * Save new person to in-memory DB and show saved person details
	 */
	@RequestMapping(value = "/address", method = RequestMethod.POST)
	public String saveOrUpdateAddress(@Valid AddressForm addressForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "person/addressform";
		}
		if (addressForm.getAddressId() != null) {
			Address existingAddress = crudService.getAddressById(addressForm.getAddressId());
			existingAddress.setAddressId(addressForm.getAddressId());
			existingAddress.setPersonId(addressForm.getPersonId());
			existingAddress.setStreet(addressForm.getStreet());
			existingAddress.setCity(addressForm.getCity());
			existingAddress.setState(addressForm.getState());
			existingAddress.setPostalCode(addressForm.getPostalCode());
			crudService.saveOrUpdateAddress(existingAddress);
		} else {
			crudService.convertAddressForm(addressForm);
		}

		return "redirect:/person/list";
	}

	/*
	 * View all person's details from in-memory DB
	 */
	@RequestMapping({ "/person/listAddress/{pid}" })
	public String listOfAddresss(@PathVariable String pid, Model model) {

		List<Address> fullListAddress = crudService.getAllAddresses();
		List<Address> personAddressList = new ArrayList<Address>();
		for (Address address : fullListAddress) {
			if (address.getPersonId() == Long.parseLong(pid)) {
				personAddressList.add(address);
			}
		}
		model.addAttribute("addresses", personAddressList);
		model.addAttribute("numberOfAddresses", personAddressList.size());
		return "/person/listaddress";
	}

	/*
	 * Delete person's address from in-memory DB
	 */
	@RequestMapping("/person/deleteAddress/{aid}/{pid}")
	public String deleteAddress(@PathVariable String aid, @PathVariable String pid) {
		crudService.deleteAddressById(Long.valueOf(pid), Long.valueOf(aid));
		return "redirect:/person/list";
	}

}
