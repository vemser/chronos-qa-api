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
                                        image: "https://i.imgur.com/q1kkUPz.png",
                                        webhookURL: "https://discord.com/api/webhooks/1212489472399118366/VtMuLFS2tdu_Qd7KWFiHDc3wSf3XF2E4bm6oSiEarIZk3hgV1Rne-VO-owSTeJiRLG0e"
                        }
        }
    }
}