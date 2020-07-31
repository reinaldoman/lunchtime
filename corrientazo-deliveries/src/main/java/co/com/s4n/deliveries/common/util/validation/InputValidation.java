package co.com.s4n.deliveries.common.util.validation;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.com.s4n.deliveries.common.util.properties.Constants;
import co.com.s4n.deliveries.common.util.properties.PropertiesUtil;

public class InputValidation implements IValidation{

	@Override
	public ValidationResult validate(String deliveryInput) {
		String regex = null; 
		try {
			regex = PropertiesUtil.getPropertyValue(Constants.REGEX_INPUT_VALIDATION);
			String regexMaxLength = PropertiesUtil.getPropertyValue(Constants.NUMBER_OF_BLOCKS_AROUND);
			regex = regex.replace("INPUT_PLACEHOLDER_VALUE", regexMaxLength);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
