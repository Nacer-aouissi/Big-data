# Docker

in this work we will learn how to use docker

## Steps :

-[creating_dockerhub_account] -[image_creation] -[push_to_dockerhub] -[creating_network] -[launching] -[checking_network]

### creating dockerhub account :

- in https://hub.docker.com/ create a free account

### Image creation :

- in project folder preparing the files and add dockerfile contains :

```
# Base image with Jupyter and Python
FROM jupyter/base-notebook

# Install required Python packages
RUN pip install --no-cache-dir \
    pandas \
    dask[dataframe] \
    matplotlib \
    seaborn \
    scikit-learn

# Copy all TP03 files into the container
COPY . /home/jovyan/work

# Set working directory
WORKDIR /home/jovyan/work

# Expose the Jupyter Notebook port
EXPOSE 8888
```

after that in cmd run the commnd :
<docker build -t username/img_name .>

### Pushing to dockerhub:

- first login to dockerhub using : docker login
- second push using command : docker push username/img_name

### Create the Docker Network

use command : docker network create network_name

### Launching the containers :

- to launch the 3 containers use command :
  - pull the image from docker hub using : docker pull yourdockerhubusername/tp03image
  - use the command :
    docker run -d --name container1 --network network_name -p 8881:8888 usename/img_name
    docker run -d --name container2 --network network_name -p 8881:8888 usename/img_name
    docker run -d --name container3 --network network_name -p 8881:8888 usename/img_name

### Checking network :

to check if the network between the 3 containers working we need to ping the containers from one another using command :

- docker exec -u 0 -it container1 bash
- ping container2
