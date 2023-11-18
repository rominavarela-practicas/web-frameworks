build:
	mvn dependency:resolve

services:
	open http://localhost:8000
	docker-compose up

init:
	docker-compose exec kafka kafka-topics --create \
	    --bootstrap-server kafka:9092 \
	    --replication-factor 1 \
	    --partitions 1 \
	    --topic NumbersStreamDataflowTopic0
	docker-compose exec kafka kafka-topics --create \
	    --bootstrap-server kafka:9092 \
	    --replication-factor 1 \
	    --partitions 1 \
	    --topic NumbersStreamDataflowTopic1