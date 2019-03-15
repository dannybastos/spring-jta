# spring-jta

## Cenario

This project uses a typical case, as a bank transfer and the log ( audit ) from this operation. We allow transfers from any value, but if the balance became negative, a NotEnouthFoundsException will be launched;

## Requirements
- docker
- docker-compose

## How to run

```
docker-compose up -d
```

## Premisses

The project creates 2 accounts by default;
id:1010, balance:100
id:1020, balance:200f

## How to test
```
curl -vd "fromAccountId=1010&toAccountId=1020&amount=1110" -H "Content-Type: application/x-www-form-urlencoded" \http://localhost:8080/transf
```

You can check the database in :
http://localhost:8080/h2-console

- JDBC URL : jdbc:h2:mem:audit
- JDBC URL : jdbc:h2:mem:bank

> This project uses JTA with Bitronix
