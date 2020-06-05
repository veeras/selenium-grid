node{
 stage("preparing selenium grid hub and node dockers"){
  bat 'docker network create grid'
  bat  'docker run -d -p 4444:4444 --net grid --name selenium-hub selenium/hub'
  bat  'docker run -d --net grid -e HUB_HOST=selenium-hub -v /dev/shm:/dev/shm selenium/node-chrome'
 }
 stage("SCM Checkout"){
  git 'https://github.com/veeras/selenium-grid'
 }
 stage('compile package'){
  // def mvnHome = tool name: 'Maven', type: 'maven'
  // bat "${mvnHome}/bin/mvn install"
  bat 'mvn install' 
}

 stage('removing all runnning containers'){
   bat "rmContainers.bat"
 }
}
