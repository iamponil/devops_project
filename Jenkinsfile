pipeline{
    agent any
    environment {
    DOCKERHUB_CREDENTIALS = credentials('dockerhub')
  }
    stages{

        stage('Cloning from GitHub') {
                steps {
                    git branch: 'master', url: 'https://github.com/iamponil/devops_project.git'
                }

            }

      stage('Clean'){
            steps {
                sh 'mvn clean '
            }

        }
        stage('Compile'){
            steps {
                sh 'mvn compile  -DskipTests'
            }
           }
 stage('Collect JaCoCo Coverage') {
             steps{
                    jacoco(execPattern: '**/target/jacoco.exec')
     }
         }

     stage('JUNIT /MOCKITO') {
       steps {
         sh 'mvn test jacoco:report'
         echo 'Test stage done'
       }
     }
 stage('SonarQube Analysis') {

   steps {
     withSonarQubeEnv('sonar-scanner') {
       sh 'mvn sonar:sonar'
     }
   }
 }
   stage('Deploy Nexus') {
            steps {
        sh 'mvn deploy -DskipTests'
            }
        }
 stage("Building Docker Image") {
                steps{
                    sh 'docker build -t iamponil/devops_project .'
                }
        }
          stage("Push to DockerHub") {
                                   steps {
                                               sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                                               sh 'docker push iamponil/devops_project'

                                       }
                                   }

 stage('Docker Compose') {
            steps {
                sh 'docker-compose up -d'
            }
        }
        }
        post {
            always {
                emailext(
                    subject: "Pipeline Status: ${currentBuild.currentResult}",
                    body: """<html>
                            <body>
                                <p>Build Status: ${currentBuild.currentResult}</p>
                                <p>Build Number: ${currentBuild.number}</p>
                                <p>Check the <a href="${BUILD_URL}">console output</a>.</p>
                            </body>
                            </html>""",
                    to: 'iamponilnemlaghi@gmail.com',
                    from: 'jenkins@example.com',
                    replyTo: 'jenkins@example.com',
                    mimeType: 'text/html'
                )
            }
        }

        }
