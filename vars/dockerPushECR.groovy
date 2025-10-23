// def call(String aws_account_id, String region, String ecr_repoName){
    
//     sh """
//      aws ecr get-login-password --region ${region} | docker login --username AWS --password-stdin ${aws_account_id}.dkr.ecr.${region}.amazonaws.com
//      docker push ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repoName}:latest
//     """
// }


def call(String aws_account_id, String region, String ecr_repoName) {
    withCredentials([
        string(credentialsId: 'aws_access_key_id', variable: 'AWS_ACCESS_KEY_ID'),
        string(credentialsId: 'aws_secret_access_key', variable: 'AWS_SECRET_ACCESS_KEY'),
        string(credentialsId: 'aws_session_token', variable: 'AWS_SESSION_TOKEN')
    ]) {
        withEnv([
            "AWS_DEFAULT_REGION=${region}",
            "ECR_REGISTRY=${aws_account_id}.dkr.ecr.${region}.amazonaws.com",
            "REPO_NAME=${ecr_repoName}"
        ]) {
            sh '''
                aws ecr get-login-password --region ${AWS_DEFAULT_REGION} | docker login --username AWS --password-stdin ${ECR_REGISTRY}
                
                docker push ${ECR_REGISTRY}/${REPO_NAME}:latest
            '''
        }
    }
}