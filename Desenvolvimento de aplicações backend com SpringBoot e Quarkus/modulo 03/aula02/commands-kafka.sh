docker exec -it florinda-eats-kafka-1 /opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9094 --create --partitions 2 --topic paymentsConfirmed

docker exec -it florinda-eats-kafka-1 /opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9094 --describe --topic paymentsConfirmed

docker exec -it florinda-eats-kafka-1 /opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9094 --list

docker exec -it florinda-eats-kafka-1 /opt/kafka/bin/kafka-console-producer.sh --bootstrap-server localhost:9094 --topic paymentsConfirmed --property "parse.key=true" --property "key.separator=;"
# >1;{"paymentId": 1, "orderId": 1}
# >2;{"paymentId":2,"orderId":2}
# >

docker exec -it florinda-eats-kafka-1 /opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9094 --topic paymentsConfirmed --from-beginning --group test
# {"paymentId": 1, "orderId": 1}
# {"paymentId":2,"orderId":2}

docker exec -it florinda-eats-kafka-1 /opt/kafka/bin/kafka-consumer-groups.sh --bootstrap-server localhost:9094 --all-groups --describe
