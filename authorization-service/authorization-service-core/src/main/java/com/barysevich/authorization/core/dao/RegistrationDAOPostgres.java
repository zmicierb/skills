package com.barysevich.authorization.core.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import com.barysevich.authorization.api.async.RegistrationStatus;
import com.barysevich.project.commons.utils.SqlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class RegistrationDAOPostgres implements RegistrationDAO
{
    private static final Logger logger = LoggerFactory.getLogger(RegistrationDAOPostgres.class);

    private final JdbcTemplate jt;

    private final NamedParameterJdbcTemplate namedJt;


    public RegistrationDAOPostgres(final JdbcTemplate jt,
                                   final NamedParameterJdbcTemplate namedJt)
    {
        this.jt = jt;
        this.namedJt = namedJt;
    }


    @Override
    public Long reserveId()
    {
        final String SQL = "INSERT INTO users (state_id) VALUES (?) RETURNING id";

        final long id = jt.queryForObject(SQL, Long.class, RegistrationStatus.NEW.getCode());

        logger.debug("Reserve id={}", id);

        return id;
    }


    @Override
    public boolean addCredentials(final long id, final String login,
                                  final Timestamp createdOn)
    {
        logger.debug("Add credentials : {}, {}, {}", id, login, createdOn);

        final String SQL = "UPDATE users SET login=:login, created_on=:created_on," +
                " state_id=:state_id WHERE id=:id";

        final MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("login", login)
                .addValue("created_on", createdOn)
                .addValue("state_id", RegistrationStatus.ACTIVE.getCode())
                .addValue("id", id);

        final boolean isUpdated = namedJt.update(SQL, map) > 0;

        logger.debug("addCredentials isUpdated={}", isUpdated);
        return isUpdated;
    }

    @Override
    public boolean storeRegistrationStatus(final long id, final int status)
    {
        return false;
    }


    @Override
    public Optional<RegistrationStatus> getRegistrationStatus(final long id)
    {
        return SqlUtils.getOptional(
                () -> jt.queryForObject("SELECT state_id FROM users WHERE id = ?",
                        this::mapRegistrationStatus, id));
    }


    private RegistrationStatus mapRegistrationStatus(final ResultSet rs, final int i) throws SQLException
    {
        return RegistrationStatus.getByCode(rs.getInt("state_id"));
    }
}
