<h2 align="center">
Backend Challenge: Ducks management
</h2>



## :rocket: Technologies used

* [Docker](https://docs.docker.com/engine/install/)
* Mysql
* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) 
* [Spring Boot](https://spring.io/projects/spring-boot)
* [JUnit](https://junit.org/junit5/)
* [JasperReport](https://community.jaspersoft.com/documentation/) **PDF report**
* [Apache-Poi](https://poi.apache.org/) **Sheet report**



## How to run

- Clone repository

- Build **MySQL** image on Docker

```
docker-compose build -d
```

- Run application with **Maven**

```
./mvnw spring-boot:run
```

Application will be running  `http://localhost:8080/` ✅   
 
 
 
 
## Database
The database is modeled as follows

**customer** 

```
	id: Integer
	name: String
	created_at: DATETIME
```


**discounts** 

```
	id: Integer
	customer_id: Integer
	created_at: DATETIME
```

**duck** 
```
	id: Integer
	status: String
	parent_id: Integer
	price: Float
	created_at: DATETIME
```

**orders** 
```
	id: Integer
	customer_id: Integer
	duck_id: Integer
	price: Float
	created_at: DATETIME
```



## Endpoints 

The REST API app is described below.


---
### Create duck

`POST /duck/`

A duck can be a mother or a child
if it's a child, you nedd pass `id` of `parent` in the request body,
for example

```json
{
    "price": 50,
    "parent_id": 1
}
```
if it is not a parent, it's no necessary pass `parent_id`

```json
{
	"price": 50
}
```

example:
```
curl -X POST -H "Content-Type: application/json" -d '{ "price": 50 }' http://localhost:8080/duck
```



---
### Create customer

`POST /customer/`

A customer can be eligible eligible to receive discounts,
in this case, you must add the property `has_discount: true` 
in the request body, 

For example: 

```json
{
	"name": "Maria",
	"has_discount": true
}
```
If you are not eligible, you do not need to pass the property

```json
{
	"name": "Maria",
	"has_discount": true
}
```

example 
```
curl -X POST -H "Content-Type: application/json" -d '{ "name": "Maria" }' http://localhost:8080/customer
```



###  Orders

#### Create

`POST /orders/`
```json
{
	"customer_id": 1,
	"duck_id": 1
}
```

example
```
curl -X POST -H "Content-Type: application/json" -d '{ "customer_id": 1,  "duck_id": 1 }' http://localhost:8080/orders
```


#### List

**Request**

`GET /orders/`

**Response**

```json
[
	{
		"id": 1,
		"customerName": "Clara",
		"price": 50.0,
		"status": "SOLD",
		"hasDiscount": false,
		"customerId": 1,
		"duckId": 1,
		"duckParentId": null
	}
]
```

example
```
curl  http://localhost:8080/orders
```






##  Reports

Endpoints to request **REPORTS FILES**

###  Sheet

- EXCEL FILE

`GET /reports` or  `GET /reports?type=sheet`

Use browser in this case

EXAMPLE OF FILE:

![Captura de Tela 2024-06-28 às 18 18 57](https://github.com/mmjck/desafio_backend_preco_justo/assets/55866244/5a9821ab-bf05-417d-a434-eab800a49870)


`GET /reports?type=pdf`


###  PDF


EXAMPLE OF FILE:

![Captura de Tela 2024-06-28 às 18 18 19](https://github.com/mmjck/desafio_backend_preco_justo/assets/55866244/7cc0e85a-8a5a-4483-b2ae-235a0337f1a0)

