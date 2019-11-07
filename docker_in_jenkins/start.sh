docker build -t yourusername/jenkins .
docker run -d \
           --name myjenkins \
           -p 8080:8080 -p 50000:50000 \
           -v /var/run/docker.sock:/var/run/docker.sock \
           -v jenkins_home:/var/jenkins_home \
            yourusername/jenkins