let res = [
    db.createUser(
        {
            user: "skill",
            pwd: "skill",
            roles: [{role: "readWrite", db: "skills"}],
        }
    ),

    db.persons.drop(),
    db.skills.drop(),

    skills_id = ObjectId(),
    db.skills.insert({
        _id: skills_id,
        langs: ['Java 6', 'Java 7', 'Java 8'],
        techs: ['Java EE 6', 'EJB 3.0'],
        servers: ['Apache Tomcat', 'IBM WebSphere Application Server 8.5'],
        dbs: ['Postgres', 'Oracle 11', 'Mongodb', 'MySQL'],
        systems: ['RHEL', 'CentOS', 'Ubuntu'],
        others: ['JUnit', 'JDBC'],
    }),

    db.persons.insert({
        name: 'Dzmitry Barysevich',
        email: 'zmicierb@tut.by',
        position: 'Java Developer',
        department: 'Application Development department',
        birthDate: new Date('1987/06/20'),
        skillsId: skills_id,
    }),
]

printjson(res)