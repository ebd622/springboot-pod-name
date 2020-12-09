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

## 3. Check the image
```
docker run --rm -p 8090:8090 ebd622/pod-name
```
(If you use `-d` with `--rm`, the container is removed when it exits or when the daemon exits, whichever happens first)

## 4. Push the image

```
docker push ebd622/pod-name:<TAG>
```

## 5. Test URL
```
curl localhost:8090/pod-name
```

## 6. Create deployment

Run the following command to generate a yaml-file:
```
kubectl run p-name --image=ebd622/pod-name:0.0.2 -o yaml --dry-run > p.yaml
```
When yaml-file is generated you need to modify it.
Some args must be provided in the section `spec` for `containers`:

```
spec:
  containers:
  - args:
    - mvn
    - clean
    - spring-boot:run
```

After appending args,  your modified `p.yaml` should look like this:

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

## 7. Expose deployment

Then run the command to expose the deployment:

```
kubectl expose deployment p-name --port=80 --target-port=8090 --type=NodePort
```
