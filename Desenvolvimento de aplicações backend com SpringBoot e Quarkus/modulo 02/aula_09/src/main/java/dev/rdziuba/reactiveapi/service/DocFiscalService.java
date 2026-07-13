package dev.rdziuba.reactiveapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import dev.rdziuba.reactiveapi.model.DocFiscal;
import dev.rdziuba.reactiveapi.repository.DocFiscalRepository;

@Service
public class DocFiscalService implements IDocFiscalService{

	private DocFiscalRepository repository;
	private WebClient webClient;
	
	public DocFiscalService(DocFiscalRepository repository, WebClient webClient) {
		super();
		this.repository = repository;
		this.webClient = webClient;
	}

	@Override
	public void doAuthorizationOnExternalAPI(Long clientId, Integer serviceId, String protocol) {
		System.out.println("DEBUG = ClientID="+clientId);
		System.out.println("DEBUG = ServiceID="+serviceId);
		webClient.get()
			     .uri("http://localhost:8090/api/v1/autorizacao/"+clientId+"?servico="+serviceId)
			     .retrieve()
			     .bodyToMono(String.class)
			     .doOnNext((response)->{
			    	 System.out.println("DEBUG - Solicitacao atendida pela API Externa");
			    	 DocFiscal document = new DocFiscal();
			    	 document.setProtocol(protocol);
			    	 document.setDocument(response);
			    	 repository.save(document);
			     })
			     .doOnError(erro -> {
			    	 System.out.println("ERRO - "+erro);
			     })
			     .subscribe();
	}

	@Override
	public DocFiscal searchForProtocol(String protocol) {
		return repository.findByProtocol(protocol).orElse(null);
	}

}
