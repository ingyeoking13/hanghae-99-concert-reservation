FROM openjdk:17

WORKDIR /usr/src/myapp
COPY . /usr/src/myapp

ARG mode_input
ENV mode=$mode_input

RUN ./gradlew build

CMD ['java -jar build/libs/reservation-1.0-SNAPSHOT.jar']

