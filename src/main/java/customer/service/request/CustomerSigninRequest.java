package customer.service.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import customer.service.config.WhiteSpaceRemovalDeserializer;

public class CustomerSigninRequest {
	
	@JsonDeserialize(using = WhiteSpaceRemovalDeserializer.class)
	@NotBlank(message = "Please enter Email")
	@Email(message = "Invalid email format")
	private String email;

}
