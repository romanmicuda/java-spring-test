# INSTRUCTIONS FOR THIS TEST

This test aims to test knowledge of java, spring and sql 

Domain model is created - marketing campaign  consists of several questions.
Each question consist of:
 - **type** : can be RATING (number of stars for example 1 to 5), or CHOICE (user can select one to many options).
 - **text**: question text
 - **options**: possible options when type CHOICE

Users can than submit Feedback to the campaign:

Feedback entity:
 - belongs to campaign
 - contains list of answers to each question from campaign

Answer:
 - either contains ratingValue (int) or list of selected options for question

# GOAL

Implement REST API to return summary of campaign, I have defined return object of the API. Please use Spring Data 

It should return:
- number of total feedbacks in the campaign
- list of questions summary
  - for each summary it should return its name and type
  - if type == RATING, it should calculate average of rating values
  - if type == CHOICE, it should return number of occurrences of each option


There is import.sql with some dummy data as an example. For this data, it should return:

- total feedbacks = 3
- question 1, rating average = 4
- question 2
  - option 1 = 1 occurrence 
  - option 2 = 3 occurrences
  - option 3 = 0 occurrences
  - option 4 = 2 occurrences


## TRY TO MAKE CODE AS CLEAN AND PERFORMANT AS POSSIBLE!


### Usage

To run the Spring Boot application, use the following command:

```bash
mvn spring-boot:run
```

You can then test the endpoint with the following `curl` command:

```bash
curl -X GET http://localhost:8080/campaign/summary/a9cf9e86-e7a5-4934-bbed-8aa03a086aed
```
