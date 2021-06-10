# JAVA DEMO APP
## REQUIREMENTS
* JDK 11 or newer installed on your machine
* docker and docker compose installed on your machine
## RUNNING TESTS
```shell
./gradlew clean test
```
## RUNNING LOCALLY
```shell
./gradlew clean build && docker-compose up --build
```
## CALLING LOCAL ENDPOINTS
Use HTTP client of your choice or enter following URLs in browser:
* http://localhost:8080/company/departments/cost
```json
{
    "Finance": 51000,
    "IT": 12000,
    "Delivery": 0
}
```
* http://localhost:8080/company/departments/it/cost
```json
{
    "departmentName": "IT",
    "departmentCost": 12000
}
```
* http://localhost:8080/company/departments/finance/cost
```json
{
    "departmentName": "Finance",
    "departmentCost": 51000
}
```
* http://localhost:8080/company/departments/delivery/cost
```json
{
    "departmentName": "Delivery",
    "departmentCost": 0
}
```