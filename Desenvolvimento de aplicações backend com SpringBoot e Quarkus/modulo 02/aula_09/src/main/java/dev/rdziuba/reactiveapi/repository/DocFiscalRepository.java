package dev.rdziuba.reactiveapi.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import dev.rdziuba.reactiveapi.model.DocFiscal;

public interface DocFiscalRepository extends ListCrudRepository<DocFiscal, Integer>{
	public Optional<DocFiscal> findByProtocol(String protocol);
}
