FROM openjdk:17


ARG mode_input
ENV mode=$mode_input

ENV TZ=Asia/Seoul

COPY build/libs/reservation-1.0-SNAPSHOT.jar /app/reservation-1.0-SNAPSHOT.jar

EXPOSE 9000

ENTRYPOINT java \
  -jar /app/reservation-1.0-SNAPSHOT.jar \
  --spring.profiles.active=${PROFILE} \
