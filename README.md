KRaft Mode (Recommended for new installs)

(KRaft = Kafka Raft Metadata mode — no external ZooKeeper)

1. Prerequisites

Kafka version must support KRaft mode (production-ready since ~3.3, required in 4.0+) 
DEV Community
+1

Java installed (JDK8+ but check version compatibility)

For production: typically use 3 or more controller nodes (for quorum) and broker nodes. 
Confluent Documentation

2. Configure KRaft Mode

In the broker/server configuration (config/kraft/server.properties or similar), set process.roles=broker,controller (or separate roles) to define what role(s) this node will take. 
Confluent Documentation
+1

Generate a cluster ID and format storage directories before starting:

bin/kafka-storage.sh random-uuid     # generate UUID
bin/kafka-storage.sh format -t <CLUSTER_ID> -c config/kraft/server.properties


Example: 
Instaclustr
+1

Configure listeners/log directories/broker ID as usual.

In the documentation: “Chapter 4. Using Kafka in KRaft mode …” 
Red Hat Docs

3. Start Kafka Broker (with KRaft)

After formatting, start the server:

bin/kafka-server-start.sh config/kraft/server.properties


Example from tutorial: 
Instaclustr

All metadata (topics, partitions, etc) is now managed by Kafka itself via Raft; no external ZooKeeper cluster.
