-- Create audit_logs table
CREATE TABLE IF NOT EXISTS audit_logs (
                                          id BIGSERIAL PRIMARY KEY,
                                          entity_type VARCHAR(50) NOT NULL,
    entity_id BIGINT NOT NULL,
    action VARCHAR(20) NOT NULL,
    details VARCHAR(500),
    timestamp TIMESTAMP NOT NULL DEFAULT NOW()
    );

-- Create index for better performance
CREATE INDEX IF NOT EXISTS idx_audit_entity
    ON audit_logs(entity_type, entity_id, timestamp DESC);

-- Function to cleanup old audit logs
CREATE OR REPLACE FUNCTION cleanup_old_audits() RETURNS void AS $$
BEGIN
DELETE FROM audit_logs WHERE timestamp < NOW() - INTERVAL '30 days';
END;
$$ LANGUAGE plpgsql;