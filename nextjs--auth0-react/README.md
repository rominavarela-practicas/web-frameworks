# Nextjs - Basic App with Auth0

# About this project

This project presents a minimal structure to showcase the integration with Auth0, following along with https://github.com/auth0/auth0-react/tree/master/examples/nextjs-app

| Technology Choise |  |
| ----- | -----|
| Style | Next.js built-in support for SASS and SCSS. |
| [@auth0/auth0-react](https://yarnpkg.com/package/@auth0/auth0-react) | Auth0 SDK for React Single Page Applications (SPA) |

#### Requirements

* Nodejs (version declared in `.nvmrc` file)

* Yarn
  
* Auth0 "Single Page Application"

  <img width="500" alt="Screen Shot 2023-07-14 at 6 15 53 p m" src="https://github.com/rominavarela-practicas/frontend-frameworks/assets/7092275/5f811336-f908-4c48-95d8-af409026914b">

  Settings:

  ```
  Allowed Callback URLs = http://localhost:3000
  Allowed Logout URLs = http://localhost:3000
  Allowed Web Origins = http://localhost:3000
  Permissions:
    ROLE_ADMIN - Admin Role Scoped Permission
  ```

#### Instructions

1. Install the project dependencies. Use the command `yarn install`

2. Start the development server. Use the command `yarn dev`

Open `http://localhost:3000/` in your browser. That's it!

Watch the demo:

![JqlzkMpCOE](https://github.com/rominavarela-practicas/frontend-frameworks/assets/7092275/6452719d-9028-48a4-9b19-21f545e9073a)

![MV42wv1NVY](https://github.com/rominavarela-practicas/frontend-frameworks/assets/7092275/e18b840f-9c6b-4856-85f8-d3404a47aac2)

