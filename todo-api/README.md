## Register end point
- Method : POST
- Path : “\register”
- Payload (Required fields)
        1. Username
        2. First name
        3. Lastname
        4. Email
        5. Password
    4. Response
        1. 201(Created)
            1. Response Header(AUTHORIZATION) will have JWT token
        2. 409 (Conflict)  Username already exists
        3. 
2. Login end point
    1. Method : POST
    2. Path : “\login”
    3. Payload (Required fields)
        1. Username
        2. Password
    4. Headers
        1. Authorization
    5. Response
        1. 201(Created)
            1. Response Header(AUTHORIZATION) will have JWT token
        2. 400 (Bad Request) if required fields doesn’t present or fail to satisfy constraints
        3. 401(Unauthorised) if username or password is invalid
3. Retrieve all Todos of logged user
    1. Method : GET
    2. Path : “\”
    3. Query params
        1. page
        2. Size
    4. Headers
        1. Authorization
    5. Response
        1. 200(OK)
            1. Paginated List of Todos
        2. 401(Unauthorised)
            1. If JWT Token is not valid
        3. 400 (Bad Request) if required fields doesn’t present or fail to satisfy constraints
4. Create a new Todo
    1. Method: POST
    2. Path : “\insert”
    3. Payload (required fields)
        1. Task
        2. isCompleted
    4. Headers
        1. Authorization
    5. Response
        1. 201 (Created)for successful creation
        2. 400 (Bad Request) if required fields doesn’t present or fail to satisfy constraints
        3. 401(Unauthorised)
            1. If JWT Token is not valid
5. Update an existing Todo
    1. Method: PUT
    2. Path : “\update\{id}”
    3. Path param : todo-id
    4. Payload (required fields)
        1. Task
        2. isCompleted
    5. Response
        1. 204 (no content)for successful update
        2. 400 (Bad Request) if required fields doesn’t present or fail to satisfy constraints
        3. 403 (Forbidden) if given id doesn’t belong to this person
        4. 401(Unauthorised)
            1. If JWT Token is not valid
6. Delete an existing Todo
    1. Method: DELETE
    2. Path : “\delete\{id}”
    3. Path param : todo-id
    4. Response
        1. 204 (no content)for successful deletion
        2. 403 (Forbidden) if given id doesn’t belong to this person
        3. 401(Unauthorised)
            1. If JWT Token is not valid
