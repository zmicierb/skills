let res = [
    rs.initiate({_id: 'replocal', members: [{_id: 0, host: "192.168.0.101:27017"}] }),

    db.createUser(
        {
            user: "skill",
            pwd: "skill",
            roles: [{role: "readWrite", db: "skills"}],
        }
    ),

    db.persons.drop(),
    db.skills.drop(),
    db.companies.drop(),
    db.skill.drop(),

    db.skill.createIndex({ name: 1 }, { unique: true }),
    db.skills.createIndex({ personId: 1 }, { unique: true }),
    db.companies.createIndex({ personId: 1 }),

    langs = ['Java 6', 'Java 7', 'Java 8'],
    techs = ['Java EE 6', 'EJB 3.0', 'Liferay Enterprise'],
    servers = ['Apache Tomcat', 'IBM WebSphere Application Server 8.5', 'IBM WebSphere Portal Server 8.5'],
    dbs = ['PostgreSQL', 'Oracle 11', 'Mongodb', 'MySQL'],
    systems = ['RHEL', 'CentOS', 'Ubuntu'],
    others = ['JUnit', 'JDBC'],

    env1 =['Java EE 6', 'Liferay Enterprise', 'PostgreSQL'],
    env2 = ['Java EE 6', 'IBM WebSphere Application Server 8.5', 'IBM WebSphere Portal Server 8.5'],
    env3 = ['Java 8', 'Spring 4.x', 'Oracle 11'],
    env4 = ['MySQL', 'RHEL'],

    db.skill.insertMany( langs.map(function(skill) {return { 'name': skill }}), {ordered: false} ),
    db.skill.insertMany( techs.map(function(skill) {return { 'name': skill }}), {ordered: false}),
    db.skill.insertMany( servers.map(function(skill) {return { 'name': skill }}), {ordered: false}),
    db.skill.insertMany( dbs.map(function(skill) {return { 'name': skill }}), {ordered: false}),
    db.skill.insertMany( systems.map(function(skill) {return { 'name': skill }}), {ordered: false}),
    db.skill.insertMany( others.map(function(skill) {return { 'name': skill }}), {ordered: false}),

    person_id = ObjectId(),
    db.persons.insert({
        _id: person_id,
        name: 'Dzmitry Barysevich',
        email: 'zmicierb@tut.by',
        position: 'Java Developer',
        department: 'Application Development department',
        birthDate: new Date('1987/06/20'),
    }),

    db.skills.insert({
        personId: person_id.str,
        langs: langs,
        techs: techs,
        servers: servers,
        dbs: dbs,
        systems: systems,
        others: others,
    }),

    db.companies.insert({
        personId: person_id.str,
        name: 'IBA',
        startDate: new Date('2016/11/1'),
        endDate: new Date('2017/09/1'),
        projects: [
            {
                position: 'Java Developer',
                description: 'Intranet web-portal for company.',
                responsibility: 'Developing portlet.',
                result: 'Project in progress.',
                environment: env1,
            },
            {
                position: 'Java Developer',
                description: 'Online-shop for sale valuables.',
                responsibility: 'First phase is complete.',
                result: 'First phase is complete.',
                environment: env2,
            },
        ],
    }),

    db.companies.insert({
        personId: person_id.str,
        name: 'BELHARD',
        startDate: new Date('2010/09/1'),
        endDate: new Date('2016/10/31'),
        projects: [
            {
                position: 'Java Developer',
                description: 'Enterprise web-application for flow of documentations.',
                responsibility: 'Developing functionality for editing and printing documents from web-browser.',
                result: 'Working project from scratch in tight terms.',
                environment: env3,
            },
            {
                position: 'Senior System Integrator',
                description: 'System integration.',
                responsibility: 'Configuring OS, installing Oracle.',
                result: 'All projects are in production.',
                environment: env4,
            },
        ],
    }),
]

printjson(res)