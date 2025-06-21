## 👨🏻‍💻 Overview

Using <strong>SpringAI</strong> integrated with the <strong>Ollama</strong> model and <strong>Kafka</strong>.

The project is a simulation of a real-time AI chat, providing users with an AI solution that runs locally on their own machines, eliminating the need to rely on external AI services.
The AI ​​model used in the project is <strong>Ollama (tinyllama)</strong>, which runs inside a Docker container, the backend built using Java and the Spring Framework
It facilitates seamless communication, while the frontend ensures an intuitive and easy-to-use experience for end users.

## ⚡️ Execution

<p>Tools:</p>

<li> Java 17 </li>
<li> Spring IA </li>
<li> Ollama (tinyllama) </li>
<li> Docker </li>
<li> Vue.js </li>
<li> Naive-UI </li>

<p></p>

<strong>Back-end</strong>

Import the project into an IDE of your choice, the IDE used for development was IntelliJ IDE, then simply build the application to download the dependencies that are managed by Maven, 
and run the application. For communication with the language model to work, you need to install Docker and initialize the container images encoded in the <code>docker-compose.yml</code> file, 
with the <code>docker-compose up -d</code> command and then the <code>docker exec -it ollama ollama run tinyllama</code> command to run the language model in the Docker container.

<strong>Front-end</strong>

Import the project into an IDE of your choice, the IDE used for development was VSCode, make sure you have the latest version of node.js installed, and then run the commands <code>npm install</code> 
to download the necessary dependencies and the command <code>npm run dev</code> to run the web application.

## 🎬 Demo

