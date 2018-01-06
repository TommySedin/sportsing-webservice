FROM payara/server-full:latest
EXPOSE 9009

RUN sed -i 's/start-domain/start-domain --debug=true/g' bin/startInForeground.sh

COPY target/webservice.war $DEPLOY_DIR
