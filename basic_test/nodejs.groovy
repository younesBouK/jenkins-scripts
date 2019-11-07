/**
this pipeline run a nodejs command (node --version) using nodejs pluging inside host
first install NodeJS tool, then go to global tools configuration 
and choose nodejs version that you need 
name this tool as 'node'  
**/

pipeline {
    agent any
    tools {nodejs 'node'}
    stages {
        stage ("first stage"){
            steps {
                sh "node --version"
            }
        }
    }
    
}