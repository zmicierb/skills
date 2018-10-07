db.skill.drop(),

    db.skill.createIndex({name: 1}, {unique: true}),

    langs = ['Java 6', 'Java 7', 'Java 8', 'Go', 'JavaScript'],
    techs = ['Java EE 6', 'EJB 3.0', 'Liferay Enterprise', 'React', 'Angular'],
    servers = ['Apache Tomcat', 'IBM WebSphere Application Server 8.5', 'IBM WebSphere Portal Server 8.5'],
    dbs = ['PostgreSQL', 'Oracle 11', 'Mongodb', 'MySQL'],
    systems = ['RHEL', 'CentOS', 'Ubuntu'],
    others = ['JUnit', 'JDBC', 'Docker', 'Kafka', 'Redis', 'Hazelcast'],

    db.skill.insertMany(langs.map(function (skill) {
        return {'name': skill}
    }), {ordered: false}),
    db.skill.insertMany(techs.map(function (skill) {
        return {'name': skill}
    }), {ordered: false}),
    db.skill.insertMany(servers.map(function (skill) {
        return {'name': skill}
    }), {ordered: false}),
    db.skill.insertMany(dbs.map(function (skill) {
        return {'name': skill}
    }), {ordered: false}),
    db.skill.insertMany(systems.map(function (skill) {
        return {'name': skill}
    }), {ordered: false}),
    db.skill.insertMany(others.map(function (skill) {
        return {'name': skill}
    }), {ordered: false})
