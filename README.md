Qilin Experiment
================

This repository holds an experiment to get to know
[Qilin](http://qilin.seas.harvard.edu/ "Homepage for Qilin").

Qilin is defined on its website to be a

> An Open-Source Java SDK for rapid prototyping of cryptographic
> protocols.

Setup
-----

We will use [maven](http://maven.apache.org/ "Homepage of Maven")
as our build system. You can find 
[detailed instructions](http://maven.apache.org/users/index.html "Maven Users Centre")
on how to use maven on the web.

### Qilin Dependency

Qilin uses 
[Mercurial](http://mercurial.selenic.com/ "Homepage of Mercurial")
as a version control system and uses
[ant](http://ant.apache.org/ "Homepage of Ant") as a build system.

In order to use Qilin as a dependency for a maven project, Qilin
should be installed into the local maven repository as an
artifact. Use the following steps to achieve this goal.

1. Clone the Qilin project.

    > hg clone http://qilin.seas.harvard.edu/repo qilin-sdk

2. Package Qilin

    > cd qilin-sdk
    > ant

3. Install Qilin into maven repository

    > mvn install:install-file -Dfile=phishmarketAll.jar -DgroupId=phishmarket \
    -DartifactId=all -Dversion=1.0.0 -Dpackaging=jar

Qilin can now be used as a dependency to the maven project by
including the following snippit in your pom.

    <dependency>
      <groupId>phishmarket</groupId>
      <artifactId>all</artifactId>
      <version>1.0.0</version>
    </dependency>