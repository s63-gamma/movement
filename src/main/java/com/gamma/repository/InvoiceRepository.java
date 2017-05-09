package com.gamma.repository;

import com.gamma.dal.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "invoice", path = "invoice")
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
	List<Invoice> findByOwnerUuid(@Param("owner") UUID owner);
	List<Invoice> findByOwnerEmailadres(@Param("email") String email);
}
