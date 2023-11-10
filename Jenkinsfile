pipeline {
    agent any
     tools {
        maven 'local_maven'
        nodejs 'nodejs'
    }

    stages {

        stage('common') {
            steps {
                 dir('common') {
                    bat 'mvn clean install'
                    //bat 'docker build -t common:$BUILD_NUMBER .'           
                    //echo 'common Build Image Completed'
                }
            }
                
        }  
        
        stage('service-registry') {
            steps {
                 dir('service-registry') {
                    bat 'npm uninstall -g node-gyp'
                    bat 'npm install -g node-gyp'
                    bat 'mvn clean package'
                    //bat 'docker build -t ms-patient:$BUILD_NUMBER .'           
                    //echo 'ms-patient Build Image Completed'
                }
            }
                
        }      
      
  } 
}
