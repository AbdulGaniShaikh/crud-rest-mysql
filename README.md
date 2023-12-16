# spring-boot crud rest with mysql as database
Spring boot CRUD (Create, Read, Update, Delete) demo application 
with MySQL DB.

`Make sure java version in your machine is 21.`

## Technologies used
* Java
* Spring Boot 3.2.0
* MySQL
* Hibernate
* Spring Validation
* Lombok
* Maven
* Mockito
* JUnit5
* REST APIs

## How to compile and run application
Go to src->main->resources->application.yaml file and change the source link of mysql according to you.
make sure to run mysql server before running the application.

    git clone https://github.com/AbdulGaniShaikh/crud-rest-mysql.git
    mvn package
    mvn spring-boot:run

-------------------
## Rest Api

### CREATE API
<details>
<summary>
<code>POST</code>
<code>/v1/api/student</code>
<code>save a new student</code>
</summary>


##### Parameters

| name         | value            | in     |
|--------------|------------------|--------|
| Content-Type | application/json | header |


##### Request Body

    {
       "name":"abdul gani",
       "year":"FE",
       "email":"shkhabdulganu@gmail.com",
       "age":19
    }

##### Responses

>    <details>
>    <summary>
>    <code>201 CREATED</code>
>    <code>application/json</code>
>    </summary>
>    
>     {
>        "name":"abdul gani",
>        "year":"FE",
>        "email":"shkhabdulganu@gmail.com",
>        "age":19
>     }
>    </details>
>    <details>
>    <summary>
>    <code>400 BAD REQUEST</code>
>    <code>application/json</code>
>    </summary>
>    
>     {
>         "year": "year cannot be null",
>         "name": "name cannot be empty",
>         "age": "age cannot be less than 10",
>         "email": "email cannot be empty"
>     }
>    </details>
</details>

---


### Read API
<details>
<summary>
<code>GET</code>
<code>/v1/api/students/{studentId}</code>
<code>get a student by id</code>
</summary>

#### Responses

>    <details>
>    <summary>
>    <code>200 OK</code>
>    <code>application/json</code>
>    </summary>
>    
>           {
>                "studentId":"1",
>                "name":"abdul gani",
>                "year":"FE",
>                "email":"shkhabdulganu@gmail.com",
>                "age":19
>           }
>    </details>
>    <details>
>    <summary>
>    <code>404 NOT FOUND</code>
>    <code>application/json</code>
>    </summary>
>    
>        {
>            "statusCode": 404,
>            "type": "404 NOT_FOUND",
>            "date": "2023-12-15T19:18:24.224+00:00",
>            "message": "student not found by id 1"
>        }
>    </details>
</details>

<details>
<summary>
<code>GET</code>
<code>/v1/api/students</code>
<code>get all students store in db</code>
</summary>


##### Parameters

| name       | default-value | in    |
|------------|---------------|-------|
| pageSize   | 10            | query |
| pageNumber | 0             | query |
| sortBy     | NONE          | query |
| dir        | ASC           | query |

#### Responses

>    <details>
>    <summary>
>    <code>200 OK</code>
>    <code>application/json</code>
>    </summary>
>    
>     [
>         {
>             "studentId": "63b99e44-20f7-4230-bef3-2958794c066f",
>             "name": "abdul gani",
>             "year": "FE",
>             "email": "abdulgani@gmail.com",
>             "age": 19
>         },
>         {
>             "studentId": "6754fd0c-aff1-4254-b02c-c9beda16dfe2",
>             "name": "abdul",
>             "year": "FE",
>             "email": "adasd@gmail.com",
>             "age": 19
>         }
>     ]
>    </details>
</details>

---


### Update API
<details>
<summary>
<code>PUT</code>
<code>/v1/api/students/{studentId}</code>
<code>update a student by id</code>
</summary>

##### Parameters

| name         | value            | in     |
|--------------|------------------|--------|
| Content-Type | application/json | header |


##### Request Body

    {
        "name":"abdul gani",
        "year":"FE",
        "email":"shkhabdulganu@gmail.com",
        "age":19
    }

#### Responses

>    <details>
>    <summary>
>    <code>200 OK</code>
>    <code>application/json</code>
>   </summary>
>
>     {
>          "studentId":"1",
>          "name":"abdul gani",
>          "year":"FE",
>          "email":"shkhabdulganu@gmail.com",
>          "age":19
>     }
>    </details>
>    <details>
>    <summary>
>    <code>404 NOT FOUND</code>
>    <code>application/json</code>
>    </summary>
>
>     {
>         "statusCode": 404,
>         "type": "404 NOT_FOUND",
>         "date": "2023-12-15T19:18:24.224+00:00",
>         "message": "student not found by id 1"
>     }
>    </details>
>    <details>
>    <summary>
>    <code>400 BAD REQUEST</code>
>    <code>application/json</code>
>    </summary>
>
>     {
>         "year": "year cannot be null",
>         "name": "name cannot be empty",
>         "age": "age cannot be less than 10",
>         "email": "email cannot be empty"
>     }
>    </details>
>    </details>

---

### Delete API
<details>
<summary>
<code>DELETE</code>
<code>/v1/api/students/{studentId}</code>
<code>delete a student by id</code>
</summary>

#### Responses

>    <details>
>    <summary>
>    <code>200 OK</code>
>    <code>text/plain;charset=UTF-8</code>
>    </summary>
>
>     deleted student by id 63b99e44-20f7-4230-bef3-2958794c066f
>    </details>
>    <details>
>    <summary>
>    <code>404 NOT FOUND</code>
>    <code>application/json</code>
>    </summary>
>
>     {
>         "statusCode": 404,
>         "type": "404 NOT_FOUND",
>         "date": "2023-12-15T19:18:24.224+00:00",
>         "message": "student not found by id 1"
>     }
>    </details>
</details>

<details>
<summary>
<code>DELETE</code>
<code>/v1/api/students</code>
<code>delete all students store in db</code>
</summary>

#### Responses

>    <details>
>    <summary>
>    <code>200 OK</code>
>    <code>text/plain;charset=UTF-8</code>
>    </summary>
>
>     deleted all entries in database
>    </details>
>    </details>

---
