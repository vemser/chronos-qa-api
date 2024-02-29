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
                    bat 'mvn -e clean test -Dmaven.test.failure.ignore=true'
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

                            def captureOutput = bat(script: 'cd C:\\Users\\rapha && node capture.js %BUILD_NUMBER%', returnStdout: true).trim()

                            def message = "# Relatorio de Testes/API Chronos\n"
                            message += "**Branch:** RELEASE\n"
                            message += "**Build:** ${buildNumber}\n"
                            message += "**Status:** ${buildResult}\n"

                            discordSend description: message,
                                        image: "https://imagizer.imageshack.com/img922/609/cSyy7S.png",
                                        webhookURL: "https://discord.com/api/webhooks/1212470165044731904/ySidL1sT1nHztTrTruu1SsT0HOZdnQ4ccS0FFAUvJ4vppmLRw5BwISDrdcCbKHBgxH4v"
                        }
        }
    }
}