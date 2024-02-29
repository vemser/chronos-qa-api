pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                script {
                    checkout scm
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    bat 'mvn -e clean test'
                }
            }
        }
        stage('Publish Allure Report') {
            steps {
                script {
                    bat 'allure generate target/allure-results -o target/allure-report'

                    archiveArtifacts 'target/allure-report/**'
                }
            }
        }

    }

    post {
        always {
            allure(
                includeProperties: false,
                jdk: '',
                results: [[path: 'target/allure-results']]
            )
            script {
                            def jobName = env.JOB_NAME
                            def buildUrl = env.BUILD_URL
                            def buildResult = currentBuild.currentResult
                            def branchName = env.BRANCH_NAME
                            def buildNumber = env.BUILD_NUMBER

                            def message = "Relatório de Testes/API ${jobName}\n"
                            message += "Branch: ${branchName}\n"
                            message += "Build: ${buildNumber}\n"
                            message += "Status: ${buildResult}\n"

                            discordSend description: message,
                                        webhookURL: "https://discord.com/api/webhooks/1212470165044731904/ySidL1sT1nHztTrTruu1SsT0HOZdnQ4ccS0FFAUvJ4vppmLRw5BwISDrdcCbKHBgxH4v"
                        }
        }
    }
}