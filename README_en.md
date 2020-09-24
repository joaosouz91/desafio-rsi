# Desafio RSI

REST API made with SpringBoot to simulate a simple e-commerce.

## Pre reqs

Have [Docker](https://www.docker.com/) running in your machine.

Have the `8080 port` released.



## Installation

After executing `git clone`, also do the same with the following command on the project root folder.

```bash
docker-compose up --build
```

The command execution will start the application deployment since a docker image.

At the end, the API will be available in:

```bash
localhost:8080
```

If perhaps the exception `com.mysql.cj.jdbc.exceptions.CommunicationsException: Communications link failure` is shown in console, just ignore. It means that the service of the Java app has suffered an attempt to be initialized before the MySQL finish its own initialization. Don't worry, the Java app will be initialized correctly.

## Getting an accessToken

It is important to know that none of the resources will be automatically available. To access them is necessary give to the request a valid `accessToken`

- Open the [Postman](https://www.postman.com/), initialize a new request and paste the below URL in the address bar.
```bash
http://localhost:8080/oauth/token
```

- Choose the http `POST` method

- On the tab `Authorization` specify the `Type` as `BasicAuth`

- In the field `Username` set the value `rsi-app`

- In the field `Password` set the value `r$s&i0#`

- Go to the tab `Body` (request) and choose the option `x-www-form-urlencoded`. After that create the variables as detailed as following.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`client` : `rsi-app`

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`username` : `admin@rsi.com.br`

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`password` : `admin`

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`grant_type` : `password`

- Click `Send` e watch the `body` return.

- The `accessToken` will be the value of the field `"access_token"`. Remember that you have two (2) minutes exactly to use this key on next requests. The most correct, thinking in a third party application, would be let the `accessToken` valid only for 30 seconds, and after that require a new one. For manual tests purposes it its taking 2 minutes to expire, not being necessary fetching a new key in a short time.


## Requests in the API Resources

Once the aplication is running it is possible obtain the API Swagger documentation through the below URL and shape your own requests.

```bash
http://localhost:8080/swagger-ui.html
```

If you don't want to mount request by request, use the requests that are already built for this API pasting the following address at the Postman import (File>Import>Link>URL).

```bash
https://www.getpostman.com/collections/025846c2604a26adb701
```

To get, for example, a Products search result, go to the `Authentication` tab (request), choose the `Type Baerer Token` and paste the `accessToken` obtained through the step above in the shown field. The other requests must follow the same flow till the accessToken expires, being necessary the request of a new key.

## Refresh Token

Thinking in third party application consuming this API, it is possible obtain `refreshToken` after any request of a `accessToken`; its value will be attached to a Cookie returned by this request, being able to be passed as a parameter to obtain a new accessToken, with no needs to set authentication parameter at user level (like the ones in the request-body) in every request.

## Heroku

This API is also in the Cloud and can be accessed through the address:
```bash
https://desafio-rsi-simple-ecommmerce.herokuapp.com/
```

Every request shown here can be applied at this endpoint, considering only that the `http` must be changed to `https`

## Some business rules

- An update in the product list can be applied only for a shopping cart, while this is being created (shopping cart is not a implemented resource on this API yet)

- Once a order is created, its products can't be updated anymore. 

- If there is a mistake in a creation of an order, with regard to have more or less items, the order can be canceled.

- An order must have its status update flow with no by-pass. It must pass through all phases sequentially.

- The by-pass exception is the "Canceled" status

- An order can't be deleted of the system, only canceled.

- There is no discount per item. Discounts can be given only to the order total.

## Considerations about the API

- The user `admin@rsi.com.br` has privileges to access every resource (read and write permissions), while the user `maria@rsi.com.br` has access only to read the resources.

- To test the access to the resources with the user Maria, change de variable `username` to `maria@rsi.com.br` on the tab `Body` of a new request of a `accessToken`. Her password was kept as `admin`, therefore it is not necessary change it on the request.

- On the Order, Products and Customers `CREATION` requests DO NOT specify the `id` field of these objects, only the `id` fields of the inner objects of them (Product List, Address, etc). Swagger specifies in its examples to pass them like `0`, but this is wrong. These properties will be removed later. Anyway, there is an exception loaded to catch request with these parameters passed incorrectly.

## Final considerations

The objective of this POC is to proof some technical capacities demanded by RSI. The focus was not to present strong business rules, but demonstrate a good usage of the current most used market frameworks, conceiving the project at its best architecture possible.



Thanks in advance.