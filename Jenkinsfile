pipeline{
    agent any

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
                                       script {
                                           docker.withRegistry('https://registry.hub.docker.com', 'Docker') {
                                               sh 'docker push iamponil/devops_project'
                                           }
                                       }
                                   }

                }
 stage('Docker Compose') {
            steps {
                sh 'docker-compose up -d'
            }
        }

        }
}