fastlane documentation
================
# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```
xcode-select --install
```

Install _fastlane_ using
```
[sudo] gem install fastlane -NV
```
or alternatively using `brew install fastlane`

# Available Actions
## Android
### android test
```
fastlane android test
```
Runs all the tests
### android deploy
```
fastlane android deploy
```
Deploy a new version to the Google Play
### android internal
```
fastlane android internal
```
Submit a new Internal Test
### android deploy_pro
```
fastlane android deploy_pro
```
Deploy a new version to the Google Play (PRO)
### android internal_pro
```
fastlane android internal_pro
```
Submit a new Internal Test (PRO)
### android deploy_persian
```
fastlane android deploy_persian
```
Deploy a new version to the Google Play (PERSIAN)
### android internal_persian
```
fastlane android internal_persian
```
Submit a new Internal Test (PERSIAN)
### android screenshots
```
fastlane android screenshots
```
Build debug and test APK for screenshots

----

This README.md is auto-generated and will be re-generated every time [fastlane](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
