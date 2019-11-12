package by.epam.fitness.entity;

import java.util.Objects;

public class ExerciseProgram extends Entity {
    private Long id;
    private Exercise exercise;
    private int repeatNumber;
    private int setNumber;
    private Long programId;
    private int numberTrainDay;

    public ExerciseProgram() {}

    public ExerciseProgram(Long id, Exercise exercise, int repeatNumber, int setNumber, Long programId, int numberTrainDay) {
        this.id = id;
        this.exercise = exercise;
        this.repeatNumber = repeatNumber;
        this.setNumber = setNumber;
        this.programId = programId;
        this.numberTrainDay = numberTrainDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public int getRepeatNumber() {
        return repeatNumber;
    }

    public void setRepeatNumber(int repeatNumber) {
        this.repeatNumber = repeatNumber;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public int getNumberTrainDay() {
        return numberTrainDay;
    }

    public void setNumberTrainDay(int numberTrainDay) {
        this.numberTrainDay = numberTrainDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExerciseProgram exerciseProgram = (ExerciseProgram) o;
        return  Objects.equals(getId(), exerciseProgram.getId()) &&
                getExercise().equals(exerciseProgram.getExercise()) &&
                Objects.equals(getProgramId(), exerciseProgram.getProgramId()) &&
                Objects.equals(getRepeatNumber(), exerciseProgram.getRepeatNumber()) &&
                Objects.equals(getSetNumber(), exerciseProgram.getSetNumber()) &&
                Objects.equals(getNumberTrainDay(), exerciseProgram.getNumberTrainDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getExercise(),getProgramId(),getRepeatNumber(),getSetNumber(),getNumberTrainDay());
    }
}
