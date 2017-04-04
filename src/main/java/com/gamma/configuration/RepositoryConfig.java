package com.gamma.configuration;

import com.gamma.dal.entities.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * Created by guushamm on 21-3-17.
 */
@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Car.class);
		config.exposeIdsFor(GpsPoint.class);
		config.exposeIdsFor(Invoice.class);
		config.exposeIdsFor(Owner.class);
		config.exposeIdsFor(Rate.class);
		config.exposeIdsFor(Region.class);
		config.exposeIdsFor(Tracker.class);
		config.exposeIdsFor(Trip.class);
	}
}
