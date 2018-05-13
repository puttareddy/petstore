.PHONY: init helm-reset mount deploy delete deleteAll
REPO=petstore-api
DEVOPS-REPO=devops-repo
TIMESTAMP=tmp-$(shell date +%s )

init: 
	# init and start only once
	minikube start --vm-driver="hyperv" --hyperv-virtual-switch="Primary Virtual Switch" -v=9
	minikube addons enable ingress
	minikube addons enable registry-creds
	helm init

helm-reset:
	kubectl delete deployment tiller-deploy --namespace kube-system
	helm init --upgrade --service-account default

deploy:
	# minikube step
	eval $(minikube docker-env);
	mvn clean package
	# build image
	docker build -t $(REPO):$(TIMESTAMP) .
	# deploy to kubernetes (minikube)
	helm init --client-only
	helm upgrade --install --debug --namespace default -f deploy/values.yaml --set imageName=$(REPO):$(TIMESTAMP) --set repoName=$(REPO) $(REPO) ../$(DEVOPS-REPO)/helm-chart
	# setup ingress
	#kubectl delete -f deploy/petstore-ingress.yaml
	#kubectl create -f deploy/petstore-ingress.yaml
		
delete:
	helm delete $(REPO) --purge
	#kubectl delete -f deploy/petstore-ingress.yaml
	#kubectl delete -f kubernetes-deployment.yaml

deleteAll:
	# delete all Releases 
	helm ls --short | xargs -L1 helm delete

mount:
	# add sudo, if not running on admin mode
	minikube mount ${PWD}:/var/log/
