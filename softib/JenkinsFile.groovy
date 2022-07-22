pipeline {
    agent any
    environment {
    registry = "mgara07/softib"
    registryCredential = 'DockerHub'
    dockerImage = ''
  }
    stages {
         stage('clone and clean repo') {
            steps {
                git changelog: false, branch: 'malekBranch',  credentialsId: 'mgara07', poll: false, url: 'https://github.com/majallouz/PI-SoftIB.git'
                
            }
        }
        stage('Test') {
            steps { 
                sh "cd softib"
                bat 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        stage('Sonar') {
            steps {
              sh "cd softib"
                bat 'mvn sonar:sonar'
            }
        }
        stage('Deploy') {
            steps {
              sh "cd softib"
                bat 'mvn package deploy '
            }
        }
       
        stage('Building image') {

        steps {
          sh "cd softib"

          script {

            dockerImage = docker.build registry + ":$BUILD_NUMBER"

          }

        }

      }

    
    stage('Deploy image') {

      steps {
        sh "cd softib"

        script {

          docker.withRegistry('', registryCredential) {

            dockerImage.push()

          }

        }

      }

    }
     
        stage('clean ws') {

            steps {
              sh "cd softib"
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
