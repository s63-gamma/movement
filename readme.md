#Installation
This project uses docker and offers a docker-compose config. 
With the docker-compose the infrastructure for the application to work are installed and configured.
Assuming docker and docker-compose are installed on your system you can use the following command to run the containers.
```
docker-compose up -d
```
If for whatever reason you want to stop the containers you can use.
```
docker-compose down
```

# Running the application
Currently the docker image doesn't contain a wildfly installation. 
Therefor to run the project locally you will have to provide a working wildfly application server.
For deployment you can either use [the following configuration and pointing to the wildfly folder](https://www.jetbrains.com/help/idea/2016.3/run-debug-configuration-jboss-server.html).
Or you can drop the war in `target/` to your wildflies deployment directory.

# Formatting
This projects offers a `.editorconfig` file. If your text editor support editorconfig (most of them do) it will automagically use our configuration.

# Commits

Commits in this project follow the [AngularJS commit message conventions](https://github.com/angular/angular.js/blob/master/CONTRIBUTING.md#-git-commit-guidelines).
This project is commitzen friendly. 
You can install commitizen with the following command (assuming you have node installed).
 ```
 npm install -g commitizen cz-conventional-changelog
 ```
 When you want to make a commit  you can format your code into the appropriate format with the following command.
 
 ```
 git cz
 ```
