pipeline {
    agent any
     tools {
        maven 'local_maven'
    }

    stages {
        stage('service-registry') {
            when {
                changeset "service-registry/**"
            }
            steps {
                 dir('service-registry') {
                    //bat 'npm uninstall -g node-gyp'
                    //bat 'npm install -g node-gyp'
                    bat 'mvn clean package'
                    //bat 'docker build -t ms-patient:$BUILD_NUMBER .'           
                    //echo 'ms-patient Build Image Completed'
                }
            }
                
        }      
      
  } 
}
