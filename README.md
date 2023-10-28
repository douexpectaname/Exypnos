# Exypnos

ἔξυπνος / awake (Biblical Greek) - Android app as an attempt to the application
of a medical research

# Build instructions

| item           | version                                     |
|:---------------|:--------------------------------------------|
| Android Studio | Android Studio Iguana \| 2023.2.1 Canary 11 |
| JDK            | Any JDK not below version 11                |

```shell
git clone https://github.com/douexpectaname/Exypnos.git
cat << EOF > local.properties
sdk.dir=<Abs path to your android sdk>
EOF

./gradlew assembleDebug
```
