node{
 stage("SCM Checkout")
 {
  git 'https://github.com/veeras/selenium-grid'
 }
 stage('compile package')
{
   def mvnHome = tool name: 'Maven', type: 'maven'
   bat "${mvnHome}/bin/mvn install"
}

}
