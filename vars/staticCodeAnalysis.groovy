def call(){
    withSonarQubeEnv(credentialsId: credentialsId) {
        sh 'mvn clean package sonar:sonar'
    }
}