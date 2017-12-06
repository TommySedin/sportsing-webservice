FROM payara/server-full:latest
COPY target/webservice.war /opt/payara41/glassfish/domains/domain1/autodeploy

