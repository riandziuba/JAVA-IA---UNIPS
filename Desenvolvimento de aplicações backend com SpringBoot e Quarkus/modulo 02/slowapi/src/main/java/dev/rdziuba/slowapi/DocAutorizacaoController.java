package dev.rdziuba.slowapi;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class DocAutorizacaoController {
	
	@GetMapping("/api/v1/autorizacao/{cliente}")
	public ResponseEntity<DocAuthorization> autorizar(@PathVariable Long cliente, @RequestParam(name = "servico") Integer servico){
		try {
			Thread.sleep(5000);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		DocAuthorization doc = new DocAuthorization();
		doc.setDateTime(LocalDateTime.now());
		doc.setClientId(cliente);
		doc.setServiceId(servico);
		doc.setAuthorizationKey(generateKey());
		return ResponseEntity.ok(doc);		
	}
	
	private String generateKey() {
		StringBuilder str = new StringBuilder();
		SecureRandom random = new SecureRandom();
		for (int i=0;i<30;i++) {
			str.append(Integer.toHexString(i).toUpperCase()); 
		}
		return str.toString();
	}

}
