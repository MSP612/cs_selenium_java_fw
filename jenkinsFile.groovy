pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'   // use the name you gave in Jenkins Maven config
        jdk 'JAVA_HOME'         // or whatever version you're using
    }

    environment {
        GIT_REPO = 'https://github.com/MSP612/cs_selenium_java_fw'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: "${env.GIT_REPO}"
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test -DsuiteXmlFile=testng.xml'
            }
        }

        stage('Post Actions') {
            steps {
                
            }
        }
    }

    post {
        always {
            echo 'Pipeline Finished.'
        }
    }
}
