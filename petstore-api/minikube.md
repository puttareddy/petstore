# Deploy Petstore-api to Minikube cluster

Follow below steps to deploy this service to Minikube cluster. 

## Start Minikube

> Recommended to run these commands in Administrator mode

Issue below command to start the Minikube cluster:


if you are NOT using Windows 10
```
# minikube start
```
OR
If you use Windows 10 with Hyper-v. 
```
$ minikube start --vm-driver="hyperv" --hyperv-virtual-switch="Primary Virtual Switch" -v=9
```
Refer confluence page, if you didn't setup the Hyper-V switch in Windows 10.


## Install helm
> Please make sure, you install helm in your local machine and run below command to configure helm, before you start any further process. 

```
$ helm init
$ minikube addons enable registry-creds
```

> Also, Clone the dependent DevOps (master branch) project and keep it in the same project location. This is required as all applied/common **Helm templates** are available in DevOps project. 

```
$ git clone https://github.com/puttareddy/petstore.git
```

## Mount a folder
Execute below command to mount a local Host path folder to Minikube VM, which can be used after POD is created.

```
$ make mount
```
> Please make sure, you mount the host path in a separate cmd prompt

## Deploy the service

> It is always recommend to build the JAR file before deploying into the Minikube cluster, as the current Dockerfile doesn't create the JAR file and uses the existing(already) built JAR file.
```
$ mvn clean package
```

Run below command to build the image from scratch locally and deploy into Cluster

```
$ make create
```

## Delete the service 

Run below command to delete the deployed service from the Cluster

```
$ make delete
```

## Tests

You should be able to get the Loans service URL with IP address by using below command for validation

```
$ minikube service petstore-api-service --url
```
Test the service with the assigned URL