# tenniscourt
This app registers interest for a tennis court, there is one booking per day and three courts. Once four people have regirterd interest
in one day a court is booked and notification is sent.
This application used in memory H2 DB

Sample cURL to create a booking 

curl -X POST \
  http://localhost:8080/booking/register \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"date":"04/09/2021",
	"name":"Test",
	"phoneNumber":"123456"
}'
