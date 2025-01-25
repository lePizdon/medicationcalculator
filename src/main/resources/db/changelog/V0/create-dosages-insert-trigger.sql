CREATE OR REPLACE FUNCTION autofill_dosages_medication_fields()
    RETURNS TRIGGER AS
$$
BEGIN
    SELECT name, medications.injection_type
    INTO NEW.medication_name, NEW.medication_injection_type
    FROM medications
    WHERE id = NEW.medication_id;

    RETURN NEW;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER autofill_dosages_fields
    BEFORE INSERT ON dosages
    FOR EACH ROW
EXECUTE FUNCTION autofill_dosages_medication_fields()