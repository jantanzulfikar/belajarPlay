
name := "playApps"

version := "1.0"

lazy val `playapps` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(   "org.postgresql" % "postgresql" % "9.4-1206-jdbc4" , jdbc ,cache , ws   , specs2 % Test)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  