#!/bin/sh

echo "Starting Glassfish Sever..."
asadmin start-domain

echo "Starting configuration..."

echo "Creating JDBC Connection Pool..."
asadmin create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype javax.sql.DataSource --property portNumber=5432:password=password:user=localhost:serverName=postgres:databaseName=jsonapisimulation JSONApiSimulationPool

echo "Creating JDBC Resource..."
asadmin create-jdbc-resource --connectionpoolid JSONApiSimulationPool jdbc/jsonapisimulation



