# PaysafeMonitoring
Paysafe Monitoring App

The application has 3 endpoints.

Start monitoring of a host.
POST /rest/start-monitoring
payload:
{
	"hostname":"api.test.paysafe.com",
	"interval":5
}

Stop monitoring of a host.
POST /rest/stop-monitoring
payload:
{
	"hostname":"api.test.paysafe.com"
}

Overview of a host.
POST /rest/overview
payload:
{
	"hostname":"api.test.paysafe.com"
}


Example for local environment:
localhost:8085/rest/start-monitoring


To build project:
gradle build

To run project:
gradle appStart

Some test cases:
https://documenter.getpostman.com/collection/view/591252-0a0d4590-474e-35b9-a3c4-d3d087f9e7ed

-TODO: use swagger documentation.
-TODO: add test cases.
