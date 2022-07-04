                ----- Final Theme 1 - Cloud Native -----                

* Necessary Technologies:
    - [Jenkins](https://www.jenkins.io/)
    - [Jfrog](https://jfrog.com/) - (OBS: you can use the script in this repository)
    - [Packer](https://www.packer.io/)
    - [Ansible](https://www.ansible.com/)
    - [Docker](https://www.docker.com/)

# Step by step to deploy this application
* Repository source: [Calculator-app](https://github.com/henrik-olvr/Calculator-Java.git)
* Pipeline demonstration video:

## Job 1 - Build App
1. At `Jenkins Dashboard`, go to `Manage Jenkins > Manage Plugins > Available`.
    1. In the search field type `Artifactory Plugin` and install it.
2. Back to dashboard, click `New Item` to create the first job.
    1. Enter the name `job1` for it.
    2. Select `Pipeline` option just bellow and click `OK`.
    3. Click on `Pipeline` in options above, in `Definition` select `Pipeline Script from SCM`.
    4. In `SCM` field, change to `Git` and add the following repository URL `https://github.com/henrik-olvr/Calculator-Java.git`.
    5. Scroll down to `Script Path`, type `job1/Jenkinsfile`, then click `Apply` and `Save`.
3. Setting Jfrog Artifactory.
    1. After run the script of Jfrog from this repository, you can access the following URL `http://localhost:8082/ui/login/`.
    2. Make the initial settings, then select the field `Quick Setup`, click `Gradle`, type the name `art1` and click `Create`.
4. Go back to `Manage Jenkins` and click on `Configure System`, scroll
   down to `JFrog` section and click on `Add Jfrog Platform Instance`.
    1. On `Instance ID`, type: `jfrog-artifactory-server`.
    2. On `JFrog Platform URL`, type: `http://localhost:8082/artifactory`.
    3. On `Default Deployer Credentials`, enter your Jfrog username and password in the respective fields.
    4. To finish, click `Apply` and `Save`.
5. Now you're able to build this job, so at `Jenkins Dashboard` select your `job1` and click `Build Now`.


## Job 2 - Provision
**_`NOTE #1:` To this job you will need Docker and Packer, so you can install both via the links in "Necessary Technologies" section._**
**_`NOTE #2:` After Docker installation, you'll need to open a terminal and type the command `sudo chmod 666 /var/run/docker.sock` in order
to give it permission to run without a `sudo` command._**
**_`NOTE #3:` Also you will need a [Dockerhub](https://hub.docker.com/) account and generate an access token._**
1. In your Dockerhub account, go to `Account settings > Security > New Access Token`
    1. Give the token a description `jenkins`
    2. In `Access Permissions`, select `Read, Write, Delete`.
    3. To finish, click `Generate`.
2. Now you need to provide Dockerhub credentials and repository information to Jenkins.
    1. Go to Jenkins dashboard and click `Manage Jenkins > Manage Credentials > (global) > Add Credentials`.
    2. Create a `Username with password` credential with your Dockerhub login information.
        1. In the `Scope` field, select `Global (Jenkins, nodes, items, all child items, etc)`.
        2. In the `Username` field, enter your `Dockerhub username`.
        3. In the `Password` field, enter your `Dockerhub Token`.
        4. In the `ID` field, type: `dockerhub-accces`.
        5. In the `Description` field, type: `Dockerhub Credentials`.
3. To finish, go to `Jenkins Dashboard` and click `New Item` to create the job 2.
    1. Enter the name `job2` for it.
    2. Select `Pipeline` option just bellow and click `OK`.
    3. Click on `Pipeline` in options above, in `Definition` select `Pipeline Script from SCM`.
    4. In `SCM` field, change to `Git` and add the following repository URL `https://github.com/henrik-olvr/Calculator-Java.git`.
    5. Scroll down to `Script Path`, type `job2/Jenkinsfile`, then click `Apply` and `Save`.
4. Now you're able to build this job, so at `Jenkins Dashboard` select your `job2` and click `Build Now`.

## Job 3 - Run
1. Go to `Jenkins Dashboard` and click `New Item` to create the job 3.
    1. Enter the name `job3` for it.
    2. Select `Pipeline` option just bellow and click `OK`.
    3. Click on `Pipeline` in options above, in `Definition` select `Pipeline Script from SCM`.
    4. In `SCM` field, change to `Git` and add the following repository URL `https://github.com/henrik-olvr/Calculator-Java.git`.
    5. Scroll down to `Script Path`, type `job3/Jenkinsfile`, then click `Apply` and `Save`.
2. Now you're able to build this job, so at `Jenkins Dashboard` select your `job3` and click `Build Now`.

# Using the Calculator Application

* In the browser's URL bar, type: `https://localhost:8083/calculator-app/calculator`.
* To perform an operation type in the browser's URL bar: `https://localhost:8083/calculator-app/calculator?val1=[value to calculate]&val2=[value to calculate]&operation=[desired operation (uppercase)]`.
* Here is one example: `https://localhost:8083/calculator-app/calculator?val1=5&val2=3&operation=MULT`.
* Operations supported by the calculator: `SUM(+), SUB(-), DIV(/), MULT(*) and POW(^)`.
* You can do as many operations as you want and then check the history of operations performed.
* To check the history, in the browser's URL bar, type `https://localhost:8083/calculator-app/calculator?showhistory`.

**_That's all! I hope you enjoy my Calculator Application. See ya!_**