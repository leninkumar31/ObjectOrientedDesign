# TODO API

## Register end point
- Method : POST
- Path : “\register”
- Payload (Required fields)
  - Username
  - First name
  - Lastname
  - Email
  - Password
- Response
  - 201(Created)
    - Response Header(AUTHORIZATION) will have JWT token
    - 409 (Conflict)  Username already exists
    - 400 (Bad Request) if required fields doesn’t present or fail to satisfy constraints
## Login end point
- Method : POST
- Path : “\login”
- Payload (Required fields)
        1. Username
        2. Password
- Headers
        1. Authorization
- Response
        1. 201(Created)
            1. Response Header(AUTHORIZATION) will have JWT token
        2. 400 (Bad Request) if required fields doesn’t present or fail to satisfy constraints
        3. 401(Unauthorised) if username or password is invalid
## Retrieve all Todos of logged user
- Method : GET
- Path : “\”
- Query params
        1. page
        2. Size
- Headers
        1. Authorization
- Response
        1. 200(OK)
            1. Paginated List of Todos
        2. 401(Unauthorised)
            1. If JWT Token is not valid
        3. 400 (Bad Request) if required fields doesn’t present or fail to satisfy constraints
## Create a new Todo
- Method: POST
- Path : “\insert”
- Payload (required fields)
        1. Task
        2. isCompleted
- Headers
        1. Authorization
- Response
        1. 201 (Created)for successful creation
        2. 400 (Bad Request) if required fields doesn’t present or fail to satisfy constraints
        3. 401(Unauthorised)
            1. If JWT Token is not valid
## Update an existing Todo
- Method: PUT
- Path : “\update\{id}”
- Path param : todo-id
- Payload (required fields)
        1. Task
        2. isCompleted
- Response
        1. 204 (no content)for successful update
        2. 400 (Bad Request) if required fields doesn’t present or fail to satisfy constraints
        3. 403 (Forbidden) if given id doesn’t belong to this person
        4. 401(Unauthorised)
            1. If JWT Token is not valid
## Delete an existing Todo
- Method: DELETE
- Path : "\delete\{id}"
- Path param : todo-id
- Response
        1. 204 (no content)for successful deletion
        2. 403 (Forbidden) if given id doesn’t belong to this person
        3. 401(Unauthorised)
            1. If JWT Token is not valid
