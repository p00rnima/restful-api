# restful-api

[![standard-readme compliant](https://img.shields.io/badge/standard--readme-OK-green.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)

> Restful API Automation suite    

## Table of Contents

- [Prerequisites](#Prerequisites)
- [Install](#install)
- [Usage](#usage)
- [Contribute](#contribute)

## Prerequisites

Install the below packages 

- Mac
 * [Homebrew](https://brew.sh/)
 * [Java 1.11](https://stackoverflow.com/questions/52524112/how-do-i-install-java-on-mac-osx-allowing-version-switching)
 * Git - Run `brew install git`
 * Gradle - Run `brew install gradle`
 * IntelliJ / Eclipse IDE for Mac
 * Findbugs plugin for Intellij [https://plugins.jetbrains.com/plugin/3847-findbugs-idea] -- Optional
 ```The FindBugs plugin for IntelliJ IDEA 
    Provides static byte code analysis to look for bugs in Java code from within IntelliJ IDEA.
    FindBugs is a defect detection tool for Java that uses static analysis to look for more than 200 bug patterns,
    such as null pointer dereferences, infinite recursive loops, bad uses of the Java libraries and deadlocks
 ```
 * SonarLint plugin for Intellij [https://plugins.jetbrains.com/plugin/7973-sonarlint] -- Optional
 ```SonarLint is an IDE extension that helps you detect and fix quality issues as you write code. Like a spell checker,
    SonarLint squiggles flaws so they can be fixed before committing code.
 ```
    
 * Gherkin plugin for Intellij
 * Gradle plugin for Intellij
 * Lombok plugin for Intellij [https://www.vogella.com/tutorials/Lombok/article.html]
 ```
 Lombok is used to reduce boilerplate code for model/data objects,
 e.g., it can generate getters and setters for those object automatically by using Lombok annotations.
 The easiest way is to use the @Data annotation.
 ```
 * TestNg-J plugin for Intellij


- Windows
 * [Java 1.11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
 * [Git on windows](https://git-scm.com/download/win)
 * [Gradle on windows](https://gradle.org/install/)
 * IntelliJ / Eclipse IDE for Windows
 * Findbugs plugin for Intellij
 * SonarLint plugin for Intellij
 * Gherkin plugin for Intellij
 * Gradle plugin for Intellij
 * Lombok plugin for Intellij
 * TestNg-J plugin for Intellij

## Install

Follow the below steps to configure the project within IntelliJ IDEA 
- Clone this repository and import project to IntelliJ IDEA
- Enable annotation processing from IntelliJ Preferences
- Execute generateJsonSchema2Pojo build task to generate JsonSchema Pojo’s
- Mark the js2p [main] folder in restful-api/build/generated-sources directory as Generated Sources Root  
- Execute compile task from gradle options of restful-api to compile 


Currently, the automated tests can be run against local environment based on settings in the PropertyHelper class. 

Use the below command to run all required tests:

 ```gradle clean testAPI -Denv=dev -Dcucumber.options="--tags @restful-api"```
 
  Please replace the cucumber options tags to run any specific tests.

Replace this env config value in PropertyHelper.java class
```
public static String getEnv() { return sysArg("env", "local");}
```

## Contribute

To contribute, you will need proper access rights to this repository. General practice within the team is to create individual branches from a sprint/develop branch. Pull requests will have to be approved by at least one other team member before your work can be merged back.

Small note: If editing the README, please conform to the [standard-readme](https://github.com/RichardLitt/standard-readme) specification.
