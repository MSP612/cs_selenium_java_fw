pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'   // use the name you gave in Jenkins Maven config
        jdk 'JAVA_HOME'         // or whatever version you're using
    }

    environment {
        GIT_REPO = 'https://github.com/your-username/selenium-grid-parallel.git'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: "${env.GIT_REPO}"
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Post Actions') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            echo 'Pipeline Finished.'
        }
    }
}
