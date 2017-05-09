package com.gamma.configuration;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.writer.GaugeWriter;

import java.util.concurrent.TimeUnit;

/**
 * Created by rick on 5/9/17.
 */
public class InfluxMetricWriter implements GaugeWriter {
	private static final String DEFAULT_DATABASE_NAME = "metrics";
	private static final int DEFAULT_BATCH_ACTIONS = 500;
	private static final int DEFAULT_FLUSH_DURATION = 30;

	private final InfluxDB influxDB;
	private final String databaseName;

	private InfluxMetricWriter(Builder builder) {
		this.influxDB = builder.influxDB;
		this.databaseName = builder.databaseName;
		this.influxDB.createDatabase( this.databaseName);
		this.influxDB.enableBatch( builder.batchActions, builder.flushDuration,
				builder.flushDurationTimeUnit);
		this.influxDB.setLogLevel( builder.logLevel);
	}

	@Override
	public void set(Metric<?> value) {
		Point point = Point.measurement( value.getName())
				.time( value.getTimestamp().getTime(), TimeUnit.MILLISECONDS)
				.addField( "value", value.getValue())
				.build();
		this.influxDB.write( this.databaseName, null, point);
	}

	public static class Builder {
		private final InfluxDB influxDB;
		private String databaseName = DEFAULT_DATABASE_NAME;
		private int batchActions = DEFAULT_BATCH_ACTIONS;
		private int flushDuration = DEFAULT_FLUSH_DURATION;
		private TimeUnit flushDurationTimeUnit = TimeUnit.SECONDS;
		private InfluxDB.LogLevel logLevel = InfluxDB.LogLevel.BASIC;

		public Builder(InfluxDB influxDB) {
			this.influxDB = influxDB;
		}

		public void setDatabaseName(String databaseName) {
			this.databaseName = databaseName;
		}

		public void setBatchActions(int batchActions) {
			this.batchActions = batchActions;
		}

		public void setFlushDuration(int flushDuration) {
			this.flushDuration = flushDuration;
		}

		public void setFlushDurationTimeUnit(TimeUnit flushDurationTimeUnit) {
			this.flushDurationTimeUnit = flushDurationTimeUnit;
		}

		public void setLogLevel(InfluxDB.LogLevel logLevel) {
			this.logLevel = logLevel;
		}

		public Builder flushDuration(int flushDuration, TimeUnit flushDurationTimeUnit) {
			this.flushDuration = flushDuration;
			this.flushDurationTimeUnit = flushDurationTimeUnit;
			return this;
		}

		public InfluxMetricWriter build() {
			return new InfluxMetricWriter(this);
		}
	}
}
