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

                bat "cd C:\\Users\\rapha && node capture.js ${buildNumber} > ${tempLogFile}"


                def printAllure = readFile(tempLogFile).trim()


                def linkStartIndex = printAllure.indexOf('http')
                def linkEndIndex = printAllure.indexOf(' ', linkStartIndex)
                def link = (linkStartIndex >= 0 && linkEndIndex > linkStartIndex) ? printAllure.substring(linkStartIndex, linkEndIndex) : null

                def message = "# Relatorio de Testes/API Chronos\n"
                message += "**Branch:** RELEASE\n"
                message += "**Build:** ${buildNumber}\n"
                message += "**Status:** ${buildResult}\n"
                message += "**Allure Output:**\n${link}"

                discordSend description: message,
                    webhookURL: "https://discord.com/api/webhooks/1212470165044731904/ySidL1sT1nHztTrTruu1SsT0HOZdnQ4ccS0FFAUvJ4vppmLRw5BwISDrdcCbKHBgxH4v"
            }
        }
    }
}
