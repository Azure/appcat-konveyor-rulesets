# 1. Lowercase instructions
from ubuntu:20.04
run echo "This is lowercase instruction"

# 2. Consecutive RUN instructions
RUN apt-get update
RUN apt-get install -y curl
RUN rm -rf /var/lib/apt/lists/*

RUN apt-get update && apt-get upgrade -y

# 4. Space before '=' in ENV, LABEL, ARG
ENV MY_VAR =value
LABEL version = "1.0"
ARG BUILD_ARG = 123