# Project Planetarium 

## update Jan 16, 2023
- added minor details to the source code
    - added DataIntegrityViolationException exception handler to prevent registering duplicate users
    - made id a mandatory entry when creating moon/planet to ensure better workflow
    - added "api" before moon and planet related URIs
- tested source code with thunder client
    - everything has passed with 200 and 201 except REGISTER: expect a 400/404 because we cannot register two users with the same username
    


## update Jan 13, 2023
- refurnished Spring app 
- recreated docker image named rollingNew. :latest and :rolling temporarily not in use
- fixed issues with adding custom dashboards to Grafana: New->Import->JSON file
- fixed issues with github webhook setup with status code 302: the webhook url should end with "/"
- configured Jenkins
- discovered issue with logging: localhost8080 generates log but cluster url does not
    - will set up other infrastructures and come back if needed

## update Jan 12, 2023
- added imaged for static and rolling logs
- fixed issues with pods restarting by updating security groups for RDS
- fixed issues with "promtail-config not found": need to kubectl apply -f promtail-configuration first
- discovered issues with Grafana: Loki data source not set up by default
    - temp solution: add manually, data source and label found successfully