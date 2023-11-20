
#### Test it out

1. Initialize the docker-compose services
2. Start producer and consumer applications
3. Send a message to the producer

```
curl --location 'http://localhost:8081/credit-card' \
--header 'Content-Type: application/json' \
--data '{
    "fullName": "Romina Full",
    "cardNumber": "0000-0000-0000-0000",
    "expDate": "10/10",
    "secCode": "344"
}'
```