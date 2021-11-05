pipeline {
  agent any
  stages {
    stage('Deploy') {
      steps {
        ws(dir: '11') {
          junit 'test'
        }

        sh 'ddd'
      }
    }

  }
}