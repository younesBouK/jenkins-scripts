pipeline {
    agent {docker "ubuntu:latest"}
    stages {
        stage('Test') {
             steps{
                 sh 'node --version' 
             }
        }
   }
}
