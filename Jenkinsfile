node {
    stage('SCM') {
        git branch: 'develop', url: 'git@github.com:HaneYuzuru/risk_manage.git'
    }
    stage('QA') {
        def scannerHome = tool 'sonarqube';
        sh "${scannerHome}/bin/sonar-scanner"
    }
    stage('build') {
        def mvnHome = tool 'M3'
        sh "${mvnHome}/bin/mvn -B clean package -Dmaven.test.skip=true"
    }
    stage('deploy') {
        sh "sudo su && docker stop my || true"
        sh "sudo su && docker rm my || true"
        sh "sudo su && docker run --name my -p 11111:8080 -d tomcat"
        sh "sudo su && docker cp target/risk_manage.war my:/usr/local/tomcat/webapps"
    }
    stage('results') {
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }
}
