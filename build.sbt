lazy val `project-two` = (project in file("."))
    .settings(
      name := "project2",
      scalaVersion := "2.12.2",
      version := "0.0.1-SNAPSHOT",
      libraryDependencies ++= Dependencies.projectTwoDependencies,
      mainClass in Compile := Some("edu.washington.rippeth.ling473.proj2.ProjectTwoDriver"),
      logLevel in run := Level.Warn,
      showSuccess := false
    )

        