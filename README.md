
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




