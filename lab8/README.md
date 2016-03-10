# Lab 8 - Maven Setup, WAR Deployment

In lab8, I took lab7 from Kai Toedter and refactored the project to
* build with maven
* include the web resources (html, css, js) in the produced WAR file

To do so required to put the REST api on a different base path (/api).

## custom finders
Lab 8 further more contains an example how to write a custom finder using Query DSL and how to seamlessly integrate it into the REST API, including
* exposure in the REST API
* listing under the /user/search/ resource
* paging and sorting
* entity link creation

10.03.2016 / Stefan Heinzer