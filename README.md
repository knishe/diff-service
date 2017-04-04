# diff-service
Its a web service provides comparison between two binary data

## Assumption

1. Only provides comparison information for strings

## Pre-requisite

1. Apache Maven 3.3.9 installed

## Run Unit test

``` sh
$ mvn test
```

## Run Integration test
```
$ mvn failsafe:integration-test
```

## Functionality

Provides comparison results of two Base64 encoded JSON data

It exposes the functionality via three web service end point as follows:
```'
1.<host>/v1/diff/<ID>/left
2.<host>/v1/diff/<ID>/right
3.<host>/v1/diff/<ID>
```

Following are some of the test results:

Test 1 when same same word is given the API:

```
Test 1

    Inputs:

    Left:   { Nishanth }
    Right:  { Nishanth }

    Result:

    {
        "areEqual":true,
        "areSizeEqual":true,
        "diffList":null
    }
```

Test 2 when different word + size is passed to the API:

```
Test 2

    Inputs:

    Left:   { Moneky }
    Right:  { Dog }

    Result:

    {
        "areEqual":false,
        "areSizeEqual":false,
        "diffList":null
    }
```

Test 3 when different word with same size is passed to the API:

```
Test 3

    Inputs:

    Left:   { Cat }
    Right:  { Dog }

    Result:

    {
        "areEqual":false,
        "areSizeEqual":true,
        "diffList":[{"length":1,"offset":0}, {"length":2,"offset":1}, {"length":3,"offset":2}]
    }

```

## Exceptions

Following are some of the exception introduced for error handling

| Exception | Description |
| ------ | ------ |
|RightExistException| When trying to add left value for the same id |
|LeftExistException| When trying to add right value  for the same id|
|InCompleteComparisonDataException | When trying to get the comparison without left or right value|
|ComparisonNotFoundException| When id of the comparison data is not found in the storage|



