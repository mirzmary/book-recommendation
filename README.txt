To start the application you need Java 8+

Application is a Spring Boot REST API written with Jersey.

You'll need to run the app (maybe specify starting class if needed).

Database is embedded H2 and books are inserted on application start, so you'll get immediate response for API calls.

First of all you need to register a user with their book preferences (see curl code below):
curl -X POST \
  http://localhost:8989/api/user/register \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"login":"test",
	"firstName":"test",
	"lastName": "test",
	"birthDate":"1990-01-01",
	"preferredGenres": [
		"FICTION",
		"DETECTIVE",
		"DRAMA",
		"NOVEL"
		],
	"preferredLanguages": [
		"ENGLISH"
		],
	"maxPreferredPage": 700
}'

After that you can get book recommendations (see curl code below):
curl -X GET \
  'http://localhost:8989/api/book/recommend?userId=1' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json'

After getting book recommendations user can leave a feedback for any of the books (see curl code below):
curl -X PUT \
  http://localhost:8989/api/book/feedback \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"userId": 1,
	"bookId": 1,
	"feedback": "LIKE"
}'