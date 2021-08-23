package com.accela.crud.mappers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.accela.crud.models.PersonForm;
import com.accela.crud.respository.Person;

@Component
public class PersonToPersonForm implements Converter<Person, PersonForm> {
	@Override
	public PersonForm convert(Person person) {
		PersonForm personForm = new PersonForm();
		personForm.setId(person.getId());
		personForm.setFirstName(person.getFirstName());
		personForm.setLastName(person.getLastName());
		return personForm;
	}
}
