# florinda-eats

Florinda Eats is a food delivery application for Dona Florinda's restaurant.

It is a Microservices Architecture composed of the following services:

- **Orders**, which contains menu information and placed orders. Runs by default on port `8080`
- **Payments**, which allows payment confirmation. Runs by default on port `8081`
- **Invoices**, which generates invoice XMLs to standard output. Runs by default on port `8082`

# What do you need to do?

## Run the Payment service

Open the `payments` service code in IntelliJ.

Run the service through IntelliJ, using _Maven_ > _Plugins_ > _Quarkus_ > `quarkus:dev`.

Open Postman and import the `florinda-eats-payments.postman_collection` collection.

Explore the service and its endpoints. For example, confirm a payment by making a `PUT http://localhost:8081/payment/1`.

## Run the Orders service

Open the `orders` service code in IntelliJ.

Run the service through IntelliJ, using _Maven_ > _Plugins_ > _Quarkus_ > `quarkus:dev`.

Open Postman and import the `florinda-eats-orders.postman_collection` collection.

Check the orders list at `http://localhost:8080/orders`.

Explore the service and its endpoints. For example, detail an order (e.g., id 1) with the URL `http://localhost:8080/orders/1`.

## Run the Invoice service

Open the `invoices` service code in IntelliJ.

Run the service through IntelliJ, using _Maven_ > _Plugins_ > _Quarkus_ > `quarkus:dev`.

Open Postman and import the `florinda-eats-invoices.postman_collection` collection.

Get an invoice for an order (e.g., id 1) with the URL `http://localhost:8082/invoice/order/1`.

## Run Kafka

The `docker-compose.yml` file contains a Kafka configuration whose port for external connections to the container is `9094`.

To run Kafka, open a Terminal and run at the project root:

```sh
docker compose up
```

Wait a bit for Kafka to start.

Now integrate the services using Quarkus Messaging Kafka! ;)
