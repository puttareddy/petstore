#FROM risingstack/alpine:3.4-v7.9.0-4.4.0
FROM node:8.1.2-slim
USER root

# Create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

# Install app dependencies
COPY package.json /usr/src/app/
ADD node_modules /usr/src/app/node_modules
RUN npm install

# Bundle app source
COPY . /usr/src/app

EXPOSE 8080

CMD npm run start
