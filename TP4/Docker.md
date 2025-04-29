# Docker Project Setup Guide

## Overview

This guide walks through setting up a Docker environment for data analysis, including creating a Docker Hub account, building images, and managing containers.

## Prerequisites

- Docker installed and running
- Basic understanding of Docker commands
- Internet connection for Docker Hub access

## Step 1: Docker Hub Account Setup

1. Visit [Docker Hub](https://hub.docker.com/)
2. Create a free account
3. Verify your email address

## Step 2: Image Creation

### Project Structure

Prepare your project folder with the following files:

- Dockerfile
- Required Python scripts
- Data files

### Dockerfile Configuration

```dockerfile
# Base image with Jupyter and Python
FROM jupyter/base-notebook

# Install required Python packages
RUN pip install --no-cache-dir \
    pandas \
    daskdataframe \
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

### Building the Image

```bash
docker build -t username/image_name .
```

## Step 3: Pushing to Docker Hub

### Login to Docker Hub

```bash
docker login
```

### Push the Image

```bash
docker push username/image_name
```

## Step 4: Network Setup

### Create Docker Network

```bash
docker network create network_name
```

## Step 5: Container Management

### Launch Containers

1. Pull the image:

```bash
docker pull yourdockerhubusername/tp03image
```

2. Run containers:

```bash
# Container 1
docker run -d --name container1 --network network_name -p 8881:8888 username/image_name

# Container 2
docker run -d --name container2 --network network_name -p 8882:8888 username/image_name

# Container 3
docker run -d --name container3 --network network_name -p 8883:8888 username/image_name
```

## Step 6: Network Verification

### Test Container Connectivity

1. Access container shell:

```bash
docker exec -u 0 -it container1 bash
```

2. Test connectivity:

```bash
ping container2
ping container3
```

## Troubleshooting

- If containers can't communicate, check network configuration
- Verify port mappings are correct
- Ensure containers are running: `docker ps`
- Check container logs: `docker logs container_name`

## Additional Resources

- [Docker Documentation](https://docs.docker.com/)
- [Docker Hub](https://hub.docker.com/)
- [Docker Network Documentation](https://docs.docker.com/network/)
