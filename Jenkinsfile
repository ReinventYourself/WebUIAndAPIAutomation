pipeline {
    agent any
    
       environment {
        BUILD_VERSION = "${env.BUILD_NUMBER}"
        JOB_Name ="${env.JOB_NAME}"
        def XmlFile='testng.xml'
    }
    
   
    stages {
    
    stage('Setup parameters') {
            steps {
                script { 
                    properties([
                        parameters([
                            choice(
                                choices: ['QA', 'Staging'], 
                                description: 'Please choose the Environment where you want to execute the test',
                                name: 'Env'
                            ),
                             choice(
                                choices: ['False', 'True'],
                                description: 'Select True if you want to send report to the configured recipient',
                                name: 'SendEmail'
                            )
                            choice(
                                choices: ['Chrome', 'Firefox'],
                                description: 'Please choose the Browser',
                                name: 'Browser'
                            )
                            
                            ]
                            )
                            ]
                            )
                            }
    }
    }
        stage('Checkout') {
            steps {
                   checkout scm
            }
        }


        stage('Build and Test') {
            steps {
             script {
                    
                    workspaceDir = "${WORKSPACE}"
                    //echo "this is work ${workspaceDir}"
                    def propertiesFile = "${workspaceDir}/src/main/resources/config.properties"
                  //  echo propertiesFile
                    def properties = new Properties()
                    properties.load(new FileInputStream(propertiesFile))
                    properties.put('Env', params.Env)
                    properties.put('SendEmail', params.SendEmail)
                    properties.put('Browser', params.Browser)
                    properties.store(new FileWriter(propertiesFile), null) 
                    //echo XmlFile
                    //echo "job name ${JOB_Name}"
                  if (JOB_Name.contains('Smoke')) {
                      XmlFile = 'testngSmoke.xml'
                      //echo "update file ${XmlFile}"
                        } 
                                  
                       //echo XmlFile 
                    
                }
                
                bat "mvn clean install -Dsurefire.suiteXmlFiles=${XmlFile}"
            }
        }
        
}
       
}