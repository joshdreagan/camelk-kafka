# camelk-kafka

## Prerequisites

Install the Camel K Operator: https://camel.apache.org/camel-k/latest/installation/installation.html
Install the Camel K CLI: https://camel.apache.org/camel-k/latest/cli/cli.html
Install the Strimzi Operator: https://strimzi.io/docs/operators/latest/quickstart.html

## Installation

Create a new project.

```
oc new-project demo
```

Create the Kafka cluster and topic.

```
oc apply -f kafka-cluster.yaml
oc apply -f kafka-topic.yaml
```

Edit the [message-processor-configmap.properties](./message-processor-configmap.properties) and [message-processor-secret.properties](message-processor-secret.properties) with your settings and credentials. Then create the ConfigMap & Secret.

```
oc create configmap message-processor-configmap --from-file=application.properties=message-processor-configmap.properties
oc create secret generic message-processor-secret --from-file=application.properties=message-processor-secret.properties
```

Run the Camel K application. _Note: Append `--dev` to the end of the command if you want to run in dev mode._

```
kamel run --configmap=message-processor-configmap --secret=message-processor-secret MessageProcessor.java
```
