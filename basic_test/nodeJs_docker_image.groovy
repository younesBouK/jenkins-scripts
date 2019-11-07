/***
this pipeline run a nodejs commnd (npm --version) inside nodejs docker container
**/

pipeline {
    agent { docker { image 'node:6.3' } }
    stages {
        stage('first stage') {
            steps {
                sh 'npm --version'
            }
        }
    }
}