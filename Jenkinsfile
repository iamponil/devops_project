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

     stage('JUNIT TEST with JaCoCo') {
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
        }
}