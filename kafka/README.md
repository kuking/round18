
### Spotify Kafka image
https://hub.docker.com/r/spotify/kafka/

###### Create
```bash
docker run -p 2181:2181 -p 9092:9092 \
--env ADVERTISED_HOST=`docker-machine ip \`docker-machine active\`` \
--env ADVERTISED_PORT=9092 spotify/kafka \
--name kafka-dev
# maybe: create -d dettach, --rm delete-after-stop
```

###### Environment

```bash
eval $(docker-machine env default)
export KAFKA=`docker-machine ip \`docker-machine active\``:9092
export ZOOKEEPER=`docker-machine ip \`docker-machine active\``:2181
```

Testing it works:
````bash
$ kafka-console-producer.sh --broker-list $KAFKA --topic test
$ kafka-console-consumer.sh --zookeeper $ZOOKEEPER --topic test
````

###### OSX

```bash
docker-machine create --driver virtualbox default
eval $(docker-machine env default)
```
