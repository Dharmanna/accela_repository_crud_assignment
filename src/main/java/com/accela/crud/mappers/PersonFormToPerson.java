package com.accela.crud.mappers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.accela.crud.models.PersonForm;
import com.accela.crud.respository.Person;

@Component
public class PersonFormToPerson implements Converter<PersonForm, Person> {

	@Override
	public Person convert(PersonForm personForm) {
		Person person = new Person();
		if (personForm.getId() != null) {
			person.setId(personForm.getId());
		}
		person.setFirstName(personForm.getFirstName());
		person.setLastName(personForm.getLastName());
		return person;
	}
}
