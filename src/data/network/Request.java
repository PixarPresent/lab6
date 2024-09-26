package data.network;

import data.recources.Worker;
import server.managers.commands.BaseCommand;

import java.io.Serializable;

public class Request implements Serializable {
    private String message;
    private Worker worker;
    private BaseCommand command;

    public Request(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public BaseCommand getBaseCommand() {
        return command;
    }

    public void setBaseCommand(BaseCommand baseCommand) {
        this.command = baseCommand;
    }
}
