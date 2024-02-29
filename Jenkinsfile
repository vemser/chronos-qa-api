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
                            def buildUrl = env.BUILD_URL
                            def buildResult = currentBuild.currentResult
                            def branchName = env.BRANCH_NAME
                            def buildNumber = env.BUILD_NUMBER

                            def message = "# Relatorio de Testes/API Chronos\n"
                            message += "**Branch:** RELEASE\n"
                            message += "**Build:** ${buildNumber}\n"
                            message += "**Status:** ${buildResult}\n"

                            def imagePath = 'C:/Users/rapha/screenshot.png'

                            discordSend description: message,
                                        image: [file: imagePath],
                                        webhookURL: "https://discord.com/api/webhooks/1212470165044731904/ySidL1sT1nHztTrTruu1SsT0HOZdnQ4ccS0FFAUvJ4vppmLRw5BwISDrdcCbKHBgxH4v"
                        }
        }
    }
}