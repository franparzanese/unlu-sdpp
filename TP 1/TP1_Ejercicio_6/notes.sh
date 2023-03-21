# Detected Maven Version: 3.6.3 is not in the allowed range [3.8.6,).
# mvn manager -> https://mvnvm.org/
# mkdir -p bin && curl -s https://bitbucket.org/mjensen/mvnvm/raw/master/mvn > bin/mvn && chmod 0755 bin/mvn

# java manager
# SDKMAN! -> https://sdkman.io/
# curl -s "https://get.sdkman.io" | bash
# source "/home/david/.sdkman/bin/sdkman-init.sh"
# sdk list java
# sdk install java 11.0.12-open

# Node manager
# curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.3/install.sh | bash
# export NVM_DIR="$([ -z "${XDG_CONFIG_HOME-}" ] && printf %s "${HOME}/.nvm" || printf %s "${XDG_CONFIG_HOME}/nvm")"\n[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh" 

# locally installed 
# nvm ls
#   nvm ls-remote
# nvm install v18.15.0 
#  nvm use [VERSION]