cd <path_to_app>
docker build -t events-recorder .
docker run –d –p 8080:8080 --name events-recorder
