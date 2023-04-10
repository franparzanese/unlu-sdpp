# 01 - Basic Mvn Java APP

The repository contains a simple Java application which outputs the string
"Hello world!" 

# Topics covered
. GIT -> https/ssh -> ssh cloning repositories.
. MANAGERS 
.. mvn version manager.
.. java version manager.
.. node version manager.

. pom.xml restrictions.
. mvn configurations.
. jar.
. docker.

# Configure SSH key 

Debemos evaluar si está corriendo el servidor de ssh en nuestro equipo:
eval $(ssh-agent -s)

Create ssh key with the following command
ssh-keygen -f ~/.ssh/id_rsa_personal -t ecdsa -N '' -C "-mail-@gmail.com"
ssh-add ~/.ssh/id_rsa_personal

Add to configfile (.ssh/config) the hostname and git service host 

```sh
Host github.com-personal
        User git
        Hostname github.com
        IdentityFile /home/david/.ssh/id_rsa_personal
```
