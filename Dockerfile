FROM openjdk:17

WORKDIR /usr/src/myapp
COPY . /usr/src/myapp

ARG mode_input
ENV mode=$mode_input

RUN ./gradlew build

CMD ['ls build/libs/*SNAPSHOT.jar | xargs -I{} java -jar {}']

