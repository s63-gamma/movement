package com.gamma.configuration;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpointMetricReader;
import org.springframework.boot.actuate.metrics.writer.GaugeWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by guushamm on 21-3-17.
 */
@Configuration
public class WebConfiguration {
	@Bean
	@ExportMetricWriter
	GaugeWriter influxMetricsWriter() {
		InfluxDB influxDB = InfluxDBFactory.connect("http://influxdb:8086",
				"root",
				"root");
		String dbName = "gmovement";    // the name of the datastore you choose
		influxDB.createDatabase(dbName);
		InfluxMetricWriter.Builder builder = new InfluxMetricWriter.Builder(influxDB);
		builder.setDatabaseName(dbName);
		builder.setBatchActions(500);    // number of points for batch before data is sent to Influx
		return builder.build();
	}

	@Bean
	public MetricsEndpointMetricReader metricsEndpointMetricReader(MetricsEndpoint metricsEndpoint) {
		return new MetricsEndpointMetricReader(metricsEndpoint);
	}
}
