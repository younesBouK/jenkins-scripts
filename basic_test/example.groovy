pipeline {
    agent any
    tools {nodejs 'node'}
    stages {
        stage (example_1){
            steps {
                sh "npm config ls"
                sh "echo 'ji younes'"
            }
        }
        stage (example_2){
            steps{
                sh "java -version"
            }
        }
    }
    
}