package com.barysevich.project.commons.queue.task;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.barysevich.project.commons.utils.SqlUtils.getOptional;
import static java.util.Objects.requireNonNull;

/**
 * Имплементация {@link TaskManager}
 */
public class DefaultTaskManager implements TaskManager
{
    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedJt;

    private static final RowMapper<RawTask> TASK_ROW_MAPPER = (rs, rowNum) ->
            RawTask.<String>builder()
                    .withId(rs.getLong("id"))
                    .withAttempts(rs.getInt("attempts"))
                    .withCreateDate(rs.getTimestamp("create_date").toLocalDateTime())
                    .withChangeDate(rs.getTimestamp("change_date").toLocalDateTime())
                    .withExecutionDate(rs.getTimestamp("execution_date").toLocalDateTime())
                    .withData(rs.getString("task"))
//                    .withParentSpan(rs.getString("span"))
                    .build();


    public DefaultTaskManager(final JdbcTemplate jdbcTemplate, final NamedParameterJdbcTemplate namedJt)
    {
        this.jdbcTemplate = requireNonNull(jdbcTemplate);
        this.namedJt = requireNonNull(namedJt);
    }


    @Override
    public void addTask(final String serializedData, final UUID uuid, final TaskProperties properties)
    {
        final String SQL = "INSERT INTO tasks (create_date, change_date, execution_date, task, queue_name, uuid) " +
                "VALUES (:create_date, :change_date, :execution_date, :task, :queue_name, :uuid) ON CONFLICT DO NOTHING";

        final LocalDateTime now = LocalDateTime.now();
        final Timestamp timestampNow = Timestamp.valueOf(now);

        final MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("create_date", timestampNow)
                .addValue("change_date", timestampNow)
                .addValue("execution_date", Timestamp.valueOf(now.plus(properties.getDelay())))
                .addValue("task", serializedData)
                .addValue("queue_name", properties.getQueueName())
                .addValue("uuid", uuid);
//                .addValue("span", properties.getSpan().isPresent() ? toJson(properties.getSpan()) : null);

        namedJt.update(SQL, params);
    }


    @Override
    public Optional<RawTask> getNextTask(final String queueName)
    {
        final String SQL = "SELECT id, create_date, change_date, execution_date, attempts, task FROM tasks " +
                "WHERE execution_date <= ? AND queue_name = ? FOR UPDATE SKIP LOCKED LIMIT 1";

        return getOptional(() ->
                jdbcTemplate.queryForObject(SQL, TASK_ROW_MAPPER, Timestamp.valueOf(LocalDateTime.now()), queueName));
    }


    @Override
    public void updateExecutionDate(final long taskId, final LocalDateTime newExecutionDate)
    {
        final String SQL = "UPDATE tasks SET change_date = now(), execution_date = ? WHERE id = ?";

        jdbcTemplate.update(SQL, Timestamp.valueOf(newExecutionDate), taskId);
    }


    @Override
    public void remove(final long taskId)
    {
        final String SQL = "DELETE FROM tasks WHERE id = ?";
        jdbcTemplate.update(SQL, taskId);
    }


    @Override
    public void updateExecutionDateAndAttempts(final long taskId,
                                               final LocalDateTime newExecutionDate,
                                               final int attempts)
    {
        final String SQL = "UPDATE tasks SET change_date = now(), execution_date = ?, attempts = ? WHERE id = ?";

        jdbcTemplate.update(SQL, Timestamp.valueOf(newExecutionDate), attempts + 1, taskId);
    }


}
