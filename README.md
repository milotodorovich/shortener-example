# Shortener (url shortener app)

## Development

`npx shadow-cljs watch app`

Allows you to:
- run the front-end
- repl the back-end
- start the backend using `shortner.core/start`
- interact with the application at `https://localhost:3001/`

`npx tailwind -i global.css -o resources/public/assets/css/output.css --watch`

Allows you to:
- modify the css and have it build real-time.

`docker compose up app`

Allows you to build and run in a local docker container

`clj -T:build uber`

Allows you to build the uberjar to `target/app-standalone.jar`

`git push heroku`

Allows you to deploy the app to heroku, where it is built and automatically deployed# Shortener (url shortener app)

## Development

`npx shadow-cljs watch app`

Allows you to:
- run the front-end
- repl the back-end
- start the backend using `shortner.core/start`
- interact with the application at `https://localhost:3001/`

`npx tailwind -i global.css -o resources/public/assets/css/output.css --watch`

Allows you to:
- modify the css and have it build real-time.

`docker compose up app`

Allows you to build and run in a local docker container

`clj -T:build uber`

Allows you to build the uberjar to `target/app-standalone.jar`

`git push heroku`

Allows you to deploy the app to heroku, where it is built and automatically deployed.
