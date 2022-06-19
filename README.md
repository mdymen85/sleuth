# Sleuth Test

## Introduction
The main goal is to test distributed log using Spring Sleuth.

## Technologies
- Docker for RabbitMQ 
- Spring Sleuth
- Java 17
- Spring Boot

### Architecture
![diagram](https://github.com/mdymen85/sleuth/blob/main/sleuth-diagram.png)

As i show in the picture above, a caller calls an endpoint from a client application that, using **feign client** technology makes a call to an other endpoint from a service that post a message into **rabbit** queue, and this queue is consumed by an other application. All this throw the same **traceId** in the logs of the free applications.

In the images below, from the three applications, you are able to see the same **traceId** in the log. This means that **Spring Sleuth** propagate in the headers of the messages, the information of the **traceId** in order to be used by the log of the other applications, all over the transaction.

### Client 
![sleuth client log](https://github.com/mdymen85/sleuth/blob/main/sleuth-client-log.png)

### Server
![sleuth server log](https://github.com/mdymen85/sleuth/blob/main/sleuth-server-log.png)

### Consumer 
![sleuth consumer log](https://github.com/mdymen85/sleuth/blob/main/sleuth-consumer-log.png)


### Curl

```
curl --location --request POST 'localhost:8080/api/v1/client' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id":1,
    "name":"Name Surname"
}'
```

### Docker for Rabbit MQ

```
docker run -d --hostname my-rabbit -p 15672:15672 -p 5672:5672 --name some-rabbit rabbitmq:3-management
```

### Attention points
The **traceId** seems to be a random string, because of that im curious if its possible to define an **UUID** as **traceId** an also as in id for idempotency treatment.
