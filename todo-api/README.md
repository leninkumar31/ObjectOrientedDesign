# TODO API

## Register end point
- Method : POST
- Path : \register
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
- Path : \login
- Payload (Required fields)
  - Username
  - Password
- Headers
  - Authorization
- Response
  - 201(Created)
    - Response Header(AUTHORIZATION) will have JWT token
  - 400 (Bad Request) if required fields doesn’t present or fail to satisfy constraints
  - 401(Unauthorised) if username or password is invalid


## Retrieve all Todos of logged user
- Method : GET
- Path : \
- Query params
  - page
  - size
- Headers
  - Authorization
- Response
  - 200(OK)
    - Paginated List of Todos
  - 401(Unauthorised) If JWT Token is not valid
  - 400 (Bad Request) if required fields doesn’t present or fail to satisfy constraints


## Create a new Todo
- Method: POST
- Path : \insert
- Payload (required fields)
  - Task
  - isCompleted
- Headers
  - Authorization
- Response
  - 201 (Created)for successful creation
  - 400 (Bad Request) if required fields doesn’t present or fail to satisfy constraints
  - 401(Unauthorised)If JWT Token is not valid


## Update an existing Todo
- Method: PUT
- Path : \update\\{id}
- Path param : todo id
- Payload (required fields)
  - Task
  - isCompleted
- Response
  - 204 (no content)for successful update
  - 400 (Bad Request) if required fields doesn’t present or fail to satisfy constraints
  - 403 (Forbidden) if given id doesn’t belong to this person
  - 401(Unauthorised) If JWT Token is not valid


## Delete an existing Todo
- Method: DELETE
- Path : \delete\\{id}
- Path param : todo-id
- Response
  - 204 (no content) for successful deletion
  - 403 (Forbidden) if given id doesn’t belong to this person
  - 401(Unauthorised) If JWT Token is not valid
