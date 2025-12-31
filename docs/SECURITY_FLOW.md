sequenceDiagram
participant Client
participant AuthController
participant LoginService
participant TokenUtil

    Client->>AuthController: POST /login
    AuthController->>LoginService: authenticate()
    LoginService->>TokenUtil: generate JWT
    AuthController-->>Client: JWT Token

    Client->>API: Request with Authorization Bearer
    API->>TokenUtil: validate JWT
    TokenUtil-->>API: Authentication OK
