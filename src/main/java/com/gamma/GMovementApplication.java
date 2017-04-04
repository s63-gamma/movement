package com.gamma;

import com.gamma.dal.entities.Car;
import com.gamma.dal.entities.Invoice;
import com.gamma.repository.CarRepository;
import com.gamma.repository.InvoiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class GMovementApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GMovementApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GMovementApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(InvoiceRepository invoiceRepository, CarRepository carRepository) {
		return (args) -> {
			ArrayList<Invoice> invoices = new ArrayList<>();
			invoices.add(new Invoice(new Date(), 0, 200, 0, "Hallo"));
			invoices.add(new Invoice(new Date(), 0, 40, 1, "Hallo"));
			invoices.add(new Invoice(new Date(), 0, 600, 0, "Hallo"));
			invoices.add(new Invoice(new Date(), 0, 275, 1, "Hallo"));
			invoices.add(new Invoice(new Date(), 0, 500, 0, "Hallo"));
			invoices.add(new Invoice(new Date(), 0, 25, 1, "Hallo"));
			invoices.add(new Invoice(new Date(), 0, 245, 1, "Hallo"));
			invoices.add(new Invoice(new Date(), 0, 365, 0, "Hallo"));

			invoices.forEach(invoiceRepository::save);

			ArrayList<Car> cars = new ArrayList<>();
			cars.add(new Car(1994,"NE-UK-69",20000.0,420.0, "Sedan"));
			cars.forEach(carRepository::save);
		};
	}


}
