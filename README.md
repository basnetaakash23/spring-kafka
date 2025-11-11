# 🦾 Running Apache Kafka in KRaft Mode

KRaft (Kafka Raft Metadata mode) allows Kafka to run **without ZooKeeper**, providing a simpler, more modern architecture for metadata management.

---

## 🧩 1. Prerequisites

- **Kafka Version:** Must support KRaft mode  
  - Production-ready since **Kafka 3.3+**  
  - **Required from Kafka 4.0+**
- **Java:** JDK 8 or higher (check version compatibility)
- **Production Clusters:** Use at least **3 controller nodes** (for quorum) and multiple broker nodes for fault tolerance.

📚 References: [DEV Community](https://dev.to/deeshath/apache-kafka-kraft-mode-setup-5nj) · [Confluent Documentation](https://docs.confluent.io/platform/current/kafka-metadata/config-kraft.html)

---

## ⚙️ 2. Configure KRaft Mode

### Edit Configuration
Open `config/kraft/server.properties` (or equivalent) and set:

```properties
process.roles=broker,controller
node.id=1
controller.quorum.voters=1@localhost:9093
listeners=PLAINTEXT://:9092,CONTROLLER://:9093
advertised.listeners=PLAINTEXT://localhost:9092
log.dirs=/tmp/kraft-combined-logs
```

### Generate Cluster ID and Format Storage

```bash
# Generate unique cluster ID
bin/kafka-storage.sh random-uuid

# Format the storage directories
bin/kafka-storage.sh format -t <CLUSTER_ID> -c config/kraft/server.properties
```

Replace `<CLUSTER_ID>` with the value generated above.

📘 Example Reference: [Instaclustr](https://www.instaclustr.com/education/apache-kafka/apache-kafka-tutorial-get-started-with-kafka-in-5-simple-steps/)

---

## 🚀 3. Start Kafka Broker (with KRaft)

Start the broker using:

```bash
bin/kafka-server-start.sh config/kraft/server.properties
```

📝 All metadata (topics, partitions, etc.) is now managed internally by **Kafka Raft**, without ZooKeeper.

📖 See also: [Red Hat Docs – Using Kafka in KRaft Mode](https://docs.redhat.com/en/documentation/red_hat_streams_for_apache_kafka/2.9/html/using_streams_for_apache_kafka_on_rhel_in_kraft_mode/assembly-kraft-mode-str)

---

## ✅ Notes

- KRaft simplifies architecture by eliminating ZooKeeper.
- Controller and broker roles can be separated for scalability in production.
- ZooKeeper mode is **deprecated** and removed from Kafka 4.0.

---

**Sources:**  
- [Confluent Documentation](https://docs.confluent.io/platform/current/kafka-metadata/config-kraft.html)  
- [Instaclustr Tutorial](https://www.instaclustr.com/education/apache-kafka/apache-kafka-tutorial-get-started-with-kafka-in-5-simple-steps/)  
- [Red Hat Kafka Docs](https://docs.redhat.com/en/documentation/red_hat_streams_for_apache_kafka/2.9/html/using_streams_for_apache_kafka_on_rhel_in_kraft_mode/assembly-kraft-mode-str)  
- [DEV Community Guide](https://dev.to/deeshath/apache-kafka-kraft-mode-setup-5nj)
