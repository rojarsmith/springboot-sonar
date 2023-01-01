# springboot-sonar

The project template for building & test agile development.

![example workflow](https://github.com/rojarsmith/springboot-sonar/actions/workflows/build.yml/badge.svg)

## Deploy

### Manual

```bash
## Remote side : ubuntu

mkdir /root/service/app/springboot-sonar

## Local side : Windows

# Install Windows PowerShell from github, OpenJDK 17.x, Gradle 7.x, git.

# Generate ssh key and deploy to github.

git Clone git@github.com:rojarsmith/springboot-sonar.git

gradle -b springboot-sonar/build.gradle bootJar

cp springboot-sonar/build/libs/* $ENV:TEMP
cp springboot-sonar/ecosystem.config.js $ENV:TEMP

ls $ENV:TEMP/springboot-sonar-0.0.1-SNAPSHOT.jar
ls $ENV:TEMP/ecosystem.config.js

# For auto script
# Example: C:\Users\ROJARS~1\AppData\Local\Temp\springboot-sonar-0.0.1-SNAPSHOT.jar
$UPLOAD_NAME="$ENV:TEMP/springboot-sonar-0.0.1-SNAPSHOT.jar"

sftp root@REMOTE_IP

cd /root/service/app/springboot-sonar

# Example: C:\springboot-sonar-0.0.1-SNAPSHOT.jar
put FILE_PATH_APP_JAR
put FILE_PATH_ECOSYSTEM_CONFIG

## Remote side : ubuntu

# Install OpenJDK 17.x, nvm, nodejs, PM2.

mv springboot-sonar-0.0.1-SNAPSHOT.jar springboot-sonar.jar

vi ecosystem.config.js

# Modify path & config
"args":[
  "-jar",
  "/root/app/springboot-sonar/springboot-sonar.jar",
  "--spring.config.location=/root/app/config/application-prod.properties"]

pm2 startOrRestart ecosystem.config.js

pm2 ls
```

### Auto

```bash
# Modify ecosystem.config.js

## Remote side : ubuntu

# Install OpenJDK 11.x, Gradle 7.x, nvm, pm2, git.

# Generate ssh key and deploy to github.

mkdir /root/service/app/springboot-sonar

## Local side : Windows

# Install Windows PowerShell from github, nvm-windows, pm2, git.

# Generate ssh key and deploy to github.

# Move to the path of source code.

pm2 deploy ecosystem.config.js production setup

pm2 deploy ecosystem.config.js production
```

## Test service

```bash
# Result: hello TAG2
curl -w '\n' http://localhost:8080/status/hello
```

