Hello world for Apache Maven
============================

#### Maven managed project structure
${basedir}: for pom.xml and all sub-directories
    |- src/main/java/: source code of the project
    |- src/main/resources/: resources of the project, like property file
    |- src/test/java/: testing code of the project, like JUnit code
    |- src/test/resources/: resources of the testing
    |- target/: jar file after build
    |- target/class/: class file during build

Take this helloworld project for example:
```
maven/helloworld$ tree
.
|-- pom.xml
`-- src
    |-- main
    |   `-- java
    |       `-- com
    |           `-- caihua
    |               `-- helloworld
    |                   `-- App.java
    `-- test
        `-- java
            `-- com
                `-- caihua
                    `-- helloworld
                        `-- AppTest.java
```

#### Install Maven
http://maven.apache.org/download.cgi

#### Check maven version
mvn -v

#### Create project
mvn archetype:generate -DgroupId=com.caihua.helloworld -DartifactId=helloworld -Dpackage=com.caihua.helloworld -Dversion=1.0

#### Build
mvn package

#### Run
java -cp target/helloworld-1.0.jar com.caihua.helloworld.App

#### Reference
http://www.oracle.com/technetwork/cn/community/java/apache-maven-getting-started-1-406235-zhs.html
