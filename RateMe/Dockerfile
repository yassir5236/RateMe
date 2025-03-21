FROM eclipse-temurin:23-jdk AS build

WORKDIR /app

RUN apt update && apt install -y maven

COPY ./pom.xml ./

RUN mvn dependency:go-offline -B

COPY ./src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:23-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar ./app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
