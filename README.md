# springboot-pod-name
Demo app for Kubernetes to get and show a pod-name

## 1. Build an image
```
docker build -t ebd622/pod-name .
```

winpty docker exec -it a7497c710ba7 bash

## 2. Tag the created image
```
docker tag <IMAGE_ID> ebd622/pod-name:<TAG>
```

--- check image:
docker run --rm -p 8090:8090 ebd622/pod-name

docker push ebd622/pod-name:0.0.1



curl localhost:8090/pod-name


--- Create deployment
kubectl run p-name --image=ebd622/pod-name:0.0.2 -o yaml --dry-run > p.yaml

```
apiVersion: apps/v1
kind: Deployment
metadata:
  creationTimestamp: null
  labels:
    run: p-name
  name: p-name
spec:
  replicas: 3
  selector:
    matchLabels:
      run: p-name
  strategy: {}
  template:
    metadata:
      creationTimestamp: null
      labels:
        run: p-name
    spec:
      containers:
      - args:
        - mvn
        - clean
        - spring-boot:run
        image: ebd622/pod-name:0.0.2
        name: p-name
        resources: {}
status: {}
```

--- expose deployment
kubectl expose deployment p-name --port=80 --target-port=8090 --type=NodePort
