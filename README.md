
# Sistema de RH 📄

### Esse sistema foi realizado com as seguintes tecnologias:



|| | |
|-|-|-|
|![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)  |![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) |![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)|
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)|![Bootstrap](https://img.shields.io/badge/-boostrap-0D1117?style=for-the-badge&logo=bootstrap&labelColor=0D1117)|![Windows](https://img.shields.io/badge/Windows-000?style=for-the-badge&logo=windows&logoColor=2CA5E0)
![Vscode](https://img.shields.io/badge/Vscode-007ACC?style=for-the-badge&logo=visual-studio-code&logoColor=white)|![Postman](https://img.shields.io/badge/Postman-FF6C37.svg?style=for-the-badge&logo=Postman&logoColor=white)|![Git](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![Hibernate](https://img.shields.io/badge/hibernate-E44C30?style=for-the-badge&logo=Color=white)|![Hibernate](https://img.shields.io/badge/Jpa-20232A?style=for-the-badge&logo=Color=white)| ![Hibernate](https://img.shields.io/badge/Lombok-E44C30?style=for-the-badge&logo=Color=white)
![Hibernate](https://img.shields.io/badge/Swagger-E44C30?style=for-the-badge&logo=Color=white)|![Hibernate](https://img.shields.io/badge/H2-E44C322?style=for-the-badge&logo=Color=white)| ![Hibernate](https://img.shields.io/badge/Maven-%236DB33F?style=for-the-badge&logo=Color=white)



# Descrição do projeto 📚

```
Esse projeto foi uma idéia pessoal minha, toda a criação dele foi realizado por mim, de forma independente. 
Eu decidi criar um sistema de RH, que possui Vagas e Candidatos.

Criei classes de configuração, exceção e objetos. 
Abaixo irei ilustrar essas classes e quais as funções elas desempenham na API. 

Utilizei como banco de dados o MySQL e também o H2 como banco de teste.

```




## O projeto foi idealizado na estrutura MVC ✔




# Explicando o Projeto:

# Estrutura

## Model 
```
- Candidato -> 
    Estrutura responsável pelo modelo do Candidato. Aqui teremos seus principais atributos e singularidades.
    Atributos: Nome, CPF, Idade, Experiência, Salário, Atuação e ID. 


- Vaga ->
    Estrutura responsável pelo modelo da Vaga. Aqui teremos seus principais atributos e singularidades. 
    Atributos: Experiência, Salário, Atuação e ID.


O atributo "ID" em ambos os models, é gerado automaticamente através do "@GeneratedValue" do Jakarta.
```

## Repository 

```
Estrutura Responsável por ser a Interface ORM do projeto. Aqui eu implementei JPA e atribui novos metódos
abstratos em cada camada repository (CandidatoRepository e VagaRepository). 

Métodos das Classes:

Candidato ->

    findByNome
    findByCpf
    findByExperiencia
    findByIdade
    findByAtuacao
    existsByCpf

Vaga ->

    findByAtuacao
    findByExperiencia
    findBySalario
```

## Service

```
Nessa estrutura eu costumo realizar a abstração máxima do Java (Interface). Criando uma Interface e posteriormente
uma classe que implementa essa interface.       
```
## Controller

```
Estrutura responsável pela criação dos endpoints. Crirei cerca de 20 endpoints para essa API.

|Métodos HTTP utilizados|

- Post
- Update
- Delete
- Get

```

## Exception

```
Nessa Estrutura eu decidi colocar as exceções da minha API. Vale Ressaltar que nessa estrutura 
eu precisei criar exception personalizadas. Extendi essas exceções da classe "Exception" do 
Java. 

```


# Banco de dados

### o Banco de dados utilizado foi o MySQL


```
Propriedades: 

spring.datasource.url=jdbc:mysql://localhost:3306/sistema_rh
spring.datasource.username= ()
spring.datasource.password= ()
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect

```

# Maven 

### O Projeto foi realizado com o Maven 

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.3.1</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.gabrielf</groupId>
	<artifactId>sistema</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>sistema</name>
	<description>Demo project for Spring Boot</description>
	<url/>
	<licenses>
		<license/>
	</licenses>
	<developers>
		<developer/>
	</developers>
	<scm>
		<connection/>
		<developerConnection/>
		<tag/>
		<url/>
	</scm>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
   		<groupId>org.springframework.boot</groupId>
   		<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>


    <dependency>
        <groupId>jakarta.platform</groupId>
        <artifactId>jakarta.jakartaee-api</artifactId>
        <version>10.0.0</version> <!-- Verifique e utilize a versão que você necessita -->
        <scope>provided</scope>
    </dependency>


		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.9.2</version>
		</dependency>

		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.9.2</version>
		</dependency>

		<dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.6.0</version>
   	</dependency>

	
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>


```



