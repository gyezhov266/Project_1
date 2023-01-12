# Project Planetarium 

## update Jan 12, 2023
- added imaged for static and rolling logs
- fixed issues with pods restarting by updating security groups for RDS
- fixed issues with "promtail-config not found": need to kubectl apply -f promtail-configuration first
- discovered issues with Grafana: Loki data source not set up by default
    - temp solution: add manually, data source and label found successfully