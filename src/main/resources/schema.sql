CREATE TABLE IF NOT EXISTS student (
    student_id UUID PRIMARY KEY NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    gender VARCHAR(6) NOT NULL
        CHECK (
            gender = 'MALE'   OR
            gender = 'male'   OR
            gender = 'FEMALE' OR
            gender = 'female'
        )
);

CREATE TABLE IF NOT EXISTS course (
    course_id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    department VARCHAR(255),
    teacher_name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS student_course (
    student_id UUID NOT NULL REFERENCES student (student_id),
    course_id UUID NOT NULL REFERENCES course (course_id),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    grade INTEGER CHECK (grade >= 0 AND grade <= 100),
    UNIQUE (student_id, course_id)
);

ALTER TABLE course ALTER department SET NOT NULL;

CREATE TYPE gender AS ENUM ('MALE', 'FEMALE');

ALTER TABLE student
ALTER COLUMN gender TYPE gender
USING (gender::gender);

ALTER TABLE student
DROP CONSTRAINT IF EXISTS student_gender_check;