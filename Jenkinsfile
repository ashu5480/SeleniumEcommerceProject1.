pipeline {
    agent any

    tools {
        jdk 'jdk21'
        maven 'Maven'
    }

    stages {
        stage('Run Tests') {
            steps {
                bat 'mvn clean test'
                echo "Test"
            }
        }
    }
}