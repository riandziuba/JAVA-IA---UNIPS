package dev.rdziuba.reactiveapi.service;

import dev.rdziuba.reactiveapi.model.DocFiscal;

public interface IDocFiscalService {

	public void doAuthorizationOnExternalAPI(Long idCliente, Integer idServico, String protocolo);
	public DocFiscal searchForProtocol(String protocolo);
}
