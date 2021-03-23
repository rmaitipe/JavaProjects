Basic HATEOAS http calls webservice built using JAX-RS & JAXB, consumes XML uses web.xml
mapped using @ApplicationPath, @Path
             @XmlRootElement, @XmlAccessorType

GET
/RestAPIExample/network-management/configurations
/RestAPIExample/network-management/configurations/1

POST
curl --location --request POST 'http://127.0.0.1:8080/RestAPIExample/network-management/configurations/' \
--header 'Content-Type: application/xml' \
--data-raw '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<configuration>
    <content>Created by POST</content>
    <status>ACTIVE</status>
</configuration>'
if consuming Json use below instead
{
    "content":"Created by POST"
    "status":"ACTIVE"
}
PUT
curl --location --request PUT 'http://127.0.0.1:8080/RestAPIExample/network-management/configurations/3' \
--header 'Content-Type: application/xml' \
--data-raw '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<configuration>
    <content>Updated by PUT</content>
    <status>INACTIVE</status>
</configuration>'
if consuming Json use below instead
{
    "content":"Upodated by PUT"
    "status":"ACTIVE"
}

DELETE
/RestAPIExample/network-management/configurations/3