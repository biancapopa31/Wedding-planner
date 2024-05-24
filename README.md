## Descrierea proiectului
pass

## Clase - orientativ
- Person
    - `String` First Name
    - `String` Last Name
    - `String` Telephone number 

- Guest - extends Person
    - `Boolean` RSVP
    - `String` Side (Groom or bride)
    - `String` Role (Optional domnisor/Domisoara de onoare)
    - `String` Relationship

- Vendor - extends Person
    - `String` E-mail
    - `Double` Price
    - `String` serviceType
    - `String` - Notes

- Task
    - `String` Name
    - `String` Description
    - `LocalDate` dueDate

- CheckList
    - `String` Name
    - `String` Description
    - `Task []` Tasks


- Table
    - `Int` tableNumber
    - `Int` capacity
    - `Person []` Members

- Wedding
    - `LocalDate` Date
    - `String` Location

    - `Person` Bride
    - `Person` Groom
    - `Person` godmother
    - `Person` Godfather
    - ...
    - `Table []` Tabels
    - `Guest []` Guests
    - `Vendor []` Vendors
    - `Task []` Tasks
    - `CheckList []` Checklists