pipeline {
    agent any
    environment {
    registry = "mgara07/softib"
    registryCredential = 'DockerHub'
    dockerImage = ''
    WORKSPACE = 'C:/ProgramData/Jenkins/.jenkins/workspace/pipeline'
  }
    


    stages {
         stage('clone and clean repo') {
            steps {
                git changelog: false, branch: 'malekBranch',  credentialsId: 'mgara07', poll: false, url: 'https://github.com/majallouz/PI-SoftIB.git'
                
            }
        }
        stage('Test') {
            steps { 
                ws("${WORKSPACE}/softib") {
                bat 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        }
        stage('Sonar') {
            steps {
                ws("${WORKSPACE}/softib"){
                bat 'mvn sonar:sonar'
            }
        }
        }
        stage('Deploy') {
            steps {
              ws("${WORKSPACE}/softib"){
                bat 'mvn package deploy '
            }
        }
        }
       
        stage('Building image') {

        steps {
         
            ws("${WORKSPACE}/softib"){
          script {

            dockerImage = docker.build registry + ":$BUILD_NUMBER"

          }

        }

      }
        }

    
    stage('Deploy image') {

      steps {
        ws("${WORKSPACE}/softib"){

        script {

          docker.withRegistry('', registryCredential) {

            dockerImage.push()

          }

        }

      }

    }
    }
        stage('clean ws') {

            steps {
              
                    cleanWs()
            }

        }
    }
    post { 
        always {            
            emailext body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
                        recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']],
                        subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
                        to: '$DEFAULT_RECIPIENTS'
        }
    } 
}
