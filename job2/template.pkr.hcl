variable "tag" {
    type = string
    default = "latest"
}

variable "dockerhub_username" {
    type = string 
    default = ""
}

variable "dockerhub_password" {
    type = string 
    default = ""
}

source "docker" "calculator-app" {
    image = "williamyeh/ansible:ubuntu18.04"
    commit = true
        changes = [
            "ENTRYPOINT java -jar /home/app/calculator-app-${var.tag}.war"
        ]
}

build {
    sources = ["source.docker.calculator-app"]

    provisioner "ansible-local" {
        playbook_file = "./job2/playbook.yaml"
    }
    provisioner "shell" {
        inline = ["mkdir /home/app"] 
    }
    provisioner "file" {
        source = "./calculator-app-${var.tag}.war"
        destination = "/home/app/calculator-app-${var.tag}.war"
    }

    post-processors {
        post-processor "docker-tag" {
            repository = "${var.dockerhub_username}/calculator-app"
            tags = ["${var.tag}"]
        }
        post-processor "docker-push" {
            login = true
            login_username = "${var.dockerhub_username}"
            login_password = "${var.dockerhub_password}"
        }
    }
    
}
