## Note
> - The code structure in this repository is intentionally designed to reflect **clean coding practices and standard architectural principles**.
> - While the actual requirement could have been implemented with a much simpler approach, this project goes beyond that.
> - It aims to serve as a benchmark for `maintainable`, `scalable`, and `well-structured` code.
> - The focus is on demonstrating an **ideal way of organizing logic**, not just solving the problem minimally.

## 🔗 Exposed Endpoints

| HTTP Method | Endpoint                   | Description                            |
|-------------|----------------------------|----------------------------------------|
| `GET`       | `/actuator`                | Lists all available actuator endpoints |
| `GET`       | `/actuator/health`         | Health status of the application       |
| `POST`      | `/api/v1/employee/add`     | Add employee                           |
| `GET`       | `/api/v1/employee/all`     | Get all employees                      |
| `GET`       | `/api/v1/employee/id/{id}` | Get employee by id                     |
| `DELETE`    | `/api/v1/employee/id/{id}` | Delete employee by id                  |
| `DELETE`    | `/api/v1/employee/all`     | Delete all employees                   |
