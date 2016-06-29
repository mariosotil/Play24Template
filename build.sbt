import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}

val projectMaintainer = "John Smith <john.smith@company.cxx>"
val projectName = "Play24Template"
val projectEntryPoint = projectName.toLowerCase

name := """%s""".format(projectName)

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  filters
)

routesGenerator := InjectedRoutesGenerator

dockerCommands := Seq(
  Cmd("FROM", "java:openjdk-8-jre"),
  Cmd("MAINTAINER", "%s".format(projectMaintainer)),
  Cmd("EXPOSE", "9000"),
  Cmd("ADD", "stage /"),
  Cmd("WORKDIR", "/opt/docker"),
  Cmd("RUN", "[\"chown\", \"-R\", \"daemon\", \".\"]"),
  Cmd("RUN", "[\"chmod\", \"+x\", \"bin/%s\"]".format(projectEntryPoint)),
  Cmd("USER", "daemon"),
  Cmd("ENTRYPOINT", "[\"bin/%s\", \"-J-Xms128m\", \"-J-Xmx512m\", \"-J-server\"]".format(projectEntryPoint)),
  ExecCmd("CMD")
)

javaOptions in Test += "-Dconfig.resource=test.conf"

