FROM openjdk:17


ARG mode_input
ENV mode=$mode_input

ENV TZ=Asia/Seoul

COPY build/libs/reservation-0.0.1-SNAPSHOT.jar /app/reservation-0.0.1.jar

EXPOSE 9000

ENTRYPOINT java \
  -jar /app/reservation-0.0.1.jar \
  --spring.profiles.active=${PROFILE} \
