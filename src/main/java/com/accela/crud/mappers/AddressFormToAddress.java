package com.accela.crud.mappers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.accela.crud.models.AddressForm;
import com.accela.crud.respository.Address;

@Component
public class AddressFormToAddress implements Converter<AddressForm, Address> {

	@Override
	public Address convert(AddressForm addressForm) {
		Address address = new Address();
		if (addressForm.getAddressId() != null) {
			address.setAddressId((address.getAddressId()));
		}
		address.setStreet(addressForm.getStreet());
		address.setCity(addressForm.getCity());
		address.setState(addressForm.getState());
		address.setPostalCode(addressForm.getPostalCode());
		address.setPersonId(addressForm.getPersonId());
		return address;
	}
}
