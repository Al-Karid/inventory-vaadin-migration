FROM docker/dev-environments-default:stable-1
ENV SPRING_ACTIVE_PROFILE=dev
WORKDIR /home/vscode
RUN apt update
RUN apt install openjdk-17-jdk openjdk-17-jre -y
RUN apt install maven -y