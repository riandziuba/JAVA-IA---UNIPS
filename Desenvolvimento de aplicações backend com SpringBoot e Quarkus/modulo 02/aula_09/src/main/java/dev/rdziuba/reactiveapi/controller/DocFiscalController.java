package dev.rdziuba.reactiveapi.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.rdziuba.reactiveapi.dto.ProtocolDTO;
import dev.rdziuba.reactiveapi.dto.RequisitionDTO;
import dev.rdziuba.reactiveapi.model.DocFiscal;
import dev.rdziuba.reactiveapi.service.IDocFiscalService;
import reactor.core.publisher.Mono;

@RestController
public class DocFiscalController {
	
	private IDocFiscalService service;

	public DocFiscalController(IDocFiscalService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/search/{protocol}")
	public ResponseEntity<DocFiscal> search(@PathVariable String protocol){
		return ResponseEntity.ok(service.searchForProtocol(protocol));
	}
	
	@PostMapping("/request")
	public Mono<ResponseEntity<ProtocolDTO>> request(@RequestBody RequisitionDTO request){
	   String protocolId = UUID.randomUUID().toString();
	   service.doAuthorizationOnExternalAPI(request.clientId(), request.serviceId(), protocolId);
	   return Mono.just(ResponseEntity.accepted().body(new ProtocolDTO(protocolId)));
	}

}
