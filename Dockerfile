FROM openjdk:11-jre-slim

ARG buildDir=build/unpack

COPY ${buildDir}/BOOT-INF/lib /app/lib
COPY ${buildDir}/META-INF /app/META-INF
COPY ${buildDir}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.ewhaenonymous.ttockclinic.TtockclinicApplication"]