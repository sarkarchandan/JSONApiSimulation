FROM glassfish:latest

MAINTAINER self

COPY . /application
WORKDIR /application

ADD glassfishConfiguration/postgresql-9.4.1212.jar /usr/local/glassfish4/glassfish/domains/domain1/lib/postgresql-9.4.1212.jar
ADD glassfishConfiguration/postgresql-9.4.1212.jre7.jar /usr/local/glassfish4/glassfish/domains/domain1/lib/postgresql-9.4.1212.jre7.jar

RUN chmod +x /application/start.sh

EXPOSE 4848
EXPOSE 8080

ENTRYPOINT ["/application/start.sh"]