# bpd

Estrutura do Projeto
  º BPD Parent (POM)
    º BPD Common Bean (JAR)
    º BPD Common (JAR)
    º BPD API (WAR)

Obs:. Sobre o projeto
  1. Gosto de utilizar o desacoplamento desta forma pois posso re-utilizar as entidades como dependências de outros projetos.
  2. Fiz o projeto utilizando um Database Master e outro Slave para utilizar com o Kubernetes caso seja necessário.
  3. Para compilar o projeto pela primeira vez é necessário compinar o BPD Parent, e caso queira subir o tomcat pode rodar o pom do BPD API.
  4. O projeto encontra-se hospedado no Google Cloud com a url:
