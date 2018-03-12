
OSX:
- docker-machine create --driver virtualbox default
- eval $(docker-machine env default)

Spotify Kafka image
- https://hub.docker.com/r/spotify/kafka/
- docker run -p 2181:2181 -p 9092:9092 \
	--env ADVERTISED_HOST=`docker-machine ip \`docker-machine active\`` \
	--env ADVERTISED_PORT=9092 spotify/kafka \
	--name kafka-dev

- maybe: -d dettach, --rm delete-after-stop

- export KAFKA=`docker-machine ip \`docker-machine active\``:9092
- export ZOOKEEPER=`docker-machine ip \`docker-machine active\``:2181

