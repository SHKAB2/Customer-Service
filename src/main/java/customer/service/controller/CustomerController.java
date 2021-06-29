package customer.service.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import customer.service.request.CustomerSigninRequest;
import customer.service.view.ViewResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class CustomerController {

	

	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, response = ViewResponse.class, message = "Audit info fetched successfully") })
	@PostMapping(value = "/signin", consumes  = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ViewResponse> signIn(@RequestBody CustomerSigninRequest request) {
		ViewResponse response= new ViewResponse();
		response.setId(1);
		response.setMessage("Customer signin api");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}