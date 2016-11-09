node {
    stage('SCM') {
        git branch: 'release', url: 'git@github.com:HaneYuzuru/risk_manage.git'
    }
    stage('QA') {
        def scannerHome = tool 'sonarqube';
        sh "${scannerHome}/bin/sonar-scanner"
    }
    stage('build') {
        def mvnHome = tool 'M3'
        sh "${mvnHome}/bin/mvn -B clean package"
    }
    stage('deploy') {
        sh "docker stop my || true"
        sh "docker rm my || true"
        sh "docker run --name my -p 11111:8080 -d tomcat"
        sh "docker cp target/risk_manage.war my:/usr/local/tomcat/webapps"
    }
    stage('results') {
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }
}