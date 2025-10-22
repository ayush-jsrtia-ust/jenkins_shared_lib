def call(String project, String ImageTag, String hubUser){
    withCredentials([string(credentialsId: 'docker', variable: 'PASS')]) {
        sh "docker login -u ${hubUser} -p ${PASS}"
        sh "docker push ${hubUser}/${project}:${ImageTag}"
        sh "docker push ${hubUser}/${project}:latest"
    }
}