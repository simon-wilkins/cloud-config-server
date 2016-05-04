provider "aws" {
    access_key = ""
    secret_key = ""
    region = "eu-west-1"
}

resource "aws_instance" "config-service" {
    ami = "ami-3f34bd4c"
    instance_type = "t2.micro"
    key_name ="xxxx"
    
    provisioner "remote-exec" {
        inline = [
          "cd /tmp",
          "nohup java -jar configuration-service-0.0.1-SNAPSHOT.jar &",
          "sleep 1"
        ]
        connection {
          user = "ec2-user"
          private_key = "${file("C:\\Users\\user\\.ssh\\xxxx.pem")}"
        }
    }
}

resource "aws_eip" "ip" {
    instance = "${aws_instance.config-service.id}"
    depends_on = ["aws_instance.config-service"]
}