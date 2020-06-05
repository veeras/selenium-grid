node{
 stage("preparing selenium grid hub and node dockers")
 {
  bat 'docker network create grid'
  bat 'sleep 1'
  bat  'docker run -d -p 4444:4444 --net grid --name selenium-hub selenium/hub'
  bat  'docker run -d --net grid -e HUB_HOST=selenium-hub -v /dev/shm:/dev/shm selenium/node-chrome'
  bat 'sleep 1'
 }
 stage("SCM Checkout")
 {
  git 'https://github.com/veeras/selenium-grid'
  bat 'sleep 1'
 }
 stage('compile package')
{
  // def mvnHome = tool name: 'Maven', type: 'maven'
  // bat "${mvnHome}/bin/mvn install"
  bat 'mvn install'
  bat 'sleep 1'
}

}
