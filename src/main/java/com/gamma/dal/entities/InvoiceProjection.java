package com.gamma.dal.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.UUID;

/**
 * Created by guushamm on 6-4-17.
 */

@Projection(name = "driver", types = Invoice.class)
public interface InvoiceProjection {
	UUID getUuid();

	Date getDate();
	Double getDistance();
	Double getPriceTotal();
	int getStatus();
	String getPaymentCode();

	@Value(value = "#{target.owner}")
	Owner getOwner();
}
