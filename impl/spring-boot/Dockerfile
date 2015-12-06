FROM java:openjdk-8
MAINTAINER xelita <admin@xelita.com>

ADD target/events-recorder*.jar /opt/apps/events-recorder.jar
EXPOSE 8080

WORKDIR /opt/apps/
CMD ["java", "-jar", "events-recorder.jar"]
