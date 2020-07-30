package co.com.s4n.deliveries.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidation implements IValidation{

	@Override
	public ValidationResult validate(String deliveryInput) {
		// TODO Auto-generated method stub
		String regex = "^[A|I|D]{1,10}$"; //TODO: load regex from properties
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(deliveryInput);
		boolean matchResult = matcher.matches();
		ValidationResult validationResult = new ValidationResult();
		validationResult.setValid(matchResult);
		if(matchResult)
			validationResult.setMessage("SUCCESS");
		else
			validationResult.setMessage("FAILURE");
		return validationResult;
	}

}
