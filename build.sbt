import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

val Scala213 = "2.13.7"

ThisBuild / crossScalaVersions := Seq("2.12.15", Scala213)
ThisBuild / scalaVersion := Scala213

ThisBuild / testFrameworks += new TestFramework("munit.Framework")

val catsV = "2.7.0"
val catsEffectV = "3.3.3"
val fs2V = "3.2.3"
// val http4sV = "0.23.7" // reproduces
val http4sV = "0.23.8-312-55d4080-SNAPSHOT" // should fix
val munitCatsEffectV = "1.0.7"


// Projects
lazy val `test-ember-timeout` = project.in(file("."))
  .disablePlugins(MimaPlugin)
  .enablePlugins(NoPublishPlugin)
  .aggregate(core.jvm, core.js)

lazy val core = crossProject(JVMPlatform, JSPlatform)
  .crossType(CrossType.Pure)
  .in(file("core"))
  .settings(
    name := "test-ember-timeout",
    fork := true,

    libraryDependencies ++= Seq(
      "org.typelevel"               %%% "cats-core"                  % catsV,
      "org.typelevel"               %%% "cats-effect"                % catsEffectV,

      "co.fs2"                      %%% "fs2-core"                   % fs2V,
      "co.fs2"                      %%% "fs2-io"                     % fs2V,

      "org.http4s"                  %%% "http4s-dsl"                 % http4sV,
      "org.http4s"                  %%% "http4s-ember-server"        % http4sV,
      "org.http4s"                  %%% "http4s-ember-client"        % http4sV,
      "org.http4s"                  %%% "http4s-circe"               % http4sV,

      "org.typelevel"               %%% "munit-cats-effect-3"        % munitCatsEffectV         % Test,

    )
  ).jvmSettings(
    assembly / assemblyJarName := "example.jar",
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % "1.2.3"
    )
  ).jsSettings(
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule)},
  )

lazy val site = project.in(file("site"))
  .disablePlugins(MimaPlugin)
  .enablePlugins(DavenverseMicrositePlugin)
  .dependsOn(core.jvm)
  .settings{
    import microsites._
    Seq(
      micrositeDescription := "Test Ember Timeout",
    )
  }
