# SPRING-RESTFULL-API

## API Spec
```
User API
Register    : POST /api/users
Login       : POST /api/auth/login
Get User    : GET /api/users/current
TopUp       : PUT /api/users/current

Category API
Create Category                 : POST /api/category
Get Single Category             : GET /api/category/{categoryId}
Get All Category By Username    : GET /api/category

Transaction History API
Create Transaction History  : POST /api/category/{categoryId}/transaction
Get Transaction by Username : GET /api/transaction
Get Transactiono History Based On Date Range : POST /api/transactionHistory
```

## Please open database.sql for an explanation of the database
database.sql

## User API Spec
### Register User
Endpoint : POST /api/users

Request Body :
```json
{
  "username" : "Jamil",
  "password" : "password",
  "name" : "jamil Jamul" 
}
```
### Login User
Endpoint : POST /api/auth/login

Request Body :
```json
{
  "username" : "Jamil",
  "password" : "password" 
}
```
Response Body (Success) :

```json
{
  "data" : {
    "token" : "d85ca312-b0c1-4733-a747-5c57eabde10b",
    "expiredAt" : 2342342423423
  }
}
```
### Get User

Endpoint : GET /api/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory) 

Response Body (Success) :

```json
{
  "data" : {
    "username" : "Jamil",
    "name" : "Jamil Jamul",
    "balance": 0
  }
}
```
### TopUp Balace
Endpoint : PUT /api/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory) 

Request Body :

```json
{
  "balance" : "125000"
}
```

## Category API Spec
### Create Category

Endpoint : POST /api/category

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
    "categoryName": "Transportation"
}
```

Response Body (Success) : 

```json
{
  "data": {
    "id": "c143ea17-0971-4957-b48c-f2aeb2128cd1",
    "categoryName": "Transportation"
  }
}
```
### Get Single Category

Endpoint : GET /api/category/{categoryId}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) : 

```json
{
  "data": {
    "id": "c143ea17-0971-4957-b48c-f2aeb2128cd1",
    "categoryName": "Transportation"
  }
}
```
### Get All Category By Username

Endpoint : GET /api/category

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) : 

```json
{
  "data": [
    {
        "id": "cf09bca4-aab0-47d0-a4ed-95f573dd5941",
        "categoryName": "TopUp"
    },
    {
        "id": "c143ea17-0971-4957-b48c-f2aeb2128cd1",
        "categoryName": "Transportation"
    }
  ]
}
```

## Transaction History API Spec
### Create Transaction History

Endpoint : POST /api/category/{categoryId}/transaction

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
    "type": "debit",
    "amount": 10000,
    "description": "Ojek Online ke kentor"
}
```

Response Body (Success) : 

```json
{
  "data": {
        "id": "9b9d9b6b-56ef-4f10-8a63-9c5f03fbffbb",
        "type": "debit",
        "amount": 10000,
        "description": "Ojek Online ke kentor"
  }
}
```
### Get Transaction by Username

Endpoint : GET /api/transaction

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) : 

```json
{
  "data": {
        {
            "id": "b12963ba-1f20-4478-a0e2-13b187b1e05f",
            "type": "credit",
            "amount": 125000,
            "description": "TopUp Balance"
        },
        {
            "id": "9b9d9b6b-56ef-4f10-8a63-9c5f03fbffbb",
            "type": "debit",
            "amount": 10000,
            "description": "Ojek Online ke kentor"
        }
  }
}
```
### Get Transactiono History Based On Date Range

Endpoint : POST /api/transactionHistory

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
    "startDate": "2023-10-13",
    "endDate": "2023-10-17"
}
```

Response Body (Success) : 

```json
[
    [
        "Jamil",
        "TopUp",
        "credit",
        125000,
        "TopUp Balance",
        "2023-10-15T07:23:47.419+00:00"
    ],
    [
        "Jamil",
        "Kuliah",
        "debit",
        10000,
        "Kuliah s2 semester 1",
        "2023-10-15T07:29:11.977+00:00"
    ]
]
```