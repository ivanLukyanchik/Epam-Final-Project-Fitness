package by.epam.fitness.command;

import java.util.Objects;

public class CommandResult {
    private String page;
    private boolean isRedirect;

    public CommandResult() {}

    public CommandResult(String page) {
        this.page = page;
        isRedirect = false;
    }

    public CommandResult(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandResult that = (CommandResult) o;
        return isRedirect == that.isRedirect &&
                Objects.equals(page, that.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, isRedirect);
    }
}
