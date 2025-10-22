def call(String aws_account_id, String region, String ecr_repo_name){
    sh """
        export ECR_PASSWORD=$(aws ecr get-login-password --region ${region})
        docker login --username AWS --password $ECR_PASSWORD ${aws_account_id}.dkr.ecr.${region}.amazonaws.com
        docker tag ${ecr_repo_name}:latest ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repo_name}:latest
        docker push ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repo_name}:latest
    """
}   