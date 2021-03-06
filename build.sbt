import sbt.Keys._

scalaVersion in Global := "2.10.4"

logLevel := Level.Info

scalacOptions in compile in Compile ++= Seq(
  "-Xlint",
  "-Ywarn-dead-code"
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-target:jvm-1.8",
  "-encoding", "UTF-8"
)

javacOptions in compile in Compile ++= Seq(
  "-source", "1.8", 
  "-target", "1.8"
)

javaOptions ++= Seq(
  "-Duser.language=en-US"
)

val cancellable_future = project.in(file("."))
            .enablePlugins(PlayScala)
            .settings(
             name := "cancellable_future",
             version := "1.0.0",
             scalaVersion := "2.11.7"
           )
