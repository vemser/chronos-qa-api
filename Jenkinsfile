pipeline {
    agent any



    stages {
        stage('Checkout API Repository') {
            steps {
                script {
                    checkout scm
                }
            }
        }
       stage('Test API Repository') {
            steps {
                script {
                    echo 'Iniciando etapa de teste para o primeiro repositório...'

                }
            }
        }
        stage('Checkout UI Repository') {
            steps {
                script {
                    bat 'rmdir /s /q repo_ui'
                    bat 'git clone -b dev https://github.com/vemser/chronos-qa-ui.git repo_ui'
                }
            }
        }
        stage('Test UI Repository') {
            steps {
                script {
                    bat 'cd repo_ui && mvn clean test -Dmaven.test.failure.ignore=true'
                }
            }
        }
        stage('Publish Allure Report') {
            steps {
                script {
                    bat 'allure generate -o allure-results'

                    archiveArtifacts 'allure-report/**'

                       dir('repo_ui') {
                            bat "allure generate -o allure-results"
                            def resultAPI = currentBuild.result
                        }

                    echo 'Arquivos de relatório Allure arquivados.'
                }
            }
        }
    }

post {
    always {
        allure([
            includeProperties: false,
            jdk: '',
            results: [
                [path: 'allure-results'],
                [path: 'repo_ui/allure-results']
            ]
        ])
            echo 'Pós-processamento concluído.'
            script {
                try {
                def buildUrl = env.BUILD_URL
                                def buildResult = currentBuild.currentResult
                                def branchName = env.BRANCH_NAME
                                def buildNumber = env.BUILD_NUMBER

                                def printAllure = bat(script: "cd C:\\Users\\rapha && node capture.js ${env.BUILD_NUMBER}", returnStdout: true).trim()
                                def link = "abc"
                                try {
                                    def matcher = (printAllure =~ /https?:\/\/[^\s]+/)
                                    link = matcher.find() ? matcher.group() : "Link não encontrado"
                                } catch (Exception e) {
                                    echo "Erro ao extrair o link da saída do comando: ${e.message}"
                                }

                                def message = "# Relatorio de Testes/API Chronos\n"
                                message += "**Branch:** RELEASE\n"
                                message += "**Build:** ${buildNumber}\n"
                                message += "**Status:** ${buildResult}\n"
                                message += "**Allure Report:** [Allure Report Link](https://bear-above-mole.ngrok-free.app/job/chronos-qa-api-pipeline/${buildNumber}/allure/)"


                                discordSend description: message,
                                    image: "${link}",
                                    webhookURL: "https://discord.com/api/webhooks/1212489472399118366/VtMuLFS2tdu_Qd7KWFiHDc3wSf3XF2E4bm6oSiEarIZk3hgV1Rne-VO-owSTeJiRLG0e"
                } catch (e) {
                    echo "Erro ao executar notificação para o Discord: ${e.message}"
                }
            }
        }
    }
}
