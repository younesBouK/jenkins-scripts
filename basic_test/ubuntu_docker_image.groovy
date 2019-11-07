/***
this pipeline run a sh command (echo "hello world") inside ubuntu docker container
**/

pipeline {
    agent {docker "ubuntu:latest"}
    stages {
        stage('first stage') {
             steps{
                 sh 'echo "hello world"' 
             }
        }
   }
}
