# supermarket-rest-kata

Kata to practice outside-in TDD with [double loop](http://coding-is-like-cooking.info/2013/04/outside-in-development-with-double-loop-tdd/).

The idea is let TDD to guide us through **architecture and domain design** in a more realistic and day to day context, a
microservices environment.

This Kata is based on https://github.com/albertllousas/supermarket-tdd-rest-kata and originally inspired on [supermarket code kata](http://codekata.com/kata/kata01-supermarket-pricing/)

## Kata description

The application has already a list of products with their prices accessible through the API endpoint `GET http://localhost:8080/products`. Data is stored in H2 database.

```
SKU     Price   Description 
---------------------------
  A      2.00     Milk 
  B      4.00     Orange Juice
  C      3.50     Chocolate
  D      1.00     Banana
```

We are going to implement a supermarket checkout, that basically calculates the total price given a list of **products** a
customer have on the **cart**.

And these are the expected input/outputs for the app.

For the following API call:

```bash
curl --request POST http://localhost:8080/checkout \
  --header 'Content-Type: application/json' \
  --data '{ skus: ["A","B","A","B","A","A","A"] }' \
  --include
```

The output should be `201 CREATED` with body:

```json
{
  "total": 18.00
}
```

**Notes**:
* Rule: Write prod code in terms of architecture and domain
* SKUs are [Stock keeping units](https://en.wikipedia.org/wiki/Stock_keeping_unit)
* Should respond with 204 for an empty cart request, `{ "skus": [] }`

Have fun!

## Where to start

We have provided the exercise with the [acceptance test](./kotlin/src/test/kotlin/com/katas/supermarket/acceptance/CalculateCartTotalAcceptanceTest.kt) already
set up, we don't want to waste time designing an API.

So jump [here](./kotlin/src/main/kotlin/com/katas/supermarket/api/ChangeMeController.kt) and start coding.

## Bonus

- How to handle non-existent SKU?
- Introduce a discount system, choose one:
  - 2x1 in some products
  - Discount codes, % per total

## Run the tests
The most convenient way of running the tests is directly from your IDE. They can also be run from the console using Gradle.
```
gradlew.bat test  // on Windows
./gradlew test    // on Linux
```

## Starting the application
```
gradlew.bat bootRun // on Windows
./gradlew bootRun   // on Linux
```
Try the existing list products endpoint with the following command:
```bash
curl --request GET http://localhost:8080/products
```

## Useful links

- [Outside-in Double Loop TDD](http://coding-is-like-cooking.info/2013/04/outside-in-development-with-double-loop-tdd/)
- [London School](https://www.slideshare.net/pkofler/outsidein-test-driven-development-the-london-school-of-tdd)
