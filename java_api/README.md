#### api

* Gradle
* Java
* Spock
* Dropwizard

#### Tasks

##### Test

Run tests
```
./gradlew test
```
or
```
./gradlew test --continuous
```

Including integration (start up server & hit a pair of endpoints as sanity check)
```
integration=1; ./gradlew test
```

Test results
```
open build/reports/tests/index.html
```

##### Run app

```
./gradlew run
```

#### References

* [Dropwizard](http://www.dropwizard.io/0.9.2/docs/getting-started.html)
* [Gradle Java plugin](https://docs.gradle.org/current/userguide/java_plugin.html)
* [Gradle Groovy plugin](https://docs.gradle.org/current/userguide/groovy_plugin.html)
* [Gradle application plugin](https://docs.gradle.org/current/userguide/application_plugin.html)
* [Spock docs](http://spockframework.github.io/spock/docs/1.0/spock_primer.html)
