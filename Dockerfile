# syntax=docker/dockerfile:1

FROM openjdk:11-jre-slim

WORKDIR /app

# dependency 다운로드
# dependency 실행?
# 소스코드 다운로드
# 스프링부트 실행


ARG buildDir=build/unpack

COPY ${buildDir}/BOOT-INF/lib /lib
COPY ${buildDir}/META-INF /META-INF
COPY ${buildDir}/BOOT-INF/classes .
ENTRYPOINT ["java","-cp","app:app/lib/*","com.ewhaenonymous.ttockclinic.TtockclinicApplication"]