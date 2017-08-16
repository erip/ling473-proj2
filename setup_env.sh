#!/usr/bin/env bash

function run() {
  export JAVA_HOME="/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.141-1.b16.el7_3.x86_64/jre/"

  # Add Scala to path
  export PATH="$PATH:/usr/share/scala/bin"

  # Add SBT to path
  export PATH="$PATH:/usr/share/sbt/bin"
  sbt --warn "run /corpora/LDC/LDC02T31/nyt/2000"
}

function run_test() {
  export JAVA_HOME="/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.141-1.b16.el7_3.x86_64/jre/"

  # Add Scala to path
  export PATH="$PATH:/usr/share/scala/bin"

  # Add SBT to path
  export PATH="$PATH:/usr/share/sbt/bin"

  sbt test
}
