# gradle-classpath-bug

Reproduces https://discuss.gradle.org/t/getbytype-or-withtype-failing-in-composite-build/42136

## Instructions

```
$ cd simple-plugin
$ ./gradlew publishToMavenLocal
$ cd ../registers-ok/composite
$ ./gradlew checkRegistrations 

> Configure project :
Registering checkRegistrations in composite

> Task :checkRegistrations
Included: [included-one, included-two]
Registered: [included-one, included-two]
OK!

BUILD SUCCESSFUL in 554ms
1 actionable task: 1 executed

$ cd ../../only-one-registers/composite
$ ./gradlew checkRegistrations

> Configure project :
Registering checkRegistrations in composite

> Task :checkRegistrations FAILED
Included: [included-one, included-two]
Registered: [included-one]

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':checkRegistrations'.
> Projects did not register - [included-two]

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.

* Get more help at https://help.gradle.org

BUILD FAILED in 12s
1 actionable task: 1 executed

```
