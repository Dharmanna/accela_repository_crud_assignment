package com.accela.crud.mappers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.accela.crud.models.AddressForm;
import com.accela.crud.respository.Address;

@Component
public class AddressToAddressForm implements Converter<Address, AddressForm> {

	@Override
	public AddressForm convert(Address address) {
		AddressForm addressForm = new AddressForm();
		addressForm.setAddressId(address.getAddressId());
		addressForm.setPersonId(address.getPersonId());
		addressForm.setStreet(address.getStreet());
		addressForm.setCity(address.getCity());
		addressForm.setState(address.getState());
		addressForm.setPostalCode(address.getPostalCode());
		return addressForm;
	}
}
