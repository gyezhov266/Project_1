# Project Planetarium 

## update Jan 13, 2023
- refurnished Spring app 
- recreated docker image named rollingNew. :latest and :rolling temporarily not in use
- fixed issues with adding custom dashboards to Grafana: New->Import->JSON file
- configured Jenkins
- discovered issue with logging: localhost8080 generates log but cluster url does not
    - will set up other infrastructures and come back if needed

    


## update Jan 12, 2023
- added imaged for static and rolling logs
- fixed issues with pods restarting by updating security groups for RDS
- fixed issues with "promtail-config not found": need to kubectl apply -f promtail-configuration first
- discovered issues with Grafana: Loki data source not set up by default
    - temp solution: add manually, data source and label found successfully