FROM clojure:tools-deps AS build

RUN apt-get update && apt-get install -y curl \
  npm \
  nodejs 


RUN node --version
RUN npm --version

WORKDIR /usr/src/app
COPY . /usr/src/app

# RUN mkdir /usr/local/nvm
# ENV NVM_DIR /usr/local/nvm
# ENV NODE_VERSION 14.18.1
# RUN curl https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash \
#   && . $NVM_DIR/nvm.sh \
#   && nvm install $NODE_VERSION \
#   && nvm alias default $NODE_VERSION \
#   && nvm use default

RUN npm i
RUN npx shadow-cljs release app
RUN npx tailwind -i global.css -o resources/public/assets/css/output.css --minify

RUN clj -T:build uber


FROM alpine/java:21-jre
COPY --from=build /usr/src/app/target/app-standalone.jar .
CMD ["java", "-jar", "app-standalone.jar"]


