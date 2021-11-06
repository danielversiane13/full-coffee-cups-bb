package CustomerFactory;

import br.com.gamabank.bluebank.entities.Address;
import br.com.gamabank.bluebank.forms.AddressForm;

public class AddressFactory {
	
	public static Address create(AddressForm form) {
		return new Address(form.street, form.city, form.state, form.country, form.number, form.zipcode, form.complement, form.neighborhood);
	}
}
