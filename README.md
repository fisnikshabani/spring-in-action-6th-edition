Cassandra operates as a cluster of nodes that together act as a complete database
system. If you don’t already have a Cassandra cluster to work with, you can start a singlenode
cluster for development purposes using Docker like this:

docker network create cassandra-net

docker run --name my-cassandra --network cassandra-net -p 9042:9042  -d cassandra:latest

Using the Cassandra CQL (Cassandra Query Language) shell, you can create a keyspace for the Taco Cloud application. You can start the CQL shell using Docker like this: 

docker run -it --network cassandra-net --rm cassandra cqlsh my-cassandra

When the shell is ready, use the create keyspace command like this:

create keyspace tacocloud with replication={'class':'SimpleStrategy', 'replication_factor':1} and durable_writes=true;
