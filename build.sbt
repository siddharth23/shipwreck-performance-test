
name := "shipwreck-performance-test"

version := "1.0"

scalaVersion := "2.11.7"
import io.gatling.sbt.GatlingPlugin
lazy val project = Project("SMPerformance", file("."))
  .enablePlugins(GatlingPlugin)
  .settings(scalaVersion := "2.11.7")
  .settings(libraryDependencies ++= Seq(
    "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.1.7" % "test",
    "io.gatling" % "gatling-test-framework" % "2.1.7" % "test"
  )).settings(resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")