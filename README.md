Trabalho feito em dupla como entrega final para o programa **IBM Blue Academy** 😃🤝👩‍💻

Openjdk version: 11.0.11

# Api documentation:

## Balance Movement Controller

POST `​/balance-movements` - Create a deposit, withdrawal or transfer between accounts

GET `​/balance-movements​/{balanceTransferId}` - Find by id a balance movement

## Bank Account Controller

GET `​/bank-accounts​/{bankAccountId}​/balance-movements` - Find all balance movements by bank account id

## Customer Controller

GET `​/customers` - Find all customers

POST `​/customers` - Create a customer

GET `​/customers​/{customerId}` - Find customer by id

PUT `​/customers​/{customerId}` - Update a customer

DELETE `​/customers​/{customerId}` - Delete a customer

PATCH `​/customers​/{customerId}​/active` - Update status active of customer by id

### Customer - Address

GET `​/customers​/{customerId}​/address` - Find address by customer id

POST `​/customers​/{customerId}​/address` - Create an address by customer id

PUT `​/customers​/{customerId}​/address` - Update an address by customer id

### Customer - Bank Account

GET `​/customers​/{customerId}​/bank-accounts` - Find bank account by customer id

POST `​/customers​/{customerId}​/bank-accounts` - Create a bank account by customer id

PATCH `​/customers​/{customerId}​/bank-accounts​/active` - Update status active of bank account by customer id
