package com.MicorService.Registration.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MicorService.Registration.CustomAnnotations.RequiresTokenAuthentication;
import com.MicorService.Registration.CustomAnnotations.RequiresTokenAuthorization;
import com.MicorService.Registration.Service.DetailService;
import com.MicorService.Registration.dto.LoginResponse;


@RestController
@RequestMapping("/api")
public class DetailController {

	@Autowired
	private  final DetailService detailService;
	
	public DetailController(DetailService detailService) {
		this.detailService=detailService;
	}
	
	
	@GetMapping("/get/detail")
	@RequiresTokenAuthentication
	public ResponseEntity<?> getDetailOfUserByToken(@RequestHeader("Authorization")String token) {
		LoginResponse loginResponse=this.detailService.getDetailofUser(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(loginResponse);
		
	}
	
	@GetMapping("admin/get/userdetail")
	@RequiresTokenAuthorization
	public ResponseEntity<?> getDetailOfUserByEmail(@RequestHeader("Authorization")String token, @RequestParam("email")String email){
		LoginResponse loginResponse=this.detailService.AuthorizedUser_getDetailOfUserByEmail(email);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(loginResponse);
	}
	
	
}
