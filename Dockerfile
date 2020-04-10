FROM openjdk:13-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=build/dockerSource
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","-Dspring.profiles.active=docker","com.visum.community.CommunityApplication"]
