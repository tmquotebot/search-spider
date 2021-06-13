FROM openjdk:11-jre-slim
LABEL maintainer=senyasdr
LABEL maintaier=shakirov-aa
WORKDIR /app
COPY build/docker/libs libs/
COPY build/docker/resources resources/
COPY build/docker/classes classes/
ENTRYPOINT ["java", "-Xmx2048m", "-cp", "/app/resources:/app/classes:/app/libs/*", "com.github.tmquotebot.searchspider.SearchSpiderApplication"]
EXPOSE 9090 8080
