node {
    stage('SCM') {
        git branch: 'develop', url: 'git@github.com:HaneYuzuru/risk_manage.git'
    }
    stage('QA') {
        sh "sonar-scanner"
    }
    stage('build') {
        def mvnHome = tool 'M3'
        sh "${mvnHome}/bin/mvn -B clean package -Dmaven.test.skip=true"
    }
    stage('deploy') {
        sh "whoami ; service docker status; docker run hello-world; docker stop my || true"
        sh "docker rm my || true"
        sh "docker run --name my -p 11111:8080 -d tomcat"
        sh "docker cp target/risk_manage-release-1.0-SNAPSHOT.war my:/usr/local/tomcat/webapps"
    }
    stage('results') {
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }
}
