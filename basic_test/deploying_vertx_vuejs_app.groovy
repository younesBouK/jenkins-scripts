pipeline{
    agent any
    stages{
        stage("cleaning workspace"){
            steps{
                cleanWs()
            }
        }
        stage("clone project"){
            steps{
                // create a credential object in jenkins
                // git credentialsId:'github_by_example' , url: 'repo_url' , branch: 'master'
                git url: 'http://username:(password|token)@repo_url' , branch: 'master'
           }
        }
         stage("Changing config to prod"){
            steps{
                echo "Changing config to prod ...."
                sh """
                   #cp config_prod.json config.json
                """
            }
        }
        stage("building web"){
            tools {nodejs 'node'}
            steps{
                sh """
                    cd web
                    npm install
                    npm run build
                    cd ..
                """
            }
        }
        stage("building jar"){
            tools {gradle "gradle"}
            steps{
                sh """
                    rm -fR gradle .gradle
                    gradle shadowjar
                    """
            }
        }
        stage("preparing project for deploying"){
            steps{
                sh """
                    cp build/libs/*.jar app.jar
                    cp src/log4j.properties log4j.properties
                    chmod +x app.jar
                """
            }
        }
        stage("deploying in docker"){
            agent {
                docker {
                    image "openjdk:8"
                    args '-v "$WORKSPACE":/home/ -p 7070:9001 --name app_by_jenkins -d'
                    reuseNode true
                }
            }
            steps{
                sh """
                    chmod +x app.jar
                    java -Dvertx.logger-delegate-factory-class-name=io.vertx.core.logging.Log4jLogDelegateFactory -Dlog4j.configurationFile=file:./log4j.properties -jar ./app.jar -conf ./config.json
                """
            }
        }
    }
}