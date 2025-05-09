https://developer.confluent.io/confluent-tutorials/kafka-on-docker/
https://medium.com/javarevisited/getting-started-with-kafka-in-docker-and-java-2051ccef1ca7
Since the introduction of KRaft Kafka no longer requires Apache ZooKeeper for managing cluster metadata
Start service:
    docker compose up -d
To produce a message, let's open a command terminal on the Kafka container:
    1. docker exec -it -w /opt/kafka/bin broker sh
       Then create a topic:
    2. ./kafka-topics.sh --create --topic my-topic --bootstrap-server broker:29092
       Next, start a console producer with this command:
    3. ./kafka-console-producer.sh  --topic my-topic --bootstrap-server broker:29092
    4. At the prompt copy each line one at time and paste into the terminal hitting enter key after each one:
       All streams
       lead to Kafka
    5. Then enter a CTRL-C to close the producer.

consume the messages with this command:
    ./kafka-console-consumer.sh --topic my-topic --from-beginning --bootstrap-server broker:29092