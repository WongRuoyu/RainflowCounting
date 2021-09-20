name := "RainflowCounting"

version := "0.1"

scalaVersion := "2.12.3"

idePackagePrefix := Some("com.wong")

libraryDependencies ++= {
    Seq(
      "org.scalanlp" %% "breeze" % "1.1",
      "org.typelevel" %% "cats-effect" % "2.5.1",
      "org.scodec" %% "scodec-stream" % "2.0.2",
      "org.scodec" %% "scodec-protocols" % "2.0.0",
      "org.scodec" %% "scodec-cats" % "1.1.0",
      "co.fs2" %% "fs2-core" % "2.5.6",
      "co.fs2" %% "fs2-io" % "2.5.6",
      "org.specs2"        %% "specs2-core"        % "3.9.4"              % "test",
      "ch.qos.logback"    % "logback-classic"     % "1.2.3",
      "com.h2database"    % "h2"                  % "1.4.187",
    )
  }
