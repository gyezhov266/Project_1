pipeline{
    agent{
        kubernetes{
            inheritFrom "devops"
        }
    }

    environment{
        DEVOPS_REGISTRY='teamkuberknights/devops'
        DEVOPS_IMAGE=''
    }

    stages{
        stage("build and push docker image"){
            steps{
                container("project_2"){
                    script{
                        DEVOPS_IMAGE=docker.build(DEVOPS_REGISTRY, ".")
                        docker.withRegistry("", 'docker-creds'){
                            DEVOPS_IMAGE.push("$currentBuild.number")
                        }
                    }
                }
            }
        }
    }
}