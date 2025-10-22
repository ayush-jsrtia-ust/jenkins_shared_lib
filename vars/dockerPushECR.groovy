def call(String aws_account_id, String region, String ecr_repo_name){
    sh """
        aws ecr get-login-password | docker login --username AWS --password-stdin ${aws_account_id}.dkr.ecr.${region}.amazonaws.com
        docker push ${ecr_repo_name}:latest 
        docker push ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repo_name}:latest
    """
}